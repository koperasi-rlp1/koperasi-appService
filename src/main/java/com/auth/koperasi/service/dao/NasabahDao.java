package com.auth.koperasi.service.dao;

import com.auth.koperasi.service.dto.NasabahDTO;
import com.auth.koperasi.service.entity.Nasabah;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.EmptyStackException;
import java.util.Optional;

@Repository
public class NasabahDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public NasabahDTO.NasabahDaftar save(NasabahDTO.NasabahDaftar nasabah) throws SQLException{
        String baseQuery = "INSERT INTO NASABAH(NIP, NAMA_NASABAH, EMAIL, NO_HP, JABATAN, UNIT_OPERASIONAL, " +
                "USERNAME, PASSWORD, ID_BACKUP, ID_STATUS, BUKTI_PEMBAYARAN) VALUES(:nip, :namaNasabah, :email, " +
                ":noHp, :jabatan, :unitOperasional, :username, :password, :idBackup, :idStatusKeanggotaan, :buktiPembayaran)";

        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("nip", nasabah.getNip());
        parameterSource.addValue("namaNasabah", nasabah.getNamaNasabah());
        parameterSource.addValue("email", nasabah.getEmail());
        parameterSource.addValue("jabatan", nasabah.getJabatan());
        parameterSource.addValue("unitOperasional", nasabah.getUnitOperasional());
        parameterSource.addValue("username", nasabah.getUsername());
        parameterSource.addValue("password", nasabah.getPassword());
        parameterSource.addValue("idBackup", nasabah.getIdBackup());
        parameterSource.addValue("idStatusKeanggotaan", nasabah.getIdStatusKeanggotaan());
        parameterSource.addValue("noHp", nasabah.getNoHp());
        parameterSource.addValue("buktiPembayaran", nasabah.getFileBuktiPembayaran());

        this.namedParameterJdbcTemplate.update(baseQuery, parameterSource);

        return nasabah;
    }

    public Optional<NasabahDTO.DataNasabah> getDataNasabahByNip(Integer nip) throws EmptyResultDataAccessException{
        String baseQuery = "SELECT * FROM NASABAH WHERE NIP = :nip";

        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("nip", nip);

        NasabahDTO.DataNasabah dataNasabah = namedParameterJdbcTemplate.queryForObject(baseQuery, parameterSource, new RowMapper<NasabahDTO.DataNasabah>() {
            @Override
            public NasabahDTO.DataNasabah mapRow(ResultSet resultSet, int i) throws SQLException{
                NasabahDTO.DataNasabah data = new NasabahDTO.DataNasabah();
                data.setNip(resultSet.getInt("NIP"));
                data.setNamaNasabah(resultSet.getString("NAMA_NASABAH"));
                data.setEmail(resultSet.getString("EMAIL"));
                data.setJabatan(resultSet.getString("JABATAN"));
                data.setNoHp(resultSet.getString("NO_HP"));
                data.setUnitOperasional(resultSet.getString("UNIT_OPERASIONAL"));
                data.setIdBackup(resultSet.getString("ID_BACKUP"));
                data.setFileBuktiPembayaran(resultSet.getString("BUKTI_PEMBAYARAN"));
                return data;
            }
        });

        return Optional.of(dataNasabah);
    }


}
