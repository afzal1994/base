package com.example.baseproject.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Base64;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

@Entity
public class MovieListBean implements Parcelable {

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "bannerUrl")
    private String bannerUrl;

    @ColumnInfo(name = "description")
    private String description;

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
    private byte[] image;

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    protected MovieListBean(Parcel in) {
        name = in.readString();
        bannerUrl = in.readString();
        description = in.readString();
        id = in.readInt();
        image = in.createByteArray();
    }

    MovieListBean() {

    }

    public static final Creator<MovieListBean> CREATOR = new Creator<MovieListBean>() {
        @Override
        public MovieListBean createFromParcel(Parcel in) {
            return new MovieListBean(in);
        }

        @Override
        public MovieListBean[] newArray(int size) {
            return new MovieListBean[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBannerUrl() {
        return bannerUrl;
    }

    public void setBannerUrl(String bannerUrl) {
        this.bannerUrl = bannerUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(bannerUrl);
        dest.writeString(description);
        dest.writeInt(id);
        dest.writeByteArray(image);
    }


}