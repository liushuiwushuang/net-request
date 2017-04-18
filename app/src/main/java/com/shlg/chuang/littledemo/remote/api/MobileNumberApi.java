package com.shlg.chuang.littledemo.remote.api;

import com.shlg.chuang.littledemo.entity.MobileNumber;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by Administrator on 2017/4/18.
 * @author magicRain
 */

public interface MobileNumberApi {

    @POST("/register")
    Call<MobileNumber> userRegister(@Body Map<String, Object> params);
}
