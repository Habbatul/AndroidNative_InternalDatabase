package com.example.hqhan.view;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.hqhan.R;
import com.example.hqhan.view.viewmodel.ViewTabelPengguna;
import com.example.hqhan.databinding.ActivityMainPenggunaBinding;
import com.example.hqhan.model.entity.ruko;
import com.example.hqhan.adapter.rukoAdapterDua;
import com.example.hqhan.model.database.rukoDB;

import java.util.ArrayList;
import java.util.List;

public class MainPengguna extends AppCompatActivity implements com.example.hqhan.adapter.rukoAdapterDua.rukoAdapterDuaCallback {
    private ActivityMainPenggunaBinding binding;
    private rukoAdapterDua rukoAdapterDua;
    private ViewTabelPengguna rukoViewModel;
    private EditText carin;
    private Button btncari;


    private LiveData<List<ruko>> mrukoss;
    private com.example.hqhan.model.database.dao.rukoDao rukoDao;

    private List<ruko> mrukos = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainPenggunaBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initAdapter();
        observeData();
        this.carin = this.findViewById(R.id.cariN);;
        this.btncari = this.findViewById(R.id.cariN_button);
        btncari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if((carin.getText().toString()).isEmpty()){
                    observeData();
                }if(!(carin.getText().toString()).isEmpty()){
                    observeDataCari();
                }
            }
        });

    }

    private void initAdapter() {
        rukoAdapterDua = new rukoAdapterDua(this, mrukos, this);
        binding.rvPengguna.setLayoutManager(new LinearLayoutManager(this));
        binding.rvPengguna.setItemAnimator(new DefaultItemAnimator());
        binding.rvPengguna.setAdapter(rukoAdapterDua);
    }

    private void observeData() {
        rukoViewModel = ViewModelProviders.of(this).get(ViewTabelPengguna.class);
        rukoViewModel.getrukos().observe(this,
                new Observer<List<ruko>>() {
                    @Override
                    public void onChanged(List<ruko> rukos) {
                        rukoAdapterDua.addData(rukos);
                    }
                });
    }

    private void observeDataCari() {
        rukoDao = rukoDB.getInstance(getApplicationContext()).rukoDao();
        mrukoss = (LiveData<List<ruko>>) rukoDao.getAllsearch(carin.getText().toString());;
        mrukoss.observe(this,
                new Observer<List<ruko>>() {
                    @Override
                    public void onChanged(List<ruko> rukos) {
                        rukoAdapterDua.addData(rukos);
                    }
                });
    }

    public void lokasiruko(ruko ruko){
        int uid = ruko.idruko;
        String url = ruko.gmap;
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        startActivity(intent);
    }

    @Override
    public void kontakRuko(ruko ruko) {
        String nomor = ruko.kontakruko;
        Intent i = new Intent(android.content.Intent.ACTION_DIAL, Uri.parse("tel:"+nomor));
        startActivity(i);
    }

}