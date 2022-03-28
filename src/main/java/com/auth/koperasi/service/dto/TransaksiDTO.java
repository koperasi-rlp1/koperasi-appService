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
        private String tanggal;
        private Time waktu;
        private Long nominalTransaksi;
        private String idNasabah;
        private String buktiPembayaran;
        private String deskripsi;
        private String statusApproval;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DataSaldoNasabah{
        private String nip;
        private String namaNasabah;
        private Long saldoWajib;
        private Long saldoSukaRela;
        private Long trWajib;
        private Long trSukaRela;
        private Long saldoAkhir;
        private Long simpananPokok;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class DataPinjamanNasabah{
        private String nip;
        private String namaNasabah;
        private Long jumlahPinjaman;
        private Long pinjamanSelesai;
        private Long pinjamanBelumSelesai;
        private Long totalUangPinjam;
        private Long totalUangBayar;
        private Long sisaPinjamBelumBayar;
    }
}
