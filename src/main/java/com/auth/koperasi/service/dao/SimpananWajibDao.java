package com.auth.koperasi.service.dao;

import com.auth.koperasi.service.dto.SimpananWajibDTO;
import com.auth.koperasi.service.entity.datatables.DataTableRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SimpananWajibDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public Long datatablesCount(DataTableRequest<SimpananWajibDTO.DataSimpanan> request){
        String baseQuery = "select count(*) as row_count " +
                "from \"TN_SIPM_WAJIB\" tsw left join \"TN_TRANSAKSI\" tt on tsw.\"ID_TRANSAKSI\" = tt.\"ID\" " +
                "where 1 = 1 ";

        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        StringBuilder query = new StringBuilder(baseQuery);

        query.append(" and tsw.\"ID_NASABAH\" = :idNasabah ");
        parameterSource.addValue("idNasabah", request.getExtraParam().getIdNasabah());

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

    public List<SimpananWajibDTO.DataSimpanan> datatables(DataTableRequest<SimpananWajibDTO.DataSimpanan> request){
        String baseQuery = "select row_number() over (order by tsw.\"ID\") as no,\n" +
                "tsw.\"ID\" as id,\n" +
                "tsw.\"ID_TRANSAKSI\" as noTransaksi,\n" +
                "tsw.\"ID_NASABAH\" as idNasabah,\n" +
                "tsw.\"NOMINAL_TRANSAKSI\" as nominalTransaksi,\n" +
                "tsw.\"TANGGAL\" as tanggal,\n" +
                "tt.\"DESKRIPSI\" as deskripsi\n" +
                "from \"TN_SIPM_WAJIB\" tsw left join \"TN_TRANSAKSI\" tt on tsw.\"ID_TRANSAKSI\" = tt.\"ID\" " +
                "where 1 = 1 ";

        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        StringBuilder query = new StringBuilder(baseQuery);

        query.append("and tsw.\"ID_NASABAH\" = :idNasabah ");
        parameterSource.addValue("idNasabah", request.getExtraParam().getIdNasabah());

        query.append(" order by :sortCol desc");
        parameterSource.addValue("sortCol", request.getSortCol()+1);
//        parameterSource.addValue("sortDir", request.getSortDir());

        query.append(" limit :limit offset :offset");
        parameterSource.addValue("limit", request.getLength());
        parameterSource.addValue("offset", request.getStart());

        return namedParameterJdbcTemplate.query(query.toString(), parameterSource, new BeanPropertyRowMapper<>(SimpananWajibDTO.DataSimpanan.class));
    }

    public List<SimpananWajibDTO.DataSimpanan> findAll(){
        String baseQuery = "select row_number() over (order by tsw.\"ID\") as no,\n" +
                "tsw.\"ID\" as id,\n" +
                "tsw.\"ID_TRANSAKSI\" as noTransaksi,\n" +
                "tsw.\"ID_NASABAH\" as idNasabah,\n" +
                "tsw.\"NOMINAL_TRANSAKSI\" as nominalTransaksi,\n" +
                "tsw.\"TANGGAL\" as tanggal,\n" +
                "tt.\"DESKRIPSI\" as deskripsi\n" +
                "from \"TN_SIPM_WAJIB\" tsw left join \"TN_TRANSAKSI\" tt on tsw.\"ID_TRANSAKSI\" = tt.\"ID\" " +
                "where 1 = 1 ";

        MapSqlParameterSource parameterSource = new MapSqlParameterSource();

        return namedParameterJdbcTemplate.query( baseQuery, parameterSource, new BeanPropertyRowMapper<>(SimpananWajibDTO.DataSimpanan.class));

    }

}
