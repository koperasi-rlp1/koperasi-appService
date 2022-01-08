package com.auth.koperasi.service.service;

import com.auth.koperasi.service.dao.NasabahDao;
import com.auth.koperasi.service.dto.NasabahDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class NasabahService {

    @Autowired
    private NasabahDao dao;

    public NasabahDTO.NasabahDaftar save(NasabahDTO.NasabahDaftar value) throws SQLException{
        return dao.save(value);
    }

    public Optional<NasabahDTO.DataNasabah> getDataNasabahByNip(Integer nip) throws EmptyResultDataAccessException{
        return dao.getDataNasabahByNip(nip);
    }

    public Boolean checkNipRegistered(Integer nip){
        List<NasabahDTO.DataNasabah> data = dao.find(nip);
        if(data.isEmpty()){
            return true;
        } else {
            return false;
        }
    }

    public Integer faseSatu(NasabahDTO.NasabahDaftar value) throws SQLException {
        Boolean isChecked = checkNipRegistered(value.getNip());
        if(isChecked == false){
            return 99;
        } else {
            value.setIdStatusKeanggotaan(1);
            dao.save(value);
            return 100;
        }
    }

    public void faseDua(NasabahDTO.NasabahDaftar value) throws DataAccessException{
        value.setIdStatusKeanggotaan(2);
        dao.update(value);
    }

}
