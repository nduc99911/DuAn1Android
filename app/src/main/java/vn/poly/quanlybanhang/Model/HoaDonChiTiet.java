package vn.poly.quanlybanhang.Model;

public
class HoaDonChiTiet {
    private int maHDCT;
    HoaDon hoaDon;
    SanPham sanPham;
    private double tongTien;
    private int soLuong;

    public HoaDonChiTiet() {
    }

    public HoaDonChiTiet(int maHDCT, HoaDon hoaDon, SanPham sanPham, double tongTien, int soLuong) {
        this.maHDCT = maHDCT;
        this.hoaDon = hoaDon;
        this.sanPham = sanPham;
        this.tongTien = tongTien;
        this.soLuong = soLuong;
    }

    public int getMaHDCT() {
        return maHDCT;
    }

    public void setMaHDCT(int maHDCT) {
        this.maHDCT = maHDCT;
    }

    public HoaDon getHoaDon() {
        return hoaDon;
    }

    public void setHoaDon(HoaDon hoaDon) {
        this.hoaDon = hoaDon;
    }

    public SanPham getSanPham() {
        return sanPham;
    }

    public void setSanPham(SanPham sanPham) {
        this.sanPham = sanPham;
    }

    public double getTongTien() {
        return tongTien;
    }

    public void setTongTien(double tongTien) {
        this.tongTien = tongTien;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }
}
