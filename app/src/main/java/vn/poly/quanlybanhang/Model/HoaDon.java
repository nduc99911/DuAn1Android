package vn.poly.quanlybanhang.Model;

public
class HoaDon {
    private String maHD,ngayBan,tenKhachHang;

    public HoaDon(String maHD, String ngayBan, String tenKhachHang) {
        this.maHD = maHD;
        this.ngayBan = ngayBan;
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

    public String getNgayBan() {
        return ngayBan;
    }

    public void setNgayBan(String ngayBan) {
        this.ngayBan = ngayBan;
    }

    public String getTenKhachHang() {
        return tenKhachHang;
    }

    public void setTenKhachHang(String tenKhachHang) {
        this.tenKhachHang = tenKhachHang;
    }
}
