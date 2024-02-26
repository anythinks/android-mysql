package com.android.tugas9mysqlcr.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.android.tugas9mysqlcr.R;
import com.android.tugas9mysqlcr.response.GetDeleteKaryawan;
import com.android.tugas9mysqlcr.response.GetUpdateKaryawan;
import com.android.tugas9mysqlcr.retrofit.ApiClient;
import com.android.tugas9mysqlcr.retrofit.ApiInterface;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateActivity extends AppCompatActivity {
    EditText kode, nama, alamat, telp, tgl, kota, kabupaten, kecamatan, kelurahan;
    Button update, delete;
    ApiInterface mApiInterface;
    DatePickerDialog pickerdate;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        kode = findViewById(R.id.editTextTextkode);
        nama = findViewById(R.id.editTextTextnama);
        alamat = findViewById(R.id.editTextTextalamat);
        telp = findViewById(R.id.editTextTexttelp);
        tgl = findViewById(R.id.editTextTexttgl);
        kota = findViewById(R.id.editTextTextkota);
        kabupaten = findViewById(R.id.editTextTextkabupaten);
        kecamatan = findViewById(R.id.editTextTextkecamatan);
        kelurahan = findViewById(R.id.editTextTextkelurahan);
        update = findViewById(R.id.button1);
        delete = findViewById(R.id.button2);

        kode.setInputType(0);
        tgl.setInputType(0);

        mApiInterface = ApiClient.getApiService();

        kode.setText(getIntent().getStringExtra("kode"));
        nama.setText(getIntent().getStringExtra("nama"));
        alamat.setText(getIntent().getStringExtra("alamat"));
        telp.setText(getIntent().getStringExtra("telepon"));
        tgl.setText(getIntent().getStringExtra("tgl"));
        kota.setText(getIntent().getStringExtra("kota"));
        kabupaten.setText(getIntent().getStringExtra("kabupaten"));
        kecamatan.setText(getIntent().getStringExtra("kecamatan"));
        kelurahan.setText(getIntent().getStringExtra("kelurahan"));

        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tgl.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    pickerdate = new DatePickerDialog(UpdateActivity.this, new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                            tgl.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
                        }
                    }, year, month, day);
                    pickerdate.show();
                }
                v.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        pickerdate = new DatePickerDialog(UpdateActivity.this, new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                tgl.setText(dayOfMonth + "/0" + (month + 1) + "/" + year);
                            }
                        }, year, month, day);
                        pickerdate.show();
                    }
                });
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Karyawanupdateget(kode.getText().toString(), nama.getText().toString(), alamat.getText().toString(), telp.getText().toString(), tgl.getText().toString(), kota.getText().toString(), kabupaten.getText().toString(), kecamatan.getText().toString(), kelurahan.getText().toString());
                finish();
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Karyawandeleteget(kode.getText().toString());
                finish();
            }
        });
    }

    public void Karyawanupdateget(String kode, String nama, String alamat, String telp, String tgl, String kota, String kabupaten, String kecamatan, String kelurahan) {
        mApiInterface.getupdatekaryawan("ok", kode, nama, alamat, telp, tgl, kota, kabupaten, kecamatan, kelurahan).enqueue(new Callback<GetUpdateKaryawan>() {
            @Override
            public void onResponse(Call<GetUpdateKaryawan> call, Response<GetUpdateKaryawan> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(UpdateActivity.this, "Diupdate", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(UpdateActivity.this, "Gagal Diupdate", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<GetUpdateKaryawan> call, Throwable t) {
                Toast.makeText(getApplication(), "Gagal", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void Karyawandeleteget(String kode) {
        mApiInterface.getdeletekaryawan("ok", kode).enqueue(new Callback<GetDeleteKaryawan>() {
            @Override
            public void onResponse(Call<GetDeleteKaryawan> call, Response<GetDeleteKaryawan> response) {
//                Toast.makeText(getApplication(),response.body().getHasil().get(0).toString(), Toast.LENGTH_SHORT).show();
                Toast.makeText(UpdateActivity.this, "Dihapus", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<GetDeleteKaryawan> call, Throwable t) {
                Toast.makeText(getApplication(), "Gagal", Toast.LENGTH_SHORT).show();
            }
        });
    }
}