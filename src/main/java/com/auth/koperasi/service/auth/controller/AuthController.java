package com.auth.koperasi.service.auth.controller;

import com.auth.koperasi.service.auth.dao.Login;
import com.auth.koperasi.service.auth.model.NasabahCheck;
import com.auth.koperasi.service.auth.model.NasabahLogin;
import com.auth.koperasi.service.auth.model.NasabahResponse;
import com.auth.koperasi.service.dto.NasabahDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;

@Controller
@RequestMapping(path = "/api/auth")
public class AuthController {

    @Autowired
    private Login login;

    @PostMapping("/login")
    public ResponseEntity<NasabahResponse> login(@RequestBody NasabahLogin nasabahLogin) throws Exception {
        NasabahResponse nasabahResponse = new NasabahResponse();
        NasabahDTO.DataNasabah dataNasabah = new NasabahDTO.DataNasabah();
        if (nasabahLogin != null) {
            String username = nasabahLogin.getUsername();
            NasabahLogin useradmindb = login.getUserByNipOrName(username);
            System.out.println(useradmindb);
            if (useradmindb != null) {
                if (Objects.equals(username, useradmindb.getUsername()) || Objects.equals(username, useradmindb.getNip())){
                    String password = nasabahLogin.getPassword();
                    if (Objects.equals(password, useradmindb.getPassword())) {
                        List<NasabahCheck> check = login.checkLogin(useradmindb.getNip());
                        if (check.isEmpty()) {
                            dataNasabah = login.getDataNasabahByNip(useradmindb.getNip());
                            if (dataNasabah.getIdStatusKeanggotaan() >= 4) {
                                String token = UUID.randomUUID().toString();
                                nasabahResponse.setIsValid(true);
                                nasabahResponse.setToken(token);
                                nasabahResponse.setResponseMessage("Login Sebagai " + dataNasabah.getNamaNasabah() + " Berhasil");
                                nasabahResponse.setDataNasabah(dataNasabah);
                                NasabahCheck inputCheck = new NasabahCheck();
                                inputCheck.setTokenKey(token);
                                inputCheck.setNip(dataNasabah.getNip());
                                login.addInfo(inputCheck);
                            } else {
                                nasabahResponse.setResponseMessage("Akun Belum Terdaftar Atau Sedang Menunggu Konfirmasi");
                            }
                        } else {
                            nasabahResponse.setResponseMessage("Akun Sedang Digunakan di Perangkat Lain");
                        }
                    } else {
                        nasabahResponse.setResponseMessage("Password yang Anda Masukkan Salah");
                    }
                } else {
                    nasabahResponse.setResponseMessage("Username yang Anda Input Tidak Valid");
                }
            } else {
                nasabahResponse.setResponseMessage("User Tidak Ditemukan");
            }
        } else {
            nasabahResponse.setResponseMessage("User Tidak Ditemukan");
        }
        return ResponseEntity.ok().body(nasabahResponse);
    }


}
