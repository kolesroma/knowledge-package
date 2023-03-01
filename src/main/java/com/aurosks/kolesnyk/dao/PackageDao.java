package com.aurosks.kolesnyk.dao;

import com.aurosks.kolesnyk.entity.PackageEntity;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;
import java.util.Optional;

@Repository
public class PackageDao {
    private final JdbcTemplate jdbcTemplate;

    public PackageDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public Integer create(PackageEntity packageEntity) {
        String SQL = "insert into `package` (`title`, `description`, `createdAt`) values(?, ?, ?)";
        var keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            var preparedStatement = connection.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, packageEntity.getTitle());
            preparedStatement.setString(2, packageEntity.getDescription());
            preparedStatement.setDate(3, Date.valueOf(packageEntity.getCreatedAt()));
            return preparedStatement;
        }, keyHolder);
        return Optional.ofNullable(keyHolder.getKey())
                .orElseThrow()
                .intValue();
    }

    public List<PackageEntity> findAll() {
        String SQL = "select * from `package`";
        return jdbcTemplate.query(SQL, this::packageEntityRowMapper);
    }

    private PackageEntity packageEntityRowMapper(ResultSet resultSet, int row) {
        try {
            return new PackageEntity(resultSet.getInt("id"),
                    resultSet.getString("title"),
                    resultSet.getString("description"),
                    resultSet.getDate("createdAt").toLocalDate());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteById(Integer id) {
        String SQL = "delete from `package` where `id` = ?";
        int rows = jdbcTemplate.update(SQL, id);
        if (rows == 0) {
            throw new RuntimeException("no such element to delete");
        }
    }
}
