package com.ccsu.jc.tvbank.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class DataJdbcTemplate {

    @Autowired
    JdbcTemplate jdbcTemplate;

    //得到spring注入的模板
    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

}
