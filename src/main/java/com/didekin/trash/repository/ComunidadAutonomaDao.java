package com.didekin.trash.repository;

import com.didekin.trash.dominio.ComunidadAutonoma;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * User: pedro@didekin
 * Date: 26/04/16
 * Time: 18:18
 */
@Repository
public class ComunidadAutonomaDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public ComunidadAutonomaDao(JdbcTemplate jdbcTemplate)
    {
        this.jdbcTemplate = jdbcTemplate;
    }

    ComunidadAutonoma getComunidadById(long cId)
    {
        return jdbcTemplate.queryForObject(
                "select * from didekin_pre.comunidad_autonoma where ca_id = ?",
                new RowMapper<ComunidadAutonoma>() {
                    @Override
                    public ComunidadAutonoma mapRow(ResultSet rs, int rowNum) throws SQLException
                    {
                        return new ComunidadAutonoma(rs.getLong("ca_id"), rs.getString("nombre"));
                    }
                }, cId);
    }
}

