package com.auth.koperasi.service.controller;

import com.auth.koperasi.service.dto.PinjamanDTO;
import com.auth.koperasi.service.entity.datatables.DataTableRequest;
import com.auth.koperasi.service.entity.datatables.DataTableResponse;
import com.auth.koperasi.service.service.PinjamanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/datatables-approval")
    public ResponseEntity<DataTableResponse<PinjamanDTO.PinjamanApproval>> datatablesApproval(
            @RequestBody DataTableRequest<PinjamanDTO.PinjamanApproval> request
    ){
        return ResponseEntity.ok().body(service.datatablesApproval(request));
    }

    @PostMapping("/datatables-tolak")
    public ResponseEntity<DataTableResponse<PinjamanDTO.PinjamanTolak>> datatablesTolak(
            @RequestBody DataTableRequest<PinjamanDTO.PinjamanTolak> request
    ){
        return ResponseEntity.ok().body(service.datatablesTolak(request));
    }

    @PostMapping("/datatables")
    public ResponseEntity<DataTableResponse<PinjamanDTO.PinjamanTerima>> datatables(
            @RequestBody DataTableRequest<PinjamanDTO.PinjamanTerima> request
    ){
        return ResponseEntity.ok().body(service.datatables(request));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.ok().body(id);
    }
}
