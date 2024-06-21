package fpoly.thienhdph47232.duanmauver1.Model;

public class Sach {
    public int maSach, maLoai, giaThue,loaiSach, soLuong;
    public String tenSach ;


    public Sach() {
    }

    public Sach(String tenSach, int giaThue, int loaiSach, int soLuong) {
        this.maSach = maSach;
        this.tenSach = tenSach;
        this.giaThue = giaThue;
        this.loaiSach = loaiSach;
        this.soLuong = soLuong;
    }

    public Sach(String tenSach, int giaThue, int loaiSach) {
        this.maSach = maSach;
        this.tenSach = tenSach;
        this.giaThue = giaThue;
        this.loaiSach = loaiSach;
    }

    public int getMaSach() {
        return maSach;
    }

    public void setMaSach(int maSach) {
        this.maSach = maSach;
    }

    public int getMaLoai() {
        return maLoai;
    }

    public void setMaLoai(int maLoai) {
        this.maLoai = maLoai;
    }

    public int getGiaThue() {
        return giaThue;
    }

    public void setGiaThue(int giaThue) {
        this.giaThue = giaThue;
    }

    public int getLoaiSach() {
        return loaiSach;
    }

    public void setLoaiSach(int loaiSach) {
        this.loaiSach = loaiSach;
    }

    public String getTenSach() {
        return tenSach;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }
}
