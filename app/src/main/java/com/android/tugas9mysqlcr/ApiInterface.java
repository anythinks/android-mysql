package com.android.tugas9mysqlcr;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiInterface {
    @GET("Read.php")
    Call<GetKaryawan> getkaryawan();

    @FormUrlEncoded
    @POST("Insert.php")
    Call<GetInsertKaryawan> getinsertkaryawan(
            @Field("kirim") String kirim,
            @Field("kode") String kode,
            @Field("nama") String nama,
            @Field("alamat") String alamat,
            @Field("telp") String telp,
            @Field("tgl") String tgl,
            @Field("kota") String kota,
            @Field("kabupaten") String kabupaten,
            @Field("kecamatan") String kecamatan,
            @Field("kelurahan") String kelurahan);

    @FormUrlEncoded
    @POST("Update.php")
    Call<GetUpdateKaryawan> getupdatekaryawan(
            @Field("kirim") String kirim,
            @Field("kode") String kode,
            @Field("nama") String nama,
            @Field("alamat") String alamat,
            @Field("telp") String telp,
            @Field("tgl") String tgl,
            @Field("kota") String kota,
            @Field("kabupaten") String kabupaten,
            @Field("kecamatan") String kecamatan,
            @Field("kelurahan") String kelurahan);

    @FormUrlEncoded
    @POST("Delete.php")
    Call<GetDeleteKaryawan> getdeletekaryawan(
            @Field("kirim") String kirim,
            @Field("kode") String kode
    );
}
