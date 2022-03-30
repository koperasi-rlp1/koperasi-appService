package com.auth.koperasi.service.dao;

import com.auth.koperasi.service.dto.PinjamanDTO;
import com.auth.koperasi.service.dto.SimpananSukaRelaDTO;
import com.auth.koperasi.service.entity.datatables.DataTableRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

@Repository
public class PinjamanDao {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    public int saveApproval(PinjamanDTO.PinjamanApproval value) throws SQLException{
        String baseQuery = "insert into \"TN_PINJAMAN_APPROVAL\"(\"ID_NASABAH\",\"NOMINAL_PINJAMAN\",\"TUJUAN_PEMINJAMAN\",\"BULAN_BAYAR\")\n" +
                "values(:idNasabah, :nominalPinjaman, :tujuanPinjam, :bulanBayar)";

        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("idNasabah", value.getIdNasabah());
        parameterSource.addValue("nominalPinjaman", value.getNominalTransaksi());
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


    public Long datatablesCountApproval(DataTableRequest<PinjamanDTO.PinjamanApproval> request){
        String baseQuery = "select count(*) as row_count " +
                "from \"TN_PINJAMAN_APPROVAL\" tpa join \"NASABAH\" n on tpa.\"ID_NASABAH\" = n.\"ID_BACKUP\" \n" +
                "where 1 = 1 ";

        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        StringBuilder query = new StringBuilder(baseQuery);

        query.append(" and tpa.\"ID_NASABAH\" = :idNasabah ");
        parameterSource.addValue("idNasabah", request.getExtraParam().getIdNasabah());

        query.append(" order by :sortCol desc");
        parameterSource.addValue("sortCol", request.getSortCol()+1);
//        parameterSource.addValue("sortDir", request.getSortDir());

        query.append(" limit :limit offset :offset");
        parameterSource.addValue("limit", request.getLength());
        parameterSource.addValue("offset", request.getStart());

        return this.jdbcTemplate.queryForObject(
                query.toString(),parameterSource,(resultSet, i) -> resultSet.getLong("row_count")
        );
    }

    public List<PinjamanDTO.PinjamanApproval> datatablesApproval(DataTableRequest<PinjamanDTO.PinjamanApproval> request){
        String baseQuery = "select row_number() over (order by tpa.\"ID\") as no,\n" +
                "tpa.\"ID\" as id,\n" +
                "tpa.\"ID_NASABAH\" as \"ID_NASABAH\",\n" +
                "tpa.\"NOMINAL_PINJAMAN\" as nominalTransaksi,\n" +
                "tpa.\"TANGGAL\" as tanggal,\n" +
                "tpa.\"WAKTU\" as waktu,\n" +
                "tpa.\"BULAN_BAYAR\" as bulanBayar,\n" +
                "tpa.\"TUJUAN_PEMINJAMAN\" as tujuanPinjam,\n" +
                "n.\"NIP\" as nip,\n" +
                "n.\"NAMA_NASABAH\" as namaNasabah\n" +
                "from \"TN_PINJAMAN_APPROVAL\" tpa join \"NASABAH\" n on tpa.\"ID_NASABAH\" = n.\"ID_BACKUP\" \n" +
                "where 1 = 1 ";

        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        StringBuilder query = new StringBuilder(baseQuery);

        query.append("and tpa.\"ID_NASABAH\" = :idNasabah ");
        parameterSource.addValue("idNasabah", request.getExtraParam().getIdNasabah());

        query.append(" order by :sortCol desc");
        parameterSource.addValue("sortCol", request.getSortCol()+1);
//        parameterSource.addValue("sortDir", request.getSortDir());

        query.append(" limit :limit offset :offset");
        parameterSource.addValue("limit", request.getLength());
        parameterSource.addValue("offset", request.getStart());

        return jdbcTemplate.query(query.toString(), parameterSource, new BeanPropertyRowMapper<>(PinjamanDTO.PinjamanApproval.class));
    }


    public void delete(Long id){
        String query = "delete from \"TN_PINJAMAN_APPROVAL\" where \"ID\" = :id";

        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("id", id);

        jdbcTemplate.update(query, parameterSource);
    }

    public Long datatablesCountTolak(DataTableRequest<PinjamanDTO.PinjamanTolak> request){
        String baseQuery = "select count(*) as row_count " +
                "from \"TN_PINJAMAN_TOLAK\" tpt join \"TN_TRANSAKSI\" tt on tpt.\"ID_TRANSAKSI\" = tt.\"ID\" \n" +
                "join \"NASABAH\" n on tpt.\"ID_NASABAH\" = n.\"ID_BACKUP\" where 1 = 1 ";

        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        StringBuilder query = new StringBuilder(baseQuery);

        query.append(" and tpt.\"ID_NASABAH\" = :idNasabah ");
        parameterSource.addValue("idNasabah", request.getExtraParam().getIdNasabah());

        query.append(" order by :sortCol desc");
        parameterSource.addValue("sortCol", request.getSortCol()+1);
//        parameterSource.addValue("sortDir", request.getSortDir());

        query.append(" limit :limit offset :offset");
        parameterSource.addValue("limit", request.getLength());
        parameterSource.addValue("offset", request.getStart());

        return this.jdbcTemplate.queryForObject(
                query.toString(),parameterSource,(resultSet, i) -> resultSet.getLong("row_count")
        );
    }

