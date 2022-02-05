package com.auth.koperasi.service.entity.datatables;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DataTableRequest<T> {

    private Integer draw, start, length, sortCol;
    private T extraParam;
    private String sortDir;
}
