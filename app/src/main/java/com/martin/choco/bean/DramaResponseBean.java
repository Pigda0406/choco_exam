package com.martin.choco.bean;

import android.support.annotation.NonNull;

import com.google.gson.JsonArray;
import com.google.gson.annotations.SerializedName;
import com.martin.choco.model.Drama;

import java.util.ArrayList;
import java.util.List;

public class DramaResponseBean {

    @SerializedName("data")
    private ArrayList<Data> dataList;

    public ArrayList<Data> getDataList() {
        return dataList;
    }


//    private JsonArray data;
//    public DramaResponseBean(JsonArray data) {
//        this.data = data;
//    }
//    public JsonArray getData() {
//        return data;
//    }
//
//    @Override
//    public String toString() {
//        return "WeatherResponseBean{" +
//                ", data=" + data.toString() +
//                '}';
//    }
    public static class Data {
        /**
         /**
         "drama_id": 1,
         "name": "致我們單純的小美好",
         "total_views": 23562274,
         "created_at": "2017-11-23T02:04:39.000Z",
         "thumb": "https://i.pinimg.com/originals/61/d4/be/61d4be8bfc29ab2b6d5cab02f72e8e3b.jpg",
         "rating": 4.4526
         */
        private int drama_id;
        private String name;
        private double total_views;
        private String created_at;
        private String thumb;
        private double rating;

        public String getThumb() {
            return thumb;
        }

        public String getName() {
            return name;
        }

        public String getCreated_at() {
            return created_at;
        }

        public int getDrama_id() {
            return drama_id;
        }

        public double getTotal_views() {
            return total_views;
        }

        public double getRating() {
            return rating;
        }

                @Override
        public String toString() {
            return "DataBean{" +
                    "drama_id='" + drama_id + '\'' +
                    ", name='" + name + '\'' +
                    ", total_views='" + total_views + '\'' +
                    ", created_at='" + created_at + '\'' +
                    ", thumb='" + thumb + '\'' +
                    ", rating=" + rating +
                    '}';
        }
    }

}
