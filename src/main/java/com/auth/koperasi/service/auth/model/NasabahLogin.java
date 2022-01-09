package com.auth.koperasi.service.auth.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NasabahLogin {

    private String username;
    private String nip;
    private String password;
}
