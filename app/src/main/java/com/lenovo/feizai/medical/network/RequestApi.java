package com.lenovo.feizai.medical.network;

import com.lenovo.feizai.medical.base.BaseModel;
import com.lenovo.feizai.medical.entity.ConsumerQuery;
import com.lenovo.feizai.medical.entity.LoginEntity;
import com.lenovo.feizai.medical.entity.PasswordLoginQuery;
import com.lenovo.feizai.medical.entity.PatientEntity;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * @author feizai
 * @date 2021/6/13 0013 PM 2:44:37
 */
public interface RequestApi {
    public final static String BASE_URL = "http://172.21.58.82:8088/";
    public final static String BASE_IMAGE_URL = "http://172.21.58.82:8088/";

    @POST("api/hospital/passwordLogin/verify/passwordLogin")
    Observable<BaseModel<LoginEntity>> password(@Body PasswordLoginQuery passwordQuery);

    @POST("api/hospital/consumer/queryPatient")
    Observable<BaseModel<PatientEntity>> queryPatient(@Body ConsumerQuery consumerQuery);
}
