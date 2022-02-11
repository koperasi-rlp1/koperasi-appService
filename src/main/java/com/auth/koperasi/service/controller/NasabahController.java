package com.auth.koperasi.service.controller;

import com.auth.koperasi.service.dto.NasabahDTO;
import com.auth.koperasi.service.service.master.NasabahService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/api/nasabah-procedure")
public class NasabahController {

    @Autowired
    private NasabahService service;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody NasabahDTO.NasabahDaftar value){
        try {
            NasabahDTO.NasabahDaftar response = service.save(value);
            return ResponseEntity.ok(response);
        } catch (SQLException dae) {
            return new ResponseEntity<>(dae.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/checking/{nip}/{username}")
    public ResponseEntity<?> checkNip(@PathVariable("nip") String nip, @PathVariable("username") String username){
        try{
            NasabahDTO.Message response = service.checkingNipOrUser(nip, username);
            return ResponseEntity.ok(response);
        } catch (EmptyResultDataAccessException e){
            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/verifikasiPembayaran")
    public ResponseEntity<Map<String, Object>> uploadFile(@RequestParam("file") MultipartFile file) {
        String message = null;
        Map<String, Object> pesan = new HashMap<>();
        try {
            String namaFile = service.uploadFile(file);
            pesan.put("file", namaFile);
            return ResponseEntity.ok().body(pesan);
        } catch (Exception exception) {
            pesan.put("pesan", "cannot input file");
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(pesan);
        }
    }

    @PutMapping("/updateFaseRegistrasi")
    public ResponseEntity<?> faseDua(@RequestBody NasabahDTO.NasabahDaftar value){
        try {
            service.naikFase(value);
            Optional<NasabahDTO.DataNasabah> data = service.getDataNasabahByNip(value.getNip());
            return ResponseEntity.ok(data);
        } catch (DataAccessException dae) {
            return new ResponseEntity<>(dae.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(name = "/getDataByNip")
    public ResponseEntity<?> getDataByNip(@RequestParam String nip){
        try{
            Optional<NasabahDTO.DataNasabah> data = service.getDataNasabahByNip(nip);
            return ResponseEntity.ok(data);
        } catch (EmptyResultDataAccessException dae) {
            return new ResponseEntity<>(dae.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
