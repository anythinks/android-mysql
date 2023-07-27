package com.android.tugas9mysqlcr;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adapterisi extends RecyclerView.Adapter<Adapterisi.ViewHolder>{
    ArrayList<Hasil> listData;
    Context context;

    public Adapterisi(ArrayList<Hasil> listData, Context context) {
        this.listData = listData;
        this.context = context;
    }

    @NonNull
    @Override
    public Adapterisi.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.layoutisi,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapterisi.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Hasil hasil =listData.get(position);
        holder.kode.setText(listData.get(position).getKode());
        holder.nama.setText(listData.get(position).getNama());
        holder.alamat.setText(listData.get(position).getAlamat());
        holder.telp.setText(listData.get(position).getTelp());
        holder.tgl.setText(listData.get(position).getTgl());
        holder.kota.setText(listData.get(position).getKota());
        holder.kabupaten.setText(listData.get(position).getKabupaten());
        holder.kecamatan.setText(listData.get(position).getKecamatan());
        holder.kelurahan.setText(listData.get(position).getKelurahan());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, UpdateActivity.class);
                intent.putExtra("kode",listData.get(position).getKode());
                intent.putExtra("nama",listData.get(position).getNama());
                intent.putExtra("alamat",listData.get(position).getAlamat());
                intent.putExtra("telepon",listData.get(position).getTelp());
                intent.putExtra("tgl",listData.get(position).getTgl());
                intent.putExtra("kota",listData.get(position).getKota());
                intent.putExtra("kabupaten",listData.get(position).getKabupaten());
                intent.putExtra("kecamatan",listData.get(position).getKecamatan());
                intent.putExtra("kelurahan",listData.get(position).getKelurahan());
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView kode, nama, alamat, telp, tgl, kota, kabupaten, kecamatan, kelurahan;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            kode =itemView.findViewById(R.id.textView1);
            nama =itemView.findViewById(R.id.textView2);
            alamat =itemView.findViewById(R.id.textView3);
            telp =itemView.findViewById(R.id.textView4);
            tgl =itemView.findViewById(R.id.textView5);
            kota =itemView.findViewById(R.id.textView6);
            kabupaten =itemView.findViewById(R.id.textView7);
            kecamatan =itemView.findViewById(R.id.textView8);
            kelurahan =itemView.findViewById(R.id.textView9);
        }
    }
}
