package com.auth.koperasi.service.controller;

import com.auth.koperasi.service.dto.TransaksiApprovalDTO;
import com.auth.koperasi.service.service.TransaksiApprovalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(path = "/api/transaksi-approval")
public class TransaksiApprovalController {

    @Autowired
    private TransaksiApprovalService service;

    @PostMapping("/pengajuanSimpanan")
    public ResponseEntity<?> ajuanSimpanan(
            @RequestBody TransaksiApprovalDTO.PengajuanSimpanan value){
        try{
            TransaksiApprovalDTO.PengajuanSimpanan pengajuanSimpanan = service.ajuanSimpanan(value);
            return ResponseEntity.ok().body(pengajuanSimpanan);
        } catch (DataAccessException dae){
            return new ResponseEntity<>(dae.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/filesupload")
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

    @GetMapping(value = "/file/{id}")
    public ResponseEntity<InputStreamResource>getImage(@PathVariable("id") String id){
        try{
            return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(
                    new InputStreamResource( service.load(id).getInputStream() ));
        }catch(IOException ex){
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(null);
        }
    }


}
