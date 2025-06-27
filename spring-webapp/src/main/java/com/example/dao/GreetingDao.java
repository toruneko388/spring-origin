package com.example.dao;

import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class GreetingDao {
    private final JdbcTemplate jdbc;

    public GreetingDao(DataSource ds) {
        this.jdbc = new JdbcTemplate(ds);
    }

    public String findFirst() {
        return jdbc.queryForObject(
            "SELECT message FROM greeting ORDER BY id LIMIT 1",
            String.class
        );
    }
}