    public List<PinjamanDTO.PinjamanTolak> datatablesTolak(DataTableRequest<PinjamanDTO.PinjamanTolak> request){
        String baseQuery = "select row_number() over (order by tpt.\"ID\") as no,\n" +
                "tpt.\"ID\" as id,\n" +
                "tpt.\"ID_NASABAH\" as idNasabah,\n" +
                "tpt.\"NOMINAL_TRANSAKSI\" as nominalTransaksi,\n" +
                "tpt.\"BULAN_BAYAR\" as bulanBayar,\n" +
                "tpt.\"TUJUAN_PINJAMAN\" as tujuanPinjam,\n" +
                "tpt.\"ADMIN_TOLAK\" as adminTolak,\n" +
                "tpt.\"TANGGAL_TOLAK\" as tanggalTolak,\n" +
                "tpt.\"ALASAN_TOLAK\" as alasanTolak,\n" +
                "tpt.\"ID_TRANSAKSI\" as idTransaksi,\n" +
                "n.\"NIP\" as nip, \n" +
                "n.\"NAMA_NASABAH\" as namaNasabah\n" +
                "from \"TN_PINJAMAN_TOLAK\" tpt join \"TN_TRANSAKSI\" tt on tpt.\"ID_TRANSAKSI\" = tt.\"ID\" \n" +
                "join \"NASABAH\" n on tpt.\"ID_NASABAH\" = n.\"ID_BACKUP\" where 1 = 1 ";

        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        StringBuilder query = new StringBuilder(baseQuery);

        query.append("and tpt.\"ID_NASABAH\" = :idNasabah ");
        parameterSource.addValue("idNasabah", request.getExtraParam().getIdNasabah());

        query.append(" order by :sortCol desc");
        parameterSource.addValue("sortCol", request.getSortCol()+1);
//        parameterSource.addValue("sortDir", request.getSortDir());

        query.append(" limit :limit offset :offset");
        parameterSource.addValue("limit", request.getLength());
        parameterSource.addValue("offset", request.getStart());

        return jdbcTemplate.query(query.toString(), parameterSource, new BeanPropertyRowMapper<>(PinjamanDTO.PinjamanTolak.class));
    }

    public Long datatablesCount(DataTableRequest<PinjamanDTO.PinjamanTerima> request){
        String baseQuery = "select count(*) as row_count " +
                "from \"TN_PINJAMAN\" tp join \"TN_PINJAMAN_TRANSAKSI\" tpt on tp.\"ID_TRANSAKSI\" = tpt.\"ID\" \n" +
                "join \"NASABAH\" n on tp.\"ID_NASABAH\" = n.\"ID_BACKUP\" where 1 = 1 ";

        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        StringBuilder query = new StringBuilder(baseQuery);

        query.append(" and tp.\"ID_NASABAH\" = :idNasabah ");
        parameterSource.addValue("idNasabah", request.getExtraParam().getIdNasabah());

        query.append(" order by :sortCol desc");
        parameterSource.addValue("sortCol", request.getSortCol()+1);
//        parameterSource.addValue("sortDir", request.getSortDir());

        query.append(" limit :limit offset :offset");
        parameterSource.addValue("limit", request.getLength());
        parameterSource.addValue("offset", request.getStart());

        return this.jdbcTemplate.queryForObject(
                query.toString(),parameterSource,(resultSet, i) -> resultSet.getLong("row_count")
        );
    }

    public List<PinjamanDTO.PinjamanTerima> datatables(DataTableRequest<PinjamanDTO.PinjamanTerima> request){
        String baseQuery = "select  row_number() over (order by tp.\"ID\") as no,\n" +
                "tp.\"ID\" as id,\n" +
                "tp.\"ID_NASABAH\" as idNasabah,\n" +
                "tp.\"TOTAL_PINJAMAN\" as totalPinjaman,\n" +
                "tp.\"SISA_PINJAMAN\" as sisaPinjaan,\n" +
                "tp.\"BULAN_BAYAR\" as bulanBayar,\n" +
                "tp.\"SISA_BULAN_BAYAR\" as sisaBulanBayar,\n" +
                "tp.\"ID_APPROVAL\" as idApproval,\n" +
                "tp.\"TANGGAL_APPROVAL\" as tanggalApprove,\n" +
                "tp.\"ADMIN_APPROVE\" as adminApprove,\n" +
                "tp.\"ID_TRANSAKSI\" as idTransaksi,\n" +
                "tp.\"TUJUAN_PINJAM\" as tujuanPinjam,\n" +
                "n.\"NIP\" as nip,\n" +
                "n.\"NAMA_NASABAH\" as namaNasabah\n" +
                "from \"TN_PINJAMAN\" tp join \"TN_PINJAMAN_TRANSAKSI\" tpt on tp.\"ID_TRANSAKSI\" = tpt.\"ID\" \n" +
                "join \"NASABAH\" n on tp.\"ID_NASABAH\" = n.\"ID_BACKUP\" where 1 = 1 ";

        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        StringBuilder query = new StringBuilder(baseQuery);

        query.append("and tp.\"ID_NASABAH\" = :idNasabah ");
        parameterSource.addValue("idNasabah", request.getExtraParam().getIdNasabah());

        query.append(" order by :sortCol desc");
        parameterSource.addValue("sortCol", request.getSortCol()+1);
//        parameterSource.addValue("sortDir", request.getSortDir());

        query.append(" limit :limit offset :offset");
        parameterSource.addValue("limit", request.getLength());
        parameterSource.addValue("offset", request.getStart());

        return jdbcTemplate.query(query.toString(), parameterSource, new BeanPropertyRowMapper<>(PinjamanDTO.PinjamanTerima.class));
    }

}
