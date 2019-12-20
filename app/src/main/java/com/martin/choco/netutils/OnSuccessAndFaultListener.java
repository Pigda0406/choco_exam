package com.martin.choco.netutils;

import org.json.JSONException;

import java.io.IOException;

import okhttp3.ResponseBody;

public interface OnSuccessAndFaultListener {
    void onSuccess(ResponseBody body) throws IOException, JSONException;

    void onFault(String errorMsg);
}
