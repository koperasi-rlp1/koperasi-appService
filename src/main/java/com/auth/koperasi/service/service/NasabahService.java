package com.auth.koperasi.service.service;

import com.auth.koperasi.service.dao.NasabahDao;
import com.auth.koperasi.service.dto.NasabahDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Optional;

@Service
public class NasabahService {

    @Autowired
    private NasabahDao dao;

    public NasabahDTO.NasabahDaftar save(NasabahDTO.NasabahDaftar value) throws SQLException{
        return dao.save(value);
    }

    public Optional<NasabahDTO.DataNasabah> getDataNasabahByNip(Integer nip) throws EmptyResultDataAccessException{
        return dao.getDataNasabahByNip(nip);
    }

}
