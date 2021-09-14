package com.lenovo.feizai.medical.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author chuchen
 */
@Data
public class ConsumerQuery extends PageQuery  {

    /**
     * 身份证号
     */
    private String identity;

    /**
     * 开始时间
     */
    private String startTime;
    
    /**
     * 结束时间
     */
    private String endTime;
}
