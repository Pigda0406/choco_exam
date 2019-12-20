package com.martin.choco.netsubscribe;


import com.martin.choco.netutils.RetrofitFactory;

import io.reactivex.Observable;
import io.reactivex.observers.DisposableObserver;

import okhttp3.ResponseBody;


public class DramaSubscribe {


    public static void getDrama(DisposableObserver<ResponseBody> subscriber) {
        Observable<ResponseBody> observable =  RetrofitFactory.getInstance().getHttpApi().getDrama();
        RetrofitFactory.getInstance().toSubscribe(observable, subscriber);
    }


}
