package com.example.hqhan;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.hqhan.databinding.ActivityMainPemilikBinding;

import java.util.ArrayList;
import java.util.List;

public class MainPemilik extends AppCompatActivity implements rukoAdapter.rukoAdapterCallback {
    private Button Tmbhruko;
    private ActivityMainPemilikBinding binding;
    private rukoAdapter rukoAdapter;
    private ViewTabelPengguna rukoViewModel;
    AlertDialog.Builder builder;

    private EditText carin;
    private Button btncari;


    private LiveData<List<ruko>> mrukoss;
    private rukoDao rukoDao;

    private List<ruko> mrukos = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainPemilikBinding.inflate(getLayoutInflater());
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
        rukoAdapter = new rukoAdapter(this, mrukos, this);
        binding.rvrukos.setLayoutManager(new LinearLayoutManager(this));
        binding.rvrukos.setItemAnimator(new DefaultItemAnimator());
        binding.rvrukos.setAdapter(rukoAdapter);
        binding.btnTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ini = new Intent(MainPemilik.this,Tambahruko.class);
                startActivity(ini);
            }
        });
    }

    private void observeData() {
        rukoViewModel = ViewModelProviders.of(this).get(ViewTabelPengguna.class);
        rukoViewModel.getrukos().observe(this,
                new Observer<List<ruko>>() {
                    @Override
                    public void onChanged(List<ruko> rukos) {
                        rukoAdapter.addData(rukos);
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
                        rukoAdapter.addData(rukos);
                    }
                });
    }

    @Override
    public void onDelete(ruko ruko) {
        int uid = ruko.idruko;
        builder = new AlertDialog.Builder(this);
        //Uncomment the below code to Set the message and title from the strings.xml file
        builder.setMessage(R.string.dialog_message).setTitle(R.string.dialog_title);

        //Setting message manually and performing action on button click
        builder.setMessage("Apakah anda yakin akan menghapus data ruko dengan id = "+uid+"?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        //fungsi delete
                        rukoViewModel.deleteSingleData(uid);
                        Toast.makeText(getApplicationContext(),"Anda berhasil menghapus data ruko",
                                Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //  Action for 'NO' Button
                        dialog.cancel();
                        Toast.makeText(getApplicationContext(),"Anda tidak jadi menghapus data ruko",
                                Toast.LENGTH_SHORT).show();
                    }
                });
        //Creating dialog box
        AlertDialog alert = builder.create();
        //Setting the title manually
        alert.setTitle("Hapus data ruko");
        alert.show();
    }
    public void lokasiruko(ruko ruko){
        int uid = ruko.idruko;
        String url = ruko.gmap;
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        startActivity(intent);
    }

    @Override
    public void onEdit(ruko ruko) {
        Integer uid = ruko.idruko;
        Intent i = new Intent(MainPemilik.this, ubahdataruko.class).putExtra("ubah", uid);
        startActivity(i);
    }

}