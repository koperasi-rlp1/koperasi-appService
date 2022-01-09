package com.auth.koperasi.service.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SimpananSukaRela {

    private Integer id;
    private Integer idTransaksi;
    private Date tanggal;
    private Time waktu;
    private Long nominalTransaksi;
    private String idNasabah;
}
