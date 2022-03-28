package com.auth.koperasi.service.service;

import com.auth.koperasi.service.dao.PinjamanDao;
import com.auth.koperasi.service.dto.PinjamanDTO;
import com.auth.koperasi.service.dto.SimpananSukaRelaDTO;
import com.auth.koperasi.service.entity.datatables.DataTableRequest;
import com.auth.koperasi.service.entity.datatables.DataTableResponse;
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

    public DataTableResponse<PinjamanDTO.PinjamanApproval> datatablesApproval(DataTableRequest<PinjamanDTO.PinjamanApproval> request){
        DataTableResponse<PinjamanDTO.PinjamanApproval> data = new DataTableResponse<>();
        data.setData(dao.datatablesApproval(request));
        data.setRecordTotal(dao.datatablesCountApproval(request));
        data.setRecordFiltered(dao.datatablesCountApproval(request));
        data.setDraw(request.getDraw());
        return data;
    }

    public DataTableResponse<PinjamanDTO.PinjamanTolak> datatablesTolak(DataTableRequest<PinjamanDTO.PinjamanTolak> request){
        DataTableResponse<PinjamanDTO.PinjamanTolak> data = new DataTableResponse<>();
        data.setData(dao.datatablesTolak(request));
        data.setRecordTotal(dao.datatablesCountTolak(request));
        data.setRecordFiltered(dao.datatablesCountTolak(request));
        data.setDraw(request.getDraw());
        return data;
    }

    public DataTableResponse<PinjamanDTO.PinjamanTerima> datatables(DataTableRequest<PinjamanDTO.PinjamanTerima> request){
        DataTableResponse<PinjamanDTO.PinjamanTerima> data = new DataTableResponse<>();
        data.setData(dao.datatables(request));
        data.setRecordTotal(dao.datatablesCount(request));
        data.setRecordFiltered(dao.datatablesCount(request));
        data.setDraw(request.getDraw());
        return data;
    }

    public void delete(Long id){
        dao.delete(id);
    }

}
