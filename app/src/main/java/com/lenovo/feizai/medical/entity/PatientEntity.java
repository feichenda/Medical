package com.lenovo.feizai.medical.entity;

import android.util.Base64;

import cn.hutool.crypto.digest.DigestUtil;

import com.google.gson.Gson;
import com.lenovo.feizai.medical.util.CreateSecretKey;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.io.Serializable;

/**
 * 接收病人信息的实体
 *
 * @author chuchen
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    private Long id;
    /**
     * 身份ID
     */
    private String identityId;

    /**
     * 姓名
     */

    private String username;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 性别：0表示女生，1表示男生
     */
    private Integer sex;

    /**
     * 电话号码
     */
    private String phoneNumber;

    /**
     * 看病日期:时间戳型
     */
    private Long  time;

    /**
     * 看病日期:string型
     */
    private String timeStamp;

    /**
     * 主治医生姓名
     */
    private String doctorName;
    /**
     * 0-无效，1-有效
     */
    private Integer status;

    /**
     * 数据签名
     */
    private byte[] signature;


    private Long gmtCreate;

    private Long gmtModified;

    /**
     * 消息加密，生成数据签名
     * @param publicKey 公钥
     * @param privateKey 私钥
     * @return 加密后的数据
     * @throws Exception
     */
//    public  String cipherText(String publicKey, String privateKey) throws Exception {
//        Gson gson = new Gson();
//        byte[] cipherText;
//        String hash= DigestUtil.sha256Hex(this.id+this.identityId+this.username+this.age+this.sex+this.phoneNumber+this.status
//                +this.gmtCreate+this.gmtModified+this.time+this.timeStamp+this.doctorName);
//        System.out.println("得到的数据摘要为------------》"+ hash);
//        this.signature = CreateSecretKey.sign(hash.getBytes(), privateKey);
//        String base64 = CreateSecretKey.encryptBASE64(signature);
//        System.out.println("数据签名为->" + base64);
//        String json  = gson.toJson(this);
//        cipherText = CreateSecretKey.encrypt(json.getBytes(), publicKey);
//        return Base64.encodeToString(cipherText);
//    }
//
//    public String getMessageHash() {
//        return DigestUtil.sha256Hex(this.id+this.identityId+this.username+this.age+this.sex+this.phoneNumber+this.status
//                +this.gmtCreate+this.gmtModified+this.time+this.timeStamp+this.doctorName);
//    }
}
