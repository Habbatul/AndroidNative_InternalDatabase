package com.example.hqhan.model.entity;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "ruko")
public class ruko implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "idruko")
    public Integer idruko;

    @ColumnInfo(name = "deskripsiruko")
    public String deskripsiruko;

    @ColumnInfo(name = "namaruko")
    public String namaruko;

    @ColumnInfo(name = "alamatruko")
    public String alamatruko;

    @ColumnInfo(name = "kontakruko")
    public String kontakruko;

    @ColumnInfo(name = "gmap")
    public String gmap;

    public ruko() {

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.idruko);
        dest.writeString(this.deskripsiruko);
        dest.writeString(this.namaruko);
        dest.writeString(this.kontakruko);
        dest.writeString(this.alamatruko);
        dest.writeString(this.gmap);
    }
    protected ruko(Parcel in) {
        this.idruko = in.readInt();
        this.deskripsiruko =in.readString();
        this.namaruko = in.readString();
        this.kontakruko = in.readString();
        this.alamatruko = in.readString();
        this.gmap = in.readString();
    }

    public static final Parcelable.Creator<ruko> CREATOR = new Parcelable.Creator<ruko>() {
        @Override
        public ruko createFromParcel(Parcel source) {
            return new ruko(source);
        }

        @Override
        public ruko[] newArray(int size) {
            return new ruko[size];
        }
    };
}
