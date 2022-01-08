package com.auth.koperasi.service.dao.master;

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
import java.util.Optional;

@Repository
public class StatusKeanggotaanDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public List<StatusKeanggotaan> findAll() throws EmptyResultDataAccessException {
        String baseQuery = "SELECT \"ID\" AS id, \"KETERANGAN\" as keterangan FROM \"STATUS_KEANGGOTAAN\"";

        MapSqlParameterSource parameterSource = new MapSqlParameterSource();

        return namedParameterJdbcTemplate.query(baseQuery, parameterSource, new RowMapper<StatusKeanggotaan>() {
            @Override
            public StatusKeanggotaan mapRow(ResultSet resultSet, int i) throws SQLException {
                StatusKeanggotaan stat = new StatusKeanggotaan();
                stat.setId(resultSet.getInt("id"));
                stat.setKeterangan(resultSet.getString("keterangan"));

                return stat;
            }
        });
    }

}
