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

import java.util.List;

public class AdapterRecyclerView extends RecyclerView.Adapter<AdapterRecyclerView.ViewHolder>{
    List<Hasil> listData;

    public AdapterRecyclerView(List<Hasil> listData) {
        this.listData = listData;
    }

    @NonNull
    @Override
    public AdapterRecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.layoutisi,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterRecyclerView.ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        Hasil index = listData.get(position);
        Context holderContext = holder.itemView.getContext();

        holder.kode.setText(index.getKode());
        holder.nama.setText(index.getNama());
        holder.alamat.setText(index.getAlamat());
        holder.telp.setText(index.getTelp());
        holder.tgl.setText(index.getTgl());
        holder.kota.setText(index.getKota());
        holder.kabupaten.setText(index.getKabupaten());
        holder.kecamatan.setText(index.getKecamatan());
        holder.kelurahan.setText(index.getKelurahan());

        holder.itemView.setOnClickListener(v -> {
                Intent intent = new Intent(holderContext, UpdateActivity.class);
                intent.putExtra("kode",index.getKode());
                intent.putExtra("nama",index.getNama());
                intent.putExtra("alamat",index.getAlamat());
                intent.putExtra("telepon",index.getTelp());
                intent.putExtra("tgl",index.getTgl());
                intent.putExtra("kota",index.getKota());
                intent.putExtra("kabupaten",index.getKabupaten());
                intent.putExtra("kecamatan",index.getKecamatan());
                intent.putExtra("kelurahan",index.getKelurahan());
                holderContext.startActivity(intent);
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
