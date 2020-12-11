package vn.poly.quanlybanhang.Model;

public
class LoaiSanPham {
    private String maLoai,tenLoai;

    public LoaiSanPham(String maLoai, String tenLoai) {
        this.maLoai = maLoai;
        this.tenLoai = tenLoai;
    }


    public String getMaLoai() {
        return maLoai;
    }


    public String getTenLoai() {
        return tenLoai;
    }

}
