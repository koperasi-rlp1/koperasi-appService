package com.auth.koperasi.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class NasabahDTO {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class NasabahDaftar{
        private Integer nip;
        private String idBackup;
        private String namaNasabah;
        private String jabatan;
        private String email;
        private String noHp;
        private String username;
        private String password;
        private String unitOperasional;
        private String statusKeanggotaan;
        private String idStatusKeanggotaan;
        private String fileBuktiPembayaran;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class DataNasabah {
        private Integer nip;
        private String namaNasabah;
        private String idBackup;
        private String email;
        private String noHp;
        private String jabatan;
        private String unitOperasional;
        private String fileBuktiPembayaran;
    }

}
