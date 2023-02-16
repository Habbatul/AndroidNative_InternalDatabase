package com.example.hqhan;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;


public class ViewTabelPengguna extends AndroidViewModel {
    Context context;
    private LiveData<List<ruko>> mrukos;
    private rukoDao rukoDao;

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