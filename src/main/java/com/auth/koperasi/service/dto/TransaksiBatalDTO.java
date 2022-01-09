package com.auth.koperasi.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.util.Date;

public class TransaksiBatalDTO {

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DataTransaksiBatal{

        private Integer idTransaksi;
        private Date tanggal;
        private Time waktu;
        private String deskripsi;
    }

}
