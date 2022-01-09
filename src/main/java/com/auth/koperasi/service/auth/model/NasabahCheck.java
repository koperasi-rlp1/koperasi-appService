package com.auth.koperasi.service.auth.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NasabahCheck {

    private String tokenKey;
    private Timestamp since;
    private String nip;
}
