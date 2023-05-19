package com.example.hqhan.adapter.database;

import android.content.Context;

import androidx.room.Room;

public class rukoDB {
    private static rukoEntity instance;

    public static rukoEntity getInstance(Context context) {
        if(rukoDB.instance == null)
        {
            rukoDB.instance = Room.databaseBuilder(context, rukoEntity.class, "ruko_DB.db").allowMainThreadQueries().build();
        }
        return rukoDB.instance;
    }

}
