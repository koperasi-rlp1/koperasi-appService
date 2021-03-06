package com.auth.koperasi.service.service.master;

import com.auth.koperasi.service.dao.NasabahDao;
import com.auth.koperasi.service.dto.NasabahDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class NasabahService {

    @Autowired
    private NasabahDao dao;


    @Value("${file.path.daftar}")
    private String basePath;

    public NasabahDTO.NasabahDaftar save(NasabahDTO.NasabahDaftar value) throws SQLException{
        String id = UUID.randomUUID().toString();
        value.setIdBackup(id);
        value.setIdStatusKeanggotaan(1);
        return dao.save(value);
    }

    public String checkNasabahPinjam(NasabahDTO.NasabahDaftar value) throws SQLException{
        Optional<NasabahDTO.DataNasabah> data = dao.getDataNasabahByNip(value.getNip());
        LocalDate localDate = LocalDate.now();
        LocalDateTime localDateTime = data.get().getCreatedDate().toLocalDateTime();
        if(localDate.getYear() > localDateTime.getYear()){
            if(localDate.getMonthValue() >= localDateTime.getMonthValue()){
                if(localDate.getDayOfMonth() >= localDateTime.getDayOfMonth()){
                    value.setIdStatusKeanggotaan(5);
                    dao.update(value);
                    return "Nasabah Telah Memenuhi Syarat Untuk Melakukan Pinjaman";
                }
            }
        }
        return "Akun Harus Melewati Tahun Pertama Untuk Melakukan Pinjaman";
    }

    public Optional<NasabahDTO.DataNasabah> getDataNasabahByNip(String nip) throws EmptyResultDataAccessException{
        return dao.getDataNasabahByNip(nip);
    }

    public NasabahDTO.Message checkingNipOrUser(String nip, String username){
        List<NasabahDTO.DataNasabah> testNip = dao.getNip(nip);
        List<NasabahDTO.NasabahDaftar> testUserName = dao.getUserName(username);
        NasabahDTO.Message message = new NasabahDTO.Message();
        if(!testNip.isEmpty()){
            message.setMessage("NIP Sudah digunakan");
            return message;
        } else if(!testUserName.isEmpty()){
            message.setMessage("Username Sudah Digukanan");
            return message;
        } else {
            message.setMessage("Data Unique");
            return message;
        }
    }

    public Boolean checkNipRegistered(String nip){
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

    public void naikFase(NasabahDTO.NasabahDaftar value) throws DataAccessException{
        dao.update(value);
    }

    public String sendVerif(MultipartFile file){
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

}
