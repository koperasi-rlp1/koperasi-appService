package com.auth.koperasi.service.controller.master;

import com.auth.koperasi.service.dao.master.JabatanDao;
import com.auth.koperasi.service.dao.master.StatusKeanggotaanDao;
import com.auth.koperasi.service.entity.master.Jabatan;
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
@RequestMapping(path = "/api/jabatan")
public class JabatanController {

    @Autowired
    private JabatanDao dao;

    @GetMapping("/findAll")
    public ResponseEntity<?> findAll(){
        try{
            List<Jabatan> data = dao.findAll();
            return ResponseEntity.ok(data);
        } catch (EmptyResultDataAccessException dae) {
            return new ResponseEntity<>(dae.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
