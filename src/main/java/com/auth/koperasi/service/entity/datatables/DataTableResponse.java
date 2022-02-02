package com.auth.koperasi.service.entity.datatables;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataTableResponse {

    private List<?> data;
    private Integer draw;
    private Integer recordFiltered, recordTotal;
}
