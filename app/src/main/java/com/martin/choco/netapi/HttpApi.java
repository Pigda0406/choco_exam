package com.martin.choco.netapi;

import com.martin.choco.bean.BaseRequestBean;
import com.martin.choco.bean.DramaResponseBean;

import io.reactivex.Observable;

import okhttp3.ResponseBody;

import retrofit2.http.Body;
import retrofit2.http.GET;


public interface HttpApi {

    @GET("v2/5a97c59c30000047005c1ed2")
    Observable<ResponseBody> getDrama();

}
