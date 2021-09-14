package com.lenovo.feizai.medical.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 密码登录信息
 * </p>
 *
 * @author chuchen
 */

@Data
public class PasswordLoginQuery implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 身份证id
     */
    private String identityId;

    /**
     * 登录密码
     */
    private String password;

    /**
     * 验证码
     */
    private String code;

    /**
     * redis中的key
     */
    private String tage;

}
