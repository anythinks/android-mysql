package com.android.tugas9mysqlcr.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.tugas9mysqlcr.response.Hasil;
import com.android.tugas9mysqlcr.R;
import com.android.tugas9mysqlcr.response.GetKaryawan;
import com.android.tugas9mysqlcr.retrofit.ApiClient;
import com.android.tugas9mysqlcr.retrofit.ApiInterface;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    ApiInterface mApiInterface;
    RecyclerView recyclerview;
    List<Hasil> hasil = new ArrayList<>();
    AdapterRecyclerView adapter;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerview = findViewById(R.id.isi);
        FloatingActionButton floatbt = findViewById(R.id.floating);
        mApiInterface = ApiClient.getApiService();

        floatbt.setOnClickListener(v ->
                startActivity(new Intent(getApplicationContext(), AddActivity.class)));
    }

    @Override
    protected void onResume() {
        super.onResume();
        karyawanget();
    }

    public void karyawanget() {
        mApiInterface.getkaryawan().enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<GetKaryawan> call,Response<GetKaryawan> response) {
                if (!response.isSuccessful()) {
                    Log.d("Hasil", response.message());
                }

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

                adapter = new AdapterRecyclerView(hasil);
                recyclerview.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                recyclerview.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<GetKaryawan> call, Throwable t) {
                Toast.makeText(getApplication(), "Gagal", Toast.LENGTH_SHORT).show();
            }
        });
    }
}