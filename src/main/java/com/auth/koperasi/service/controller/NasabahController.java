package com.auth.koperasi.service.controller;

import com.auth.koperasi.service.dto.NasabahDTO;
import com.auth.koperasi.service.service.NasabahService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.Optional;

@RestController
@RequestMapping("/api/nasabah-procedure")
public class NasabahController {

    @Autowired
    private NasabahService service;

    @PostMapping(name = "/save")
    public ResponseEntity<?> save(@RequestBody NasabahDTO.NasabahDaftar value){
        try {
            NasabahDTO.NasabahDaftar save = service.save(value);
            return ResponseEntity.ok(save);
        } catch (SQLException dae) {
            return new ResponseEntity<>(dae.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(name = "/faseSatu")
    public ResponseEntity<?> faseSatu(@RequestBody NasabahDTO.NasabahDaftar value){
        try {
            Integer response = service.faseSatu(value);
            return ResponseEntity.ok(response);
        } catch (SQLException dae) {
            return new ResponseEntity<>(dae.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(name = "/faseDua")
    public ResponseEntity<?> faseDua(@RequestBody NasabahDTO.NasabahDaftar value){
        try {
            service.faseDua(value);
            Optional<NasabahDTO.DataNasabah> data = service.getDataNasabahByNip(value.getNip());
            return ResponseEntity.ok(data);
        } catch (DataAccessException dae) {
            return new ResponseEntity<>(dae.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(name = "/getDataByNip")
    public ResponseEntity<?> getDataByNip(@RequestParam Integer nip){
        try{
            Optional<NasabahDTO.DataNasabah> data = service.getDataNasabahByNip(nip);
            return ResponseEntity.ok(data);
        } catch (EmptyResultDataAccessException dae) {
            return new ResponseEntity<>(dae.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
