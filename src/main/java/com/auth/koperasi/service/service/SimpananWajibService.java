package com.auth.koperasi.service.service;

import com.auth.koperasi.service.dao.SimpananWajibDao;
import com.auth.koperasi.service.dto.SimpananWajibDTO;
import com.auth.koperasi.service.entity.datatables.DataTableRequest;
import com.auth.koperasi.service.entity.datatables.DataTableResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SimpananWajibService {

    @Autowired
    private SimpananWajibDao dao;

    public DataTableResponse<SimpananWajibDTO.DataSimpanan> datatables(DataTableRequest<SimpananWajibDTO.DataSimpanan> request){
        DataTableResponse<SimpananWajibDTO.DataSimpanan> data = new DataTableResponse<>();
        data.setData(dao.datatables(request));
        data.setRecordTotal(dao.datatablesCount(request));
        data.setRecordFiltered(dao.datatablesCount(request));
        data.setDraw(request.getDraw());
        return data;
    }
}
