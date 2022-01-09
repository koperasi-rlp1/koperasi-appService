package com.auth.koperasi.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.util.Date;

public class TransaksiDTO {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class DataTransaksi{
        private Integer id;
        private Integer idApproval;
        private String jenisTransaksi;
        private Date tanggal;
        private Time waktu;
        private Long nominalTransaksi;
        private String idNasabah;
        private String buktiPembayaran;
        private String deskripsi;
        private String statusApproval;
    }
}
