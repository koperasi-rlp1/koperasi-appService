package com.auth.koperasi.service.service;

import com.auth.koperasi.service.dao.TransaksiApprovalDao;
import com.auth.koperasi.service.dto.TransaksiApprovalDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;



import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;


@Service
public class TransaksiApprovalService {

    @Autowired
    private TransaksiApprovalDao dao;


    @Value("${file.path.approval}")
    private String basePath;


    public TransaksiApprovalDTO.PengajuanSimpanan ajuanSimpanan(TransaksiApprovalDTO.PengajuanSimpanan value){
        LocalDate localDate = LocalDate.now();
        value.setTanggal(localDate);
        return dao.pengajuanSimpanan(value);
    }

    public String uploadFile(MultipartFile file){
        try{
            Path root = Paths.get(basePath);
            String[] fileFrags = file.getOriginalFilename().split("\\.");
            String extension = fileFrags[fileFrags.length - 1];
            String uuid = UUID.randomUUID().toString() + "." + extension;
            Files.copy(file.getInputStream(), root.resolve(uuid));
            return uuid;
        } catch (IOException e){
            throw new RuntimeException("could not store the file. error : " + e.getMessage());
        }
    }

    public Resource load(String fileName){
        try{
            Path root = Paths.get(basePath);
            Path file = root.resolve(fileName);
            Resource resource = new UrlResource(file.toUri());

            if (resource.exists() || resource.isReadable()) {
                return  resource;
            } else {
                throw  new RuntimeException("couldn't found the file");
            }
        } catch (MalformedURLException a){
            throw  new RuntimeException("Cannot show picture");
        }
    }

}
