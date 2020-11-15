package com.example.duan1android.Model;

public class DonViTinh {
    private String donViTinh;

    public DonViTinh(String donViTinh) {
        this.donViTinh = donViTinh;
    }

    public String getDonViTinh() {
        return donViTinh;
    }

    public void setDonViTinh(String donViTinh) {
        this.donViTinh = donViTinh;
    }

    @Override
    public String toString() {
        return "DonViTinh{" +
                "donViTinh='" + donViTinh + '\'' +
                '}';
    }
}
