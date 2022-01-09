package com.auth.koperasi.service.auth.model;

import com.auth.koperasi.service.dto.NasabahDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NasabahResponse {

    private String responseMessage;
    private String token;
    private Boolean isValid;
    private NasabahDTO.DataNasabah dataNasabah;
}
