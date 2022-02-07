package com.auth.koperasi.service.controller;

import com.auth.koperasi.service.dto.SimpananSukaRelaDTO;
import com.auth.koperasi.service.dto.SimpananWajibDTO;
import com.auth.koperasi.service.entity.datatables.DataTableRequest;
import com.auth.koperasi.service.entity.datatables.DataTableResponse;
import com.auth.koperasi.service.service.SimpananSukaRelaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/simpanan-sukarela")
public class SimpananSukaRelaController {

    @Autowired
    private SimpananSukaRelaService service;

    @PostMapping("/datatables")
    public ResponseEntity<DataTableResponse<SimpananSukaRelaDTO.DataSimpanan>> datatables(
            @RequestBody DataTableRequest<SimpananSukaRelaDTO.DataSimpanan> request
    ){
        return ResponseEntity.ok().body(service.datatables(request));
    }
}
