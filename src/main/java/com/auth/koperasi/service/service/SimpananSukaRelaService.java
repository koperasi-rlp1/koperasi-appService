package com.auth.koperasi.service.service;

import com.auth.koperasi.service.dao.SimpananSukaRelaDao;
import com.auth.koperasi.service.dto.SimpananSukaRelaDTO;
import com.auth.koperasi.service.dto.SimpananWajibDTO;
import com.auth.koperasi.service.entity.datatables.DataTableRequest;
import com.auth.koperasi.service.entity.datatables.DataTableResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SimpananSukaRelaService {

    @Autowired
    private SimpananSukaRelaDao dao;

    public DataTableResponse<SimpananSukaRelaDTO.DataSimpanan> datatables(DataTableRequest<SimpananSukaRelaDTO.DataSimpanan> request){
        DataTableResponse<SimpananSukaRelaDTO.DataSimpanan> data = new DataTableResponse<>();
        data.setData(dao.datatables(request));
        data.setRecordTotal(dao.datatablesCount(request));
        data.setRecordFiltered(dao.datatablesCount(request));
        data.setDraw(request.getDraw());
        return data;
    }
}
