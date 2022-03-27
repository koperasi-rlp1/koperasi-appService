package com.auth.koperasi.service.dao;

import com.auth.koperasi.service.dto.PinjamanDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;

@Repository
public class PinjamanDao {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    public int saveApproval(PinjamanDTO.PinjamanApproval value) throws SQLException{
        String baseQuery = "insert into \"TN_PINJAMAN_APPROVAL\"(\"ID_NASABAH\",\"NOMINAL_PINJAMAN\",\"TANGGAL\",\"WAKTU\",\"TUJUAN_PEMINJAMAN\",\"BULAN_BAYAR\")\n" +
                "values(:idNasabah, :nominalPinjaman, :tanggal, :waktu, :tujuanPinjam, :bulanBayar)";

        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("idNasabah", value.getIdNasabah());
        parameterSource.addValue("nominalPinjaman", value.getNominalTransaksi());
        parameterSource.addValue("tanggal", value.getTanggal());
        parameterSource.addValue("waktu", value.getWaktu());
        parameterSource.addValue("tujuanPinjam", value.getTujuanPinjam());
        parameterSource.addValue("bulanBayar", value.getBulanBayar());

        return jdbcTemplate.update(baseQuery, parameterSource);
    }

//    public int savePinjaman(PinjamanDTO.PinjamanTerima value) throws SQLException{
//        String baseQuery = "insert into \"TN_PINJAMAN_APPROVAL\"(\"ID_NASABAH\",\"NOMINAL_PINJAMAN\",\"TANGGAL\",\"WAKTU\",\"TUJUAN_PEMINJAMAN\",\"BULAN_BAYAR\")\n" +
//                "values(:idNasabah, :nominalPinjaman, :tanggal, :waktu, :tujuanPinjam, :bulanBayar)";
//
//        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
//        parameterSource.addValue("idNasabah", value.getIdNasabah());
//        parameterSource.addValue("nominalPinjaman", value.getNominalTransaksi());
//        parameterSource.addValue("tanggal", value.getTanggal());
//        parameterSource.addValue("waktu", value.getWaktu());
//        parameterSource.addValue("tujuanPinjam", value.getTujuanPinjam());
//        parameterSource.addValue("bulanBayar", value.getBulanBayar());
//
//        return jdbcTemplate.update(baseQuery, parameterSource);
//    }


}
