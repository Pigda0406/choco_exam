package com.martin.choco.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class Drama implements Parcelable {

    /**
     "drama_id": 1,
     "name": "致我們單純的小美好",
     "total_views": 23562274,
     "created_at": "2017-11-23T02:04:39.000Z",
     "thumb": "https://i.pinimg.com/originals/61/d4/be/61d4be8bfc29ab2b6d5cab02f72e8e3b.jpg",
     "rating": 4.4526
     */
    @SerializedName("drama_id")
    @Expose
    private Integer dramaId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("total_views")
    @Expose
    private Integer totalViews;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("thumb")
    @Expose
    private String thumb;
    @SerializedName("rating")
    @Expose
    private float rating;

    public Drama(Parcel in) {
        if (in.readByte() == 0) {
            dramaId = null;
        } else {
            dramaId = in.readInt();
        }
        name = in.readString();
        if (in.readByte() == 0) {
            totalViews = null;
        } else {
            totalViews = in.readInt();
        }
        createdAt = in.readString();
        thumb = in.readString();
        rating = in.readFloat();
    }

    public static final Creator<Drama> CREATOR = new Creator<Drama>() {
        @Override
        public Drama createFromParcel(Parcel in) {
            return new Drama(in);
        }

        @Override
        public Drama[] newArray(int size) {
            return new Drama[size];
        }
    };

    public Integer getDramaId() {
        return dramaId;
    }

    public void setDramaId(Integer dramaId) {
        this.dramaId = dramaId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getTotalViews() {
        return totalViews;
    }

    public void setTotalViews(Integer totalViews) {
        this.totalViews = totalViews;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        if (dramaId == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(dramaId);
        }
        parcel.writeString(name);
        if (totalViews == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(totalViews);
        }
        parcel.writeString(createdAt);
        parcel.writeString(thumb);
        parcel.writeFloat(rating);
    }
}
