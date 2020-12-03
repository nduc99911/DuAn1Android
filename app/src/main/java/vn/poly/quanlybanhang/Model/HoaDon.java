package vn.poly.quanlybanhang.Model;

import java.util.Date;

public
class HoaDon {
    private String maHD,tenKhachHang;
private Date ngayBan;

    public HoaDon(String maHD, String tenKhachHang, Date ngayBan) {
        this.maHD = maHD;
        this.tenKhachHang = tenKhachHang;
        this.ngayBan = ngayBan;
    }
    public HoaDon(String maHD, String tenKhachHang) {
        this.maHD = maHD;
        this.tenKhachHang = tenKhachHang;

    }

    public HoaDon() {

    }


    public String getMaHD() {
        return maHD;
    }

    public void setMaHD(String maHD) {
        this.maHD = maHD;
    }

    public String getTenKhachHang() {
        return tenKhachHang;
    }

    public void setTenKhachHang(String tenKhachHang) {
        this.tenKhachHang = tenKhachHang;
    }

    public Date getNgayBan() {
        return ngayBan;
    }

    public void setNgayBan(Date ngayBan) {
        this.ngayBan = ngayBan;
    }
}
