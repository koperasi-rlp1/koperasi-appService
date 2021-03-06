package com.auth.koperasi.service.dao;

import com.auth.koperasi.service.dao.query.QueryTransaksi;
import com.auth.koperasi.service.dto.SimpananSukaRelaDTO;
import com.auth.koperasi.service.dto.TransaksiDTO;
import com.auth.koperasi.service.entity.datatables.DataTableRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
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
                "from \"TN_TRANSAKSI_APPROVAL\" " +
                "where 1 = 1 ";

        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        StringBuilder query = new StringBuilder(baseQuery);

        query.append(" and \"ID_NASABAH\" = :idNasabah ");
        parameterSource.addValue("idNasabah", request.getExtraParam().getIdNasabah());

        query.append(" and \"JENIS_TRANSAKSI\" = :jenisTransaksi ");
        parameterSource.addValue("jenisTransaksi", request.getExtraParam().getJenisTransaksi());

        query.append(" order by :sortCol asc");
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
                "to_char(\"TANGGAL\", 'dd/mm/yyyy') as tanggal,\n" +
                "\"DESKRIPSI\" as deskripsi,\n" +
                "\"BUKTI_TRANSAKSI\" as buktiPembayaran\n" +
                "from \"TN_TRANSAKSI_APPROVAL\"\n" +
                "where 1 = 1 ";

        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        StringBuilder query = new StringBuilder(baseQuery);

        query.append("and \"ID_NASABAH\" = :idNasabah ");
        parameterSource.addValue("idNasabah", request.getExtraParam().getIdNasabah());


        query.append(" and \"JENIS_TRANSAKSI\" = :jenisTransaksi ");
        parameterSource.addValue("jenisTransaksi", request.getExtraParam().getJenisTransaksi());

        query.append(" order by :sortCol asc");
        parameterSource.addValue("sortCol", request.getSortCol()+1);
//        parameterSource.addValue("sortDir", request.getSortDir());

        query.append(" limit :limit offset :offset");
        parameterSource.addValue("limit", request.getLength());
        parameterSource.addValue("offset", request.getStart());

        return namedParameterJdbcTemplate.query(query.toString(), parameterSource, new BeanPropertyRowMapper<>(TransaksiDTO.DataTransaksi.class));
    }

    public TransaksiDTO.DataSaldoNasabah findDataSaldoNasabah(String nip) throws EmptyResultDataAccessException{
        String baseQuery = QueryTransaksi.QUERY_SALDO_NASABAH;

        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("nip", nip);

        return namedParameterJdbcTemplate.queryForObject(baseQuery, parameterSource, new BeanPropertyRowMapper<>(TransaksiDTO.DataSaldoNasabah.class));
    }


    public TransaksiDTO.DataPinjamanNasabah findDataPinjamanNasabah(String nip) throws EmptyResultDataAccessException{
        String baseQuery = QueryTransaksi.QUERY_PINJAMAN_NASABAH;

        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("nip", nip);

        return namedParameterJdbcTemplate.queryForObject(baseQuery, parameterSource, new BeanPropertyRowMapper<>(TransaksiDTO.DataPinjamanNasabah.class));
    }



}
