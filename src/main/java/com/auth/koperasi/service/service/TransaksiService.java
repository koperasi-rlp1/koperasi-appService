package com.auth.koperasi.service.service;

import com.auth.koperasi.service.dao.TransaksiDao;
import com.auth.koperasi.service.dto.SimpananSukaRelaDTO;
import com.auth.koperasi.service.dto.TransaksiDTO;
import com.auth.koperasi.service.entity.datatables.DataTableRequest;
import com.auth.koperasi.service.entity.datatables.DataTableResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class TransaksiService {

    @Autowired
    private TransaksiDao dao;

    public DataTableResponse<TransaksiDTO.DataTransaksi> datatablesApproval(DataTableRequest<TransaksiDTO.DataTransaksi> request){
        DataTableResponse<TransaksiDTO.DataTransaksi> data = new DataTableResponse<>();
        data.setData(dao.datatablesApproval(request));
        System.out.println(data.getData());
        data.setRecordTotal(dao.datatablesApprovalCount(request));
        data.setRecordFiltered(dao.datatablesApprovalCount(request));
        data.setDraw(request.getDraw());
        return data;
    }

    public TransaksiDTO.DataSaldoNasabah findDataSaldoNasabah(String nip) throws EmptyResultDataAccessException{
        return dao.findDataSaldoNasabah(nip);
    }
}
