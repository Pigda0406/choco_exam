package com.martin.choco.utils;

import com.google.gson.Gson;

public class GsonUtils {
    public static Gson gson = new Gson();

    public static <T> T fromJson(String result, Class<T> clazz){
        try{
            if(gson==null){
                gson = new Gson();
            }
            return gson.fromJson(result, clazz);
        }catch (Exception e){

            return null;
        }
    }

    public static String toJson(Object obj){
        if(null == gson){
            gson = new Gson();
        }
        return gson.toJson(obj);
    }
}
