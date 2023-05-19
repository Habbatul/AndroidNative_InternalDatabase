package com.example.hqhan.viewmodel;

import android.app.Application;
import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.arch.core.util.Function;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import com.example.hqhan.model.database.rukoDB;
import com.example.hqhan.model.entity.ruko;

import java.util.List;


public class ViewTabelPengguna extends AndroidViewModel {
    private MutableLiveData<String> keyword = new MutableLiveData<>();
    private LiveData<List<ruko>> rukos;
    private com.example.hqhan.model.database.dao.rukoDao rukoDao;

    public ViewTabelPengguna(@NonNull Application application) {
        super(application);

        rukoDao = rukoDB.getInstance(application).rukoDao();
        rukos = Transformations.switchMap(keyword, new Function<String, LiveData<List<ruko>>>() {
            @Override
            public LiveData<List<ruko>> apply(String keyword) {
                if (TextUtils.isEmpty(keyword)) {
                    return rukoDao.getAll();
                } else {
                    return rukoDao.getAllsearch(keyword);
                }
            }
        });
    }

    public void setKeyword(String keyword) {
        this.keyword.setValue(keyword);
    }

    public void deleteSingleData(int uid) {
        rukoDao.deleteSingleData(uid);
    }

    public LiveData<List<ruko>> getrukos() {
        return rukos;
    }
}