package org.example.message.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Objects;

@Configuration
public class JdbcConfig {
    public Connection connection(JdbcTemplate jdbcTemplate) throws SQLException {
        return Objects.requireNonNull(jdbcTemplate.getDataSource()).getConnection();
    }
}
