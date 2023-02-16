package com.example.hqhan;

import androidx.room.Database;
import androidx.room.RoomDatabase;

    @Database(entities = {ruko.class, Pengguna.class}, version = 1, exportSchema = false)
    public abstract class rukoEntity extends RoomDatabase {
        public abstract rukoDao rukoDao();
    }
