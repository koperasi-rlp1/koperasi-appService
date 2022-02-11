package com.auth.koperasi.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.Date;

public class NasabahDTO {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class NasabahDaftar{
        private String nip;
        private String idBackup;
        private String namaNasabah;
        private String jabatan;
        private String email;
        private String noHp;
        private String username;
        private String password;
        private String unitOperasional;
        private Integer idStatusKeanggotaan;
        private String fileBuktiPembayaran;
        private String jenisKelamin;
        private Date tanggalLahir;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class DataNasabah {
        private String nip;
        private String namaNasabah;
        private String idBackup;
        private String email;
        private String noHp;
        private String jabatan;
        private String unitOperasional;
        private Integer idStatusKeanggotaan;
        private String fileBuktiPembayaran;
        private Timestamp createdDate;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Message{
        private String message;
    }

}
