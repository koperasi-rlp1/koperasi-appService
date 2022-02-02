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
        private Integer noTransaksi;
        private Date tanggal;
        private Time waktu;
        private Long nominalTransaksi;
        private String idNasabah;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class AkumulasiData{
        private String idNasabah;
        private Long nominalSimpananSukaRela;
    }
}
