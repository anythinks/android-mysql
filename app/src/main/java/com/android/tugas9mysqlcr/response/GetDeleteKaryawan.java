package com.android.tugas9mysqlcr.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetDeleteKaryawan {
    @SerializedName("hasil")
    @Expose
    private List<String> hasil;

    public List<String> getHasil() {
        return hasil;
    }

    public void setHasil(List<String> hasil) {
        this.hasil = hasil;
    }
}
