package com.example.hqhan.view.viewmodel;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.hqhan.model.database.rukoDB;
import com.example.hqhan.model.entity.ruko;

import java.util.List;


public class ViewTabelPengguna extends AndroidViewModel {
    Context context;
    private LiveData<List<ruko>> mrukos;
    private com.example.hqhan.model.database.dao.rukoDao rukoDao;

    public ViewTabelPengguna(@NonNull Application application) {
        super(application);

        rukoDao = rukoDB.getInstance(application).rukoDao();
        mrukos = (LiveData<List<ruko>>) rukoDao.getAll();
    }
    public LiveData<List<ruko>> getrukos() {
        return mrukos;
    }

    public void deleteSingleData(int uid) {
        rukoDao.deleteSingleData(uid);
    }


}