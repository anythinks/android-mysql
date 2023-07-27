package com.android.tugas9mysqlcr;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetKaryawan {
    @SerializedName("hasil")
    @Expose
    private List<Hasil> hasil;

    public List<Hasil> getHasil() {
        return hasil;
    }

    public void setHasil(List<Hasil> hasil) {
        this.hasil = hasil;
    }
}
