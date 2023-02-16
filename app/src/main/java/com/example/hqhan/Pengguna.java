package com.example.hqhan;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Pengguna {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "id")
    public int id;

    @ColumnInfo(name = "tipe")
    public String tipe;

    @ColumnInfo(name = "nama")
    public String nama;

    @ColumnInfo(name = "password")
    public String password;

    @ColumnInfo(name = "alamat")
    public String alamat;

    @ColumnInfo(name = "kontak")
    public String kontak;
}
