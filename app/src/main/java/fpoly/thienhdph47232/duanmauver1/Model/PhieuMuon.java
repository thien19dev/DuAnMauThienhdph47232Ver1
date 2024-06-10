package fpoly.thienhdph47232.duanmauver1.Model;

import java.util.Date;

public class PhieuMuon {
    public int maPhieuMuon;
    public String maThuThu;
    public int maThanhVien, maSach;
    public Date ngay;
    public int traSach, tienThue;
    public PhieuMuon() {
    }

    public PhieuMuon(String maThuThu,
                     int maThanhVien,
                     int maSach,
                     Date ngay,
                     int traSach,
                     int tienThue) {
        this.maPhieuMuon = maPhieuMuon;
        this.maThuThu = maThuThu;
        this.maThanhVien = maThanhVien;
        this.maSach = maSach;
        this.ngay = ngay;
        this.traSach = traSach;
        this.tienThue = tienThue;
    }

    public int getMaPhieuMuon() {
        return maPhieuMuon;
    }

    public void setMaPhieuMuon(int maPhieuMuon) {
        this.maPhieuMuon = maPhieuMuon;
    }

    public String getMaThuThu() {
        return maThuThu;
    }

    public void setMaThuThu(String maThuThu) {
        this.maThuThu = maThuThu;
    }

    public int getMaThanhVien() {
        return maThanhVien;
    }

    public void setMaThanhVien(int maThanhVien) {
        this.maThanhVien = maThanhVien;
    }

    public int getMaSach() {
        return maSach;
    }

    public void setMaSach(int maSach) {
        this.maSach = maSach;
    }

    public Date getNgay() {
        return ngay;
    }

    public void setNgay(Date ngay) {
        this.ngay = ngay;
    }

    public int getTraSach() {
        return traSach;
    }

    public void setTraSach(int traSach) {
        this.traSach = traSach;
    }

    public int getTienThue() {
        return tienThue;
    }

    public void setTienThue(int tienThue) {
        this.tienThue = tienThue;
    }
}
