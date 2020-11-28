package vn.poly.quanlybanhang.Model;

public
class KhachHang {
    private String ten,email,soDienThoai,diaChi;
    private double tienNo,tienDaMua;

    public KhachHang(String ten, String email, String soDienThoai, String diaChi, double tienNo, double tienDaMua) {
        this.ten = ten;
        this.email = email;
        this.soDienThoai = soDienThoai;
        this.diaChi = diaChi;
        this.tienNo = tienNo;
        this.tienDaMua = tienDaMua;
    }

    public KhachHang() {
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public double getTienNo() {
        return tienNo;
    }

    public void setTienNo(double tienNo) {
        this.tienNo = tienNo;
    }

    public double getTienDaMua() {
        return tienDaMua;
    }

    public void setTienDaMua(double tienDaMua) {
        this.tienDaMua = tienDaMua;
    }
}
