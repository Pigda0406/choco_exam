package com.martin.choco.netutils;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;


import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.martin.choco.utils.CompressUtils;
import com.orhanobut.logger.Logger;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import javax.net.ssl.SSLHandshakeException;

import io.reactivex.observers.DisposableObserver;
import okhttp3.ResponseBody;
import retrofit2.HttpException;


public class OnSuccessAndFaultSub extends DisposableObserver<ResponseBody> implements ProgressCancelListener {

    private boolean showProgress = true;
    private OnSuccessAndFaultListener mOnSuccessAndFaultListener;

    private Context context;
    private ProgressDialog progressDialog;

    public OnSuccessAndFaultSub(OnSuccessAndFaultListener mOnSuccessAndFaultListener) {
        this.mOnSuccessAndFaultListener = mOnSuccessAndFaultListener;
    }


    public OnSuccessAndFaultSub(OnSuccessAndFaultListener mOnSuccessAndFaultListener, Context context) {
        this.mOnSuccessAndFaultListener = mOnSuccessAndFaultListener;
        this.context = context;
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Please Wait~");
    }

    public OnSuccessAndFaultSub(OnSuccessAndFaultListener mOnSuccessAndFaultListener, Context context, boolean showProgress) {
        this.mOnSuccessAndFaultListener = mOnSuccessAndFaultListener;
        this.context = context;
        progressDialog = new ProgressDialog(context);
        this.showProgress = showProgress;
    }


    private void showProgressDialog() {
        if (showProgress && null != progressDialog) {
            progressDialog.show();
        }
    }


    private void dismissProgressDialog() {
        if (showProgress && null != progressDialog) {
            progressDialog.dismiss();
        }
    }

    @Override
    public void onStart() {
        showProgressDialog();
    }


    @Override public void onComplete() {
        dismissProgressDialog();
        progressDialog = null;
    }

    @Override
    public void onError(Throwable e) {
        try {
            if (e instanceof SocketTimeoutException) {//请求超时
            } else if (e instanceof ConnectException) {//网络连接超时
                //                ToastManager.showShortToast("网络连接超时");
                mOnSuccessAndFaultListener.onFault("网络连接超时");
            } else if (e instanceof SSLHandshakeException) {//安全证书异常
                //                ToastManager.showShortToast("安全证书异常");
                mOnSuccessAndFaultListener.onFault("安全证书异常");
            } else if (e instanceof HttpException) {//请求的地址不存在
                int code = ((HttpException) e).code();
                if (code == 504) {
                    //                    ToastManager.showShortToast("网络异常，请检查您的网络状态");
                    mOnSuccessAndFaultListener.onFault("网络异常，请检查您的网络状态");
                } else if (code == 404) {
                    //                    ToastManager.showShortToast("请求的地址不存在");
                    mOnSuccessAndFaultListener.onFault("请求的地址不存在");
                } else {
                    //                    ToastManager.showShortToast("请求失败");
                    mOnSuccessAndFaultListener.onFault("请求失败");
                }
            } else if (e instanceof UnknownHostException) {//域名解析失败
                //                ToastManager.showShortToast("域名解析失败");
                mOnSuccessAndFaultListener.onFault("域名解析失败");
            } else {
                //                ToastManager.showShortToast("error:" + e.getMessage());
                mOnSuccessAndFaultListener.onFault("error:" + e.getMessage());
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        } finally {
            Log.e("OnSuccessAndFaultSub", "error:" + e.getMessage());
            //            mOnSuccessAndFaultListener.onFault("error:" + e.getMessage());
            dismissProgressDialog();
            progressDialog = null;

        }

    }

//    @Override
//    public void onNext(ResponseBody body) {
//        Logger.d(body);
//        Log.e("body", "8888888888888888888888888888888");
//        try {
//            Log.e("body2", body.string());
//
//            //final String result = CompressUtils.decompress(body.byteStream());
//
//            mOnSuccessAndFaultListener.onSuccess(body.string());
//        } catch (Exception e) {
//            Log.e("body", "99999999999999999999999999999999999");
//            e.printStackTrace();
//        }
//    }
@Override
public void onNext(ResponseBody body) {
    try {
        //final String result = CompressUtils.decompress(body.byteStream());
        //Log.e("body", result);

        mOnSuccessAndFaultListener.onSuccess(body);
//TODO 天气接口返回数据格式没有resultCode等公共信息，onNext方法请根据自己公司接口返回的数据来调整
//            JSONObject jsonObject = new JSONObject(result);
//            int resultCode = jsonObject.getInt("ErrorCode");
//            if (resultCode == 1) {
//                mOnSuccessAndFaultListener.onSuccess(result);
//            } else {
//                String errorMsg = jsonObject.getString("ErrorMessage");
//                mOnSuccessAndFaultListener.onFault(errorMsg);
//                Log.e("OnSuccessAndFaultSub", "errorMsg: " + errorMsg);
//            }
    } catch (Exception e) {
        e.printStackTrace();
    }
}

    @Override
    public void onCancelProgress() {
        if (!this.isDisposed()) {
            this.dispose();
        }
    }
}
