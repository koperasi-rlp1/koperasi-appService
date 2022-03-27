package com.auth.koperasi.service.service;

import com.auth.koperasi.service.dao.PinjamanDao;
import com.auth.koperasi.service.dto.PinjamanDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public class PinjamanService {

    @Autowired
    private PinjamanDao dao;

    public int saveApproval(PinjamanDTO.PinjamanApproval value) throws SQLException {
        return dao.saveApproval(value);
    }
}
