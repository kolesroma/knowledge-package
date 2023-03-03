package com.aurosks.kolesnyk.dao;

import com.aurosks.kolesnyk.entity.ContainerEntity;
import com.aurosks.kolesnyk.entity.PackageEntity;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;
import java.util.Optional;

@Repository
public class ContainerDaoImpl implements ContainerDao {
    private final JdbcTemplate jdbcTemplate;

    public ContainerDaoImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
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
}
