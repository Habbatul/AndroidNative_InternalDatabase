package com.example.hqhan.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hqhan.R;
import com.example.hqhan.model.entity.Pengguna;
import com.example.hqhan.model.database.rukoDB;
import com.example.hqhan.model.database.dao.rukoDao;
import com.example.hqhan.model.entity.ruko;

public class ubahdataruko extends AppCompatActivity {
    private EditText edtDeskripsi;
    private EditText edtNama;
    private EditText edtAlamat;
    private EditText edtKontak;
    private EditText edtGmap;
    private Button btnUbah;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubahdata_ruko);
        this.btnUbah = this.findViewById(R.id.btn_ubahruko);
        this.edtNama = this.findViewById(R.id.namaruko);
        this.edtGmap = this.findViewById(R.id.GMAP);
        this.edtAlamat = this.findViewById(R.id.alamatruko);
        this.edtKontak = this.findViewById(R.id.kontakruko);
        this.edtDeskripsi = this.findViewById(R.id.deskripsiruko);
        rukoDao ruko;
        ruko = rukoDB.getInstance(getBaseContext()).rukoDao();
        Bundle extras = getIntent().getExtras();
        int uid = extras.getInt("ubah");


        ruko daftarruko = ruko.ambilSingleData(uid);
        edtNama.setText(daftarruko.namaruko);
        edtGmap.setText(daftarruko.gmap);
        edtAlamat.setText(daftarruko.alamatruko);
        edtKontak.setText(daftarruko.kontakruko);
        edtDeskripsi.setText(daftarruko.deskripsiruko);

        this.btnUbah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean valid = validasi();
                if (valid) {
                    rukoDao ruko = rukoDB.getInstance(getApplicationContext()).rukoDao();
                    ruko.ubahSingleData(edtDeskripsi.getText().toString(),
                            edtNama.getText().toString(),edtAlamat.getText().toString(),
                            edtKontak.getText().toString(),edtGmap.getText().toString(),uid);


                    Toast.makeText(ubahdataruko.this, "Ubah data ruko berhasil", Toast.LENGTH_SHORT).show();

                    finish();
                }
            }
        });
    }

    private ruko buatruko() {
        ruko u = new ruko();
        u.namaruko = this.edtNama.getText().toString().trim();
        u.gmap = this.edtGmap.getText().toString().trim();
        u.alamatruko = this.edtAlamat.getText().toString().trim();
        u.kontakruko = this.edtKontak.getText().toString().trim();
        u.deskripsiruko = this.edtDeskripsi.getText().toString().trim();
        return u;
    }

    private boolean validasi() {
        String namaruko = this.edtNama.getText().toString().trim();
        String gmap = this.edtGmap.getText().toString().trim();
        String alamatruko = this.edtAlamat.getText().toString().trim();
        String kontakruko = this.edtKontak.getText().toString().trim();
        String fasilitasruko = this.edtDeskripsi.getText().toString().trim();

        if (namaruko.isEmpty())
            Toast.makeText(this, "Nama masih kosong!", Toast.LENGTH_SHORT).show();
        else if (gmap.isEmpty())
            Toast.makeText(this, "Url google map masih kosong!", Toast.LENGTH_SHORT).show();
        else if (alamatruko.isEmpty())
            Toast.makeText(this, "alamat masih kosong!", Toast.LENGTH_SHORT).show();
        else if (kontakruko.isEmpty())
            Toast.makeText(this, "kontak masih kosong!", Toast.LENGTH_SHORT).show();
        else if (fasilitasruko.isEmpty())
            Toast.makeText(this, "deskripsi masih kosong!", Toast.LENGTH_SHORT).show();
        else {
            rukoDao user = rukoDB.getInstance(getApplicationContext()).rukoDao();
            Pengguna currentUserData = user.findByNama(gmap);
            if (currentUserData != null)
                Toast.makeText(this, "ruko yang ingin didaftarkan sudah ada!", Toast.LENGTH_SHORT).show();
            else
                return true;
        }
        return false;
    }
}