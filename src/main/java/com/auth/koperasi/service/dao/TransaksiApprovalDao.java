package com.auth.koperasi.service.dao;

import com.auth.koperasi.service.dto.TransaksiApprovalDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class TransaksiApprovalDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public TransaksiApprovalDTO.PengajuanSimpanan pengajuanSimpanan(TransaksiApprovalDTO.PengajuanSimpanan value) throws DataAccessException{
        String baseQuery = "INSERT INTO \"TN_TRANSAKSI_APPROVAL\"(\"JENIS_TRANSAKSI\", \"NOMINAL_TRANSAKSI\", \"ID_NASABAH\", \"BUKTI_TRANSAKSI\", \"DESKRIPSI\", \"TANGGAL\")\n" +
                "VALUES(:jenisTransaksi, :nominalTransaksi, :idNasabah, :buktiTransaksi, :deskripsi, :tanggal)";

        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("jenisTransaksi", value.getJenisTransaksi());
        parameterSource.addValue("nominalTransaksi", value.getNominalTransaksi());
        parameterSource.addValue("idNasabah", value.getIdNasabah());
        parameterSource.addValue("buktiTransaksi", value.getBuktiPembayaran());
        parameterSource.addValue("deskripsi", value.getDeskripsi());
        parameterSource.addValue("tanggal", value.getTanggal());

        namedParameterJdbcTemplate.update(baseQuery, parameterSource);

        return value;
    }
}
