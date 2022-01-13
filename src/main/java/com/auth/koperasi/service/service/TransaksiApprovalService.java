package com.auth.koperasi.service.service;

import com.auth.koperasi.service.dao.TransaksiApprovalDao;
import com.auth.koperasi.service.dto.TransaksiApprovalDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransaksiApprovalService {

    @Autowired
    private TransaksiApprovalDao dao;

    public TransaksiApprovalDTO.PengajuanSimpanan ajuanSimpanan(TransaksiApprovalDTO.PengajuanSimpanan value){
        return dao.pengajuanSimpanan(value);
    }
}
