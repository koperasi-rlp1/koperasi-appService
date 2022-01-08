package com.auth.koperasi.service.controller.master;

import com.auth.koperasi.service.dao.master.StatusKeanggotaanDao;
import com.auth.koperasi.service.entity.master.StatusKeanggotaan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(path = "/api/master")
public class StatusKeanggotaanController {

    @Autowired
    private StatusKeanggotaanDao dao;

    @GetMapping(name = "/stats")
    public ResponseEntity<?> findAll(){
        try{
            List<StatusKeanggotaan> data = dao.findAll();
            return ResponseEntity.ok(data);
        } catch (EmptyResultDataAccessException dae) {
            return new ResponseEntity<>(dae.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
