package com.example.hqhan.viewmodel;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.hqhan.model.database.rukoDB;
import com.example.hqhan.model.entity.ruko;

import java.util.List;


public class ViewTabelPenggunaSearch extends AndroidViewModel {
    Context context;
    private LiveData<List<ruko>> arukos;
    private com.example.hqhan.model.database.dao.rukoDao rukoDao;

    public ViewTabelPenggunaSearch(@NonNull Application application) {
        super(application);

        rukoDao = rukoDB.getInstance(application).rukoDao();
    }

    public void setKeyword(String keyword) {
        arukos = (LiveData<List<ruko>>) rukoDao.getAllsearch(keyword);
    }

    public LiveData<List<ruko>> getrukos() {
        return arukos;
    }

//    public void deleteSingleData(int uid) {
//        rukoDao.deleteSingleData(uid);
//    }

}