package com.lenovo.feizai.medical.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author chuchen
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    private Long id;

    /**
     * 身份证id
     */
    private String identityId;

    /**
     * 登录密码
     */
    private String password;

    /**
     * 权限
     */
    private Integer role;

    /**
     * 电话号码
     */
    private String phoneNumber;

    /**
     * 0-未激活，1-已激活：通过是否创建登录密码判断
     */
    private Integer status;

    /**
     * 创建时间
     */
    private Long gmtCreate;

    /**
     * 修改时间
     */
    private Long gmtModified;


}
