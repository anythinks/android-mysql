package com.android.tugas9mysqlcr;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.android.tugas9mysqlcr.manage.GetInsertKaryawan;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddActivity extends AppCompatActivity {

    EditText kode, nama, alamat, telp, tgl, kota, kabupaten, kecamatan, kelurahan;
    private ApiInterface mApiInterface;
    AlertDialog.Builder builder;
    DatePickerDialog pickerdate;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        kode = findViewById(R.id.editTextTextkode);
        nama = findViewById(R.id.editTextTextnama);
        alamat = findViewById(R.id.editTextTextalamat);
        telp = findViewById(R.id.editTextTexttelp);
        tgl = findViewById(R.id.editTextTexttgl);
        kota = findViewById(R.id.editTextTextkota);
        kabupaten = findViewById(R.id.editTextTextkabupaten);
        kecamatan = findViewById(R.id.editTextTextkecamatan);
        kelurahan = findViewById(R.id.editTextTextkelurahan);
        Button simpan = findViewById(R.id.button);
        Button clear = findViewById(R.id.button2);
        builder = new AlertDialog.Builder(this);
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);

        tgl.setInputType(0);
        mApiInterface = ApiClient.getRetrofit().create(ApiInterface.class);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tgl.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) {
                pickerdate = new DatePickerDialog(AddActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year12, int month12, int dayOfMonth) {
                        tgl.setText(dayOfMonth + "/" + (month12 + 1) + "/" + year12);
                    }
                }, year, month, day);
                pickerdate.show();
            }
            v.setOnClickListener(v1 -> {
                pickerdate = new DatePickerDialog(AddActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year1, int month1, int dayOfMonth) {
                        tgl.setText(dayOfMonth + "/0" + (month1 + 1) + "/" + year1);
                    }
                }, year, month, day);
                pickerdate.show();
            });
        });

        simpan.setOnClickListener(view -> {
            if (kode.length() == 0) {
                builder.setTitle("Gagal")
                        .setMessage("Harap masukkan kode")
                        .setPositiveButton("OK", (dialog, which) -> dialog.dismiss())
                        .create().show();
            } else {
                Karyawaninsertget(kode.getText().toString(),
                        nama.getText().toString(),
                        alamat.getText().toString(),
                        telp.getText().toString(),
                        tgl.getText().toString(),
                        kota.getText().toString(),
                        kabupaten.getText().toString(),
                        kecamatan.getText().toString(),
                        kelurahan.getText().toString());
            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kode.setText("");
                nama.setText("");
                alamat.setText("");
                telp.setText("");
                tgl.setText("");
                kota.setText("");
                kabupaten.setText("");
                kecamatan.setText("");
                kelurahan.setText("");

                kode.requestFocus();
            }
        });
    }

    void Karyawaninsertget(String kode, String nama, String alamat, String telp, String tgl, String kota, String kabupaten, String kecamatan, String kelurahan) {
        mApiInterface.getinsertkaryawan("ok", kode, nama, alamat, telp, tgl, kota, kabupaten, kecamatan, kelurahan).enqueue(new Callback<GetInsertKaryawan>() {
            @Override
            public void onResponse(Call<GetInsertKaryawan> call, Response<GetInsertKaryawan> response) {
                Toast.makeText(getApplicationContext(), "Berhasil", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<GetInsertKaryawan> call, Throwable t) {
                Toast.makeText(getApplication(), "Gagal", Toast.LENGTH_SHORT).show();
            }
        });
    }
}