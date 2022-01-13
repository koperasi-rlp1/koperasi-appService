package com.auth.koperasi.service.controller;

import com.auth.koperasi.service.dto.TransaksiApprovalDTO;
import com.auth.koperasi.service.service.TransaksiApprovalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/api/transaksi-approval")
public class TransaksiApprovalController {

    @Autowired
    private TransaksiApprovalService service;

    @PostMapping("/pengajuanSimpanan")
    public ResponseEntity<?> ajuanSimpanan(@RequestBody TransaksiApprovalDTO.PengajuanSimpanan value ){
        try{
            TransaksiApprovalDTO.PengajuanSimpanan pengajuanSimpanan = service.ajuanSimpanan(value);
            return ResponseEntity.ok().body(pengajuanSimpanan);
        } catch (DataAccessException dae){
            return new ResponseEntity<>(dae.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
