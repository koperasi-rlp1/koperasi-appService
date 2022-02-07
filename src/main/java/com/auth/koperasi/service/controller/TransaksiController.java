package com.auth.koperasi.service.controller;

import com.auth.koperasi.service.dto.SimpananSukaRelaDTO;
import com.auth.koperasi.service.dto.TransaksiDTO;
import com.auth.koperasi.service.entity.datatables.DataTableRequest;
import com.auth.koperasi.service.entity.datatables.DataTableResponse;
import com.auth.koperasi.service.service.TransaksiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/transaksi")
public class TransaksiController {

    @Autowired
    private TransaksiService service;

    @PostMapping("/datatables-approval")
    public ResponseEntity<DataTableResponse<TransaksiDTO.DataTransaksi>> datatables(
            @RequestBody DataTableRequest<TransaksiDTO.DataTransaksi> request
    ){
        return ResponseEntity.ok().body(service.datatablesApproval(request));
    }
}
