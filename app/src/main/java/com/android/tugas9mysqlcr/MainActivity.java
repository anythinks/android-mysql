package com.android.tugas9mysqlcr;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ApiInterface mApiInterface;
    RecyclerView recyclerview;
    ArrayList<Hasil> hasil;
    Adapterisi adapterisi;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerview = findViewById(R.id.isi);
        FloatingActionButton floatbt = findViewById(R.id.floating);
        mApiInterface =ApiClient.getRetrofit().create(ApiInterface.class);

        floatbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), AddActivity.class));
            }
        });
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus){
            Karyawanget();
        }
    }

    public void Karyawanget()
    {
        mApiInterface.getkaryawan().enqueue(new Callback<GetKaryawan>() {
            @Override
            public void onResponse(Call<GetKaryawan> call, Response<GetKaryawan> response) {
                Log.d("Hasil",response.toString());

                hasil=new ArrayList<>();
                for(int i=0;i<response.body().getHasil().size();i++)
                {
                    String kode =response.body().getHasil().get(i).getKode();
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
                adapterisi = new Adapterisi(hasil,getApplication());
                recyclerview.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                recyclerview.setAdapter(adapterisi);
            }
            @Override
            public void onFailure(Call<GetKaryawan> call, Throwable t) {
                Toast.makeText(getApplication(),"Gagal", Toast.LENGTH_SHORT).show();
            }
        });
    }
}