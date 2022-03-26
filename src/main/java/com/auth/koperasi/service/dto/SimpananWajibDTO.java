package com.auth.koperasi.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.util.Date;

public class SimpananWajibDTO {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class DataSimpanan{
        private Integer no;
        private Long id;
        private Long noTransaksi;
        private String tanggal;
        private Long nominalTransaksi;
        private String idNasabah;
        private String deskripsi;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class AkumulasiData{
        private String idNasabah;
        private Long nominalSimpananSukaRela;
    }
}
