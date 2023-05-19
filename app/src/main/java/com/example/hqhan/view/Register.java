package com.example.hqhan.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hqhan.model.entity.Pengguna;
import com.example.hqhan.R;
import com.example.hqhan.adapter.database.rukoDB;
import com.example.hqhan.adapter.database.rukoDao;

public class Register extends AppCompatActivity {
    private EditText edtNama;
    private EditText edtTipe;
    private EditText edtAlamat;
    private EditText edtKontak;
    private EditText edtPw;
    private Button btn_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        this.btn_register = this.findViewById(R.id.btn_register);
        this.edtNama = this.findViewById(R.id.nama);
        this.edtTipe = this.findViewById(R.id.tipe);
        this.edtAlamat = this.findViewById(R.id.alamat);
        this.edtKontak = this.findViewById(R.id.kontak);
        this.edtPw = this.findViewById(R.id.PW);

        this.btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean valid = validasi();
                if (valid) {
                    rukoDao user = rukoDB.getInstance(getApplicationContext()).rukoDao();
                    user.insertAll(buatUser());
                    Toast.makeText(Register.this, "Registrasi berhasil", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
    }

    private Pengguna buatUser() {
        Pengguna u = new Pengguna();
        u.nama = this.edtNama.getText().toString().trim();
        u.alamat= this.edtAlamat.getText().toString().trim();
        u.tipe= this.edtTipe.getText().toString().trim();
        u.kontak = this.edtKontak.getText().toString().trim();
        u.password = this.edtPw.getText().toString().trim();
        return u;
    }

    private boolean validasi() {
        String PwInput = this.edtPw.getText().toString().trim();
        String NamaInput = this.edtNama.getText().toString().trim();
        String AlamatInput= this.edtAlamat.getText().toString().trim();
        String KontakInput= this.edtKontak.getText().toString().trim();
        String TipeInput= this.edtTipe.getText().toString().trim();

        if (NamaInput.isEmpty())
            Toast.makeText(this, "Nama masih kosong!", Toast.LENGTH_SHORT).show();
        else if (AlamatInput.isEmpty())
            Toast.makeText(this, "Alamat masih kosong!", Toast.LENGTH_SHORT).show();
        else if (KontakInput.isEmpty())
            Toast.makeText(this, "Kontak masih kosong!", Toast.LENGTH_SHORT).show();
        else if (TipeInput.isEmpty())
            Toast.makeText(this, "Tipe user masih kosong!", Toast.LENGTH_SHORT).show();
        else if (PwInput.isEmpty())
            Toast.makeText(this, "Password masih kosong!", Toast.LENGTH_SHORT).show();
        else {
            rukoDao user =rukoDB.getInstance(getApplicationContext()).rukoDao();
            Pengguna currentUserData = user.findByNama(NamaInput);
            if (currentUserData != null)
                Toast.makeText(this, "Akun yang didaftarkan sudah ada!", Toast.LENGTH_SHORT).show();
            else
                return true;
        }
        return false;
    }
}