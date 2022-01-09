package com.auth.koperasi.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.util.Date;

public class TransaksiApprovalDTO {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class PengajuanSimpanan{
        private String jenisTransaksi;
        private Long nominalTransaksi;
        private String idNasabah;
        private String buktiPembayaran;
        private String deskripsi;
    }
}
