package com.auth.koperasi.service.controller;

import com.auth.koperasi.service.dto.SimpananWajibDTO;
import com.auth.koperasi.service.entity.datatables.DataTableRequest;
import com.auth.koperasi.service.entity.datatables.DataTableResponse;
import com.auth.koperasi.service.service.SimpananWajibService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/simpanan-wajib")
public class SimpananWajibController {

    @Autowired
    private SimpananWajibService service;

    @PostMapping("/datatables")
    public ResponseEntity<DataTableResponse<SimpananWajibDTO.DataSimpanan>> datatables(
            @RequestBody DataTableRequest<SimpananWajibDTO.DataSimpanan> request
    ){
        return ResponseEntity.ok().body(service.datatables(request));
    }
}
