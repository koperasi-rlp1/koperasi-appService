package com.auth.koperasi.service.dao;

import com.auth.koperasi.service.dto.SimpananSukaRelaDTO;
import com.auth.koperasi.service.dto.TransaksiDTO;
import com.auth.koperasi.service.entity.datatables.DataTableRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TransaksiDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public Long datatablesApprovalCount(DataTableRequest<TransaksiDTO.DataTransaksi> request){
        String baseQuery = "select count(*) as row_count " +
                "from \"TN_SIPM_APPROVAL\" " +
                "where 1 = 1 ";

        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        StringBuilder query = new StringBuilder(baseQuery);

        query.append(" and \"ID_NASABAH\" = :idNasabah ");
        parameterSource.addValue("idNasabah", request.getExtraParam().getIdNasabah());

        query.append(" and \"JENIS_TRANSAKSI\" = :jenisTransaksi ");
        parameterSource.addValue("jenisTransasksi", request.getExtraParam().getJenisTransaksi());

        query.append(" order by :sortCol desc");
        parameterSource.addValue("sortCol", request.getSortCol()+1);
//        parameterSource.addValue("sortDir", request.getSortDir());

        query.append(" limit :limit offset :offset");
        parameterSource.addValue("limit", request.getLength());
        parameterSource.addValue("offset", request.getStart());

        return this.namedParameterJdbcTemplate.queryForObject(
                query.toString(),parameterSource,(resultSet, i) -> resultSet.getLong("row_count")
        );
    }

    public List<TransaksiDTO.DataTransaksi> datatablesApproval(DataTableRequest<TransaksiDTO.DataTransaksi> request){
        String baseQuery = "select row_number() over (order by \"ID\") as no,\n" +
                "\"ID\" as idApproval,\n" +
                "\"ID_NASABAH\" as idNasabah,\n" +
                "\"NOMINAL_TRANSAKSI\" as nominalTransaksi,\n" +
                "\"TANGGAL\" as tanggal,\n" +
                "\"DESKRIPSI\" as deskripsi,\n" +
                "\"BUKTI_TRANSAKSI\" as buktiPembayaran\n" +
                "from \"TN_TRANSAKSI_APPROVAL\"\n" +
                "where 1 = 1 ";

        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        StringBuilder query = new StringBuilder(baseQuery);

        query.append("and \"ID_NASABAH\" = :idNasabah ");
        parameterSource.addValue("idNasabah", request.getExtraParam().getIdNasabah());


        query.append(" and \"JENIS_TRANSAKSI\" = :jenisTransaksi ");
        parameterSource.addValue("jenisTransasksi", request.getExtraParam().getJenisTransaksi());

        query.append(" order by :sortCol desc");
        parameterSource.addValue("sortCol", request.getSortCol()+1);
//        parameterSource.addValue("sortDir", request.getSortDir());

        query.append(" limit :limit offset :offset");
        parameterSource.addValue("limit", request.getLength());
        parameterSource.addValue("offset", request.getStart());

        return namedParameterJdbcTemplate.query(query.toString(), parameterSource, new BeanPropertyRowMapper<>(TransaksiDTO.DataTransaksi.class));
    }
}
