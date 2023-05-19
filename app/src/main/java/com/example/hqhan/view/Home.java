package com.example.hqhan.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hqhan.model.entity.Pengguna;
import com.example.hqhan.model.sharedpref.PreferenceTipe;
import com.example.hqhan.R;
import com.example.hqhan.model.sharedpref.preferenceNama;


public class Home extends AppCompatActivity{
    private SharedPreferences sharedPrefs;
    private Button Logout_btn;
    private Button List_btn;
    private static final String AUTO_LOGIN_KEY = "key_tetapLogin";
    private Pengguna UserSaatIni;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        TextView kataWelcome = findViewById(R.id.welcome_text);

        String Nama = preferenceNama.getRegisteredUser(getBaseContext());
        kataWelcome.setText("Selamat Datang \n"+ Nama);

        sharedPrefs = getSharedPreferences("cek_login", Context.MODE_PRIVATE);
        Logout_btn = findViewById(R.id.btn_logout);
        Logout_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = sharedPrefs.edit();
                editor.remove(AUTO_LOGIN_KEY);
                editor.apply();
                Intent ini = new Intent(Home.this, MainActivity.class);
                startActivity(ini);
                finish();
            }
        });
        List_btn = findViewById(R.id.btn_List);
        String CekTipe= PreferenceTipe.getRegisteredTipe(getBaseContext());
        List_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(CekTipe.equals("pemilik")){
                    Intent ini = new Intent(Home.this, MainPemilik.class);
                    startActivity(ini);
                }
                else if(CekTipe.equals("pengguna")){
                    Intent ini = new Intent(Home.this, MainPengguna.class);
                    startActivity(ini);
                }
                else{
                    Toast.makeText(getApplicationContext(),"Anda Tidak bertipe pengguna maupun pemilik, anda bertipe : "+CekTipe,
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}