package com.auth.koperasi.service.controller;

import com.auth.koperasi.service.dto.PinjamanDTO;
import com.auth.koperasi.service.service.PinjamanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.SQLException;

@Controller
@RequestMapping(path = "/api/pinjaman")
public class PinjamanController {

    @Autowired
    private PinjamanService service;

    @PostMapping("/save-approval")
    public ResponseEntity<?> saveApproval(
            @RequestBody PinjamanDTO.PinjamanApproval value
    ){
        try{
            return ResponseEntity.ok().body(service.saveApproval(value));
        } catch (SQLException e){
            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
