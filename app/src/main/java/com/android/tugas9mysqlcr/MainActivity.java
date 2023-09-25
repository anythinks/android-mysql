package com.android.tugas9mysqlcr;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.tugas9mysqlcr.manage.GetKaryawan;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    ApiInterface mApiInterface;
    RecyclerView recyclerview;
    ArrayList<Hasil> hasil;
    Adapterisi adapterisi;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        hasil=new ArrayList<>();
        recyclerview = findViewById(R.id.isi);
        FloatingActionButton floatbt = findViewById(R.id.floating);
        mApiInterface = ApiClient.getRetrofit().create(ApiInterface.class);

        floatbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), AddActivity.class));
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        karyawanget();
    }

    public void karyawanget() {
        mApiInterface.getkaryawan().enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<GetKaryawan> call, Response<GetKaryawan> response) {
                Log.d("Hasil", response.toString());

                for (int i = 0; i < response.body().getHasil().size(); i++) {
                    String kode = response.body().getHasil().get(i).getKode();
                    String nama = response.body().getHasil().get(i).getNama();
                    String alamat = response.body().getHasil().get(i).getAlamat();
                    String telp = response.body().getHasil().get(i).getTelp();
                    String tgl = response.body().getHasil().get(i).getTgl();
                    String kota = response.body().getHasil().get(i).getKota();
                    String kabupaten = response.body().getHasil().get(i).getKabupaten();
                    String kecamatan = response.body().getHasil().get(i).getKecamatan();
                    String kelurahan = response.body().getHasil().get(i).getKelurahan();
                    hasil.add(new Hasil(kode, nama, alamat, telp, tgl, kota, kabupaten, kecamatan, kelurahan));
                }
                adapterisi = new Adapterisi(hasil, getApplication());
                recyclerview.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                recyclerview.setAdapter(adapterisi);
            }

            @Override
            public void onFailure(Call<GetKaryawan> call, Throwable t) {
                Toast.makeText(getApplication(), "Gagal", Toast.LENGTH_SHORT).show();
            }
        });
    }
}