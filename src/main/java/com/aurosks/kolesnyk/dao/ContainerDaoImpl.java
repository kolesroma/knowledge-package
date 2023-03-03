package com.aurosks.kolesnyk.dao;

import com.aurosks.kolesnyk.entity.ContainerEntity;
import com.aurosks.kolesnyk.entity.PackageEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.support.TransactionTemplate;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

@Repository
public class ContainerDaoImpl implements ContainerDao {
    private final JdbcTemplate jdbcTemplate;
    private final PlatformTransactionManager transactionManager;

    public ContainerDaoImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.transactionManager = new DataSourceTransactionManager(dataSource);
    }

    @Override
    public List<ContainerEntity> findAll() {
        String SQL = "select * from `container`";
        return jdbcTemplate.query(SQL, this::containerEntityRowMapper);
    }

    private ContainerEntity containerEntityRowMapper(ResultSet resultSet, int i) {
        try {
            return new ContainerEntity(resultSet.getInt("id"),
                    resultSet.getString("title"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteById(Integer id) {
        String SQL = "delete from `container` where `id` = ?";
        int rows = jdbcTemplate.update(SQL, id);
        if (rows == 0) {
            throw new RuntimeException("no such element to delete");
        }
    }

    @Override
    public List<PackageEntity> findAllById(Integer containerId) {
        String SQL = "select * from `package` join `package_container` `pc` on `package`.`id` = `pc`.`package_id` where `container_id` = ?";
        return jdbcTemplate.query(SQL,
                preparedStatement -> preparedStatement.setInt(1, containerId),
                PackageDaoImpl::packageEntityRowMapper);
    }

    @Override
    public Integer create(ContainerEntity containerEntity) {
        String SQL = "insert into `container` (`title`) values(?)";
        var keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            var preparedStatement = connection.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, containerEntity.getTitle());
            return preparedStatement;
        }, keyHolder);
        return Optional.ofNullable(keyHolder.getKey())
                .orElseThrow()
                .intValue();
    }

    @Override
    public void update(Integer containerId, List<PackageEntity> packageEntities) {
        var transactionTemplate = new TransactionTemplate(transactionManager);
        transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        transactionTemplate.execute(status -> {
            String SQL = "delete from `package_container` where `container_id` = ?";
            jdbcTemplate.update(SQL, containerId);
            String SQL2 = "insert into `package_container` (`package_id`, `container_id`) values (?, ?)";
            packageEntities.forEach(
                    packageEntity -> jdbcTemplate.update(SQL2,  packageEntity.getId(), containerId)
            );
            return null;
        });
    }
}
