package com.example.hqhan.model.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.hqhan.model.entity.Pengguna;
import com.example.hqhan.model.entity.ruko;

@Database(entities = {ruko.class, Pengguna.class}, version = 1, exportSchema = false)
    public abstract class rukoEntity extends RoomDatabase {
        public abstract rukoDao rukoDao();
    }
