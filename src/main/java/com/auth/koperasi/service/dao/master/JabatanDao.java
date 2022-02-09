package com.auth.koperasi.service.dao.master;

import com.auth.koperasi.service.entity.master.Jabatan;
import com.auth.koperasi.service.entity.master.StatusKeanggotaan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class JabatanDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public List<Jabatan> findAll() throws EmptyResultDataAccessException {
        String baseQuery = "SELECT \"JABATAN\" AS jabatan FROM \"TM_JABATAN\"";

        MapSqlParameterSource parameterSource = new MapSqlParameterSource();

        return namedParameterJdbcTemplate.query(baseQuery, parameterSource, new RowMapper<Jabatan>() {
            @Override
            public Jabatan mapRow(ResultSet resultSet, int i) throws SQLException {
                Jabatan stat = new Jabatan();
                stat.setJabatan(resultSet.getString("jabatan"));

                return stat;
            }
        });
    }

}
