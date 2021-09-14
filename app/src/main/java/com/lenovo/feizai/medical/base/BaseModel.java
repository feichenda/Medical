package com.lenovo.feizai.medical.base;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author feizai
 * @date 12/31/2020 031 11:45:15 AM
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseModel<T> {
    private Integer code;//默认成功的时候为200
    private String msg;
    private T data;
    private List<T> datas;
}

