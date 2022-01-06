package com.auth.koperasi.service.dao;

import com.auth.koperasi.service.dto.NasabahDTO;
import com.auth.koperasi.service.entity.Nasabah;
import com.auth.koperasi.service.entity.master.StatusKeanggotaan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.EmptyStackException;
import java.util.List;
import java.util.Optional;

@Repository
public class NasabahDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public NasabahDTO.NasabahDaftar save(NasabahDTO.NasabahDaftar nasabah) throws SQLException{
        String baseQuery = "INSERT INTO NASABAH(NIP, NAMA_NASABAH, EMAIL, NO_HP, JABATAN, UNIT_OPERASIONAL, " +
                "USERNAME, PASSWORD, ID_BACKUP, ID_STATUS, BUKTI_PEMBAYARAN, CREATED_DATE) VALUES(:nip, :namaNasabah, :email, " +
                ":noHp, :jabatan, :unitOperasional, :username, :password, :idBackup, :idStatusKeanggotaan, :buktiPembayaran, :createdDate)";

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
        parameterSource.addValue("createdDate", nasabah.getCreatedDate());

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
                data.setCreatedDate(resultSet.getTimestamp("CREATED_DATE"));
                return data;
            }
        });

        return Optional.of(dataNasabah);
    }

    public List<NasabahDTO.DataNasabah> find(Integer nip) throws EmptyResultDataAccessException {
        String baseQuery = "SELECT NIP FROM NASABAH WHERE NIP = :nip";

        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("nip", nip);

        return namedParameterJdbcTemplate.query(baseQuery, parameterSource, new RowMapper<NasabahDTO.DataNasabah>() {
            @Override
            public NasabahDTO.DataNasabah mapRow(ResultSet resultSet, int i) throws SQLException {
                NasabahDTO.DataNasabah data = new NasabahDTO.DataNasabah();
                data.setNip(resultSet.getInt("NIP"));

                return data;
            }
        });
    }

    public void update(NasabahDTO.NasabahDaftar value) throws DataAccessException {
        String baseQuery = "UPDATE NASABAH SET ";

        MapSqlParameterSource parameterSource = new MapSqlParameterSource();

        StringBuilder buildInQuery;

        buildInQuery = new StringBuilder(baseQuery);
            buildInQuery.append(" NIP = :nip");
            parameterSource.addValue("nip", value.getNip());

        if(value.getNamaNasabah() != null){
            buildInQuery.append(", NAMA_NASABAH = :namaNasabah");
            parameterSource.addValue("namaNasabah", value.getNamaNasabah());
        }

        if(value.getEmail() != null){
            buildInQuery.append(", EMAIL = :email");
            parameterSource.addValue("email", value.getEmail());
        }

        if(value.getNoHp() != null){
            buildInQuery.append(", NO_HP = :noHp");
            parameterSource.addValue("noHp", value.getNoHp());
        }

        if(value.getJabatan() != null){
            buildInQuery.append(", JABATAN = :jabatan");
            parameterSource.addValue("jabatan", value.getJabatan());
        }

        if(value.getUnitOperasional() != null){
            buildInQuery.append(", UNIT_OPERASIONAL = :unitOperasional");
            parameterSource.addValue("unitOperasional", value.getUnitOperasional());
        }

        if(value.getUsername() != null){
            buildInQuery.append(", USERNAME = :username");
            parameterSource.addValue("username", value.getUsername());
        }

        if(value.getPassword() != null){
            buildInQuery.append(", PASSWORD = :password");
            parameterSource.addValue("password", value.getPassword());
        }

        if(value.getIdBackup() != null){
            buildInQuery.append(", ID_BACKUP = :idBakcup");
            parameterSource.addValue("idBackup", value.getIdBackup());
        }

        if(value.getIdStatusKeanggotaan() != null){
            buildInQuery.append(", ID_STATUS_KEANGGOTAAN = :idKeanggotaan");
            parameterSource.addValue("idKeanggotaan", value.getIdStatusKeanggotaan());
        }

        if(value.getFileBuktiPembayaran() != null){
            buildInQuery.append(", BUKTI_PEMBAYARAN = :buktiPembayaran");
            parameterSource.addValue("buktiPembayaran", value.getFileBuktiPembayaran());
        }

        if(value.getCreatedDate() != null){
            buildInQuery.append(", CREATED_DATE = :createdDate");
            parameterSource.addValue("createdDate", value.getCreatedDate());
        }

        buildInQuery.append(" WHERE NIP = :nipInduk");
        parameterSource.addValue("nipInduk", value.getNip());

        namedParameterJdbcTemplate.update(buildInQuery.toString(),parameterSource);
    }


}
