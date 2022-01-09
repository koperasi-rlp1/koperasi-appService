package com.auth.koperasi.service.auth.dao;

import com.auth.koperasi.service.auth.model.NasabahCheck;
import com.auth.koperasi.service.auth.model.NasabahLogin;
import com.auth.koperasi.service.dto.NasabahDTO;
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
public class Login {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public NasabahLogin getUserByNipOrName(String param){
        String baseQuery = "SELECT * FROM \"NASABAH\" WHERE \"NIP\" = :param or \"USERNAME\" = :param";

        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("param", param);

        NasabahLogin dataNasabah = namedParameterJdbcTemplate.queryForObject(baseQuery, parameterSource, new RowMapper<NasabahLogin>() {
            @Override
            public NasabahLogin mapRow(ResultSet resultSet, int i) throws SQLException {
                NasabahLogin data = new NasabahLogin();
                data.setNip(resultSet.getString("NIP"));
                data.setUsername(resultSet.getString("USERNAME"));
                data.setPassword(resultSet.getString("PASSWORD"));
                return data;
            }
        });

        return dataNasabah;
    }

    public List<NasabahCheck> checkLogin(String nip){
        String baseQuery = "SELECT * FROM \"TA_NASABAH_LOGIN\" WHERE \"NIP\" = :nip";

        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("nip", nip);

        List<NasabahCheck> dataNasabah = namedParameterJdbcTemplate.query(baseQuery, parameterSource, new RowMapper<NasabahCheck>() {
            @Override
            public NasabahCheck mapRow(ResultSet resultSet, int i) throws SQLException {
                NasabahCheck data = new NasabahCheck();
                data.setNip(resultSet.getString("NIP"));
                data.setSince(resultSet.getTimestamp("SINCE"));
                data.setTokenKey(resultSet.getString("TOKEN_KEY"));
                return data;
            }
        });

        return dataNasabah;
    }

    public NasabahDTO.DataNasabah getDataNasabahByNip(String nip) throws EmptyResultDataAccessException {
        String baseQuery = "SELECT * FROM \"NASABAH\" WHERE \"NIP\" = :nip";

        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("nip", nip);

        NasabahDTO.DataNasabah dataNasabah = namedParameterJdbcTemplate.queryForObject(baseQuery, parameterSource, new RowMapper<NasabahDTO.DataNasabah>() {
            @Override
            public NasabahDTO.DataNasabah mapRow(ResultSet resultSet, int i) throws SQLException{
                NasabahDTO.DataNasabah data = new NasabahDTO.DataNasabah();
                data.setNip(resultSet.getString("NIP"));
                data.setNamaNasabah(resultSet.getString("NAMA_NASABAH"));
                data.setEmail(resultSet.getString("EMAIL"));
                data.setJabatan(resultSet.getString("JABATAN"));
                data.setNoHp(resultSet.getString("NO_HP"));
                data.setUnitOperasional(resultSet.getString("UNIT_OPERASIONAL"));
                data.setIdBackup(resultSet.getString("ID_BACKUP"));
                data.setFileBuktiPembayaran(resultSet.getString("BUKTI_PEMBAYARAN"));
                data.setCreatedDate(resultSet.getTimestamp("CREATED_DATE"));
                data.setIdStatusKeanggotaan(resultSet.getInt("ID_STATUS_KEANGGOTAAN"));
                return data;
            }
        });

        return dataNasabah;
    }

    public void addInfo(NasabahCheck nasabahCheck){
        String baseQuery= "INSERT INTO \"TA_NASABAH_LOGIN\"(\"NIP\", \"TOKEN_KEY\", \"SINCE\") " +
                "VALUES(:nip, :token, CURRENT_TIMESTAMP)";

        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("nip", nasabahCheck.getNip());
        parameterSource.addValue("token", nasabahCheck.getTokenKey());

        namedParameterJdbcTemplate.update(baseQuery, parameterSource);
    }

}
