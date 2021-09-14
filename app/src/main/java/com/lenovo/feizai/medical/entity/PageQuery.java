package com.lenovo.feizai.medical.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 分页查询条件
 * </p>
 *
 * @author chuchen
 */
@Data
public class PageQuery implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 当前页码，默认第一页
     */
    private Long pageIndex = 1L;
    /**
     * 每页条数，默认每页 4 条
     */
    private Long pageSize = 4L;
}
