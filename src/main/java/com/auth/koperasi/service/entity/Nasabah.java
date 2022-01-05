package com.auth.koperasi.service.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Nasabah {

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

}
