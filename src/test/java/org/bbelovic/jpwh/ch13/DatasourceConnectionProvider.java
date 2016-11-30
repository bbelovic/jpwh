package org.bbelovic.jpwh.ch13;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class DatasourceConnectionProvider {
    private DataSource dataSource = init();

    private HikariDataSource init() {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setUsername("testuser");
        hikariConfig.setPassword("test_pass");
        hikariConfig.setJdbcUrl("jdbc:postgresql://localhost:5432/jpwh3");
        hikariConfig.setDriverClassName("org.postgresql.Driver");
        return new HikariDataSource(hikariConfig);
    }

    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
