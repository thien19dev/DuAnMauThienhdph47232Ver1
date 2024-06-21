package fpoly.thienhdph47232.duanmauver1.Model;

import android.content.ContentValues;

public class ThanhVien {
    public int maThanhVien;
    public String hoTen, namSinh;
    public long soTaiKhoan;

    public ThanhVien() {
    }

    public ThanhVien(String hoTen, String namSinh, long soTaiKhoan) {
        this.maThanhVien = maThanhVien;
        this.hoTen = hoTen;
        this.namSinh = namSinh;
        this.soTaiKhoan = soTaiKhoan;
    }

    public ThanhVien(String hoTen, String namSinh) {
        this.maThanhVien = maThanhVien;
        this.hoTen = hoTen;
        this.namSinh = namSinh;
        this.soTaiKhoan = soTaiKhoan;
    }

    public int getMaThanhVien() {
        return maThanhVien;
    }

    public void setMaThanhVien(int maThanhVien) {
        this.maThanhVien = maThanhVien;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getNamSinh() {
        return namSinh;
    }

    public void setNamSinh(String namSinh) {
        this.namSinh = namSinh;
    }

    public long getSoTaiKhoan() {
        return soTaiKhoan;
    }

    public void setSoTaiKhoan(long soTaiKhoan) {
        this.soTaiKhoan = soTaiKhoan;
    }
}
