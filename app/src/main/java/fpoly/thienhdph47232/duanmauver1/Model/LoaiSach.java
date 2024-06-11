package fpoly.thienhdph47232.duanmauver1.Model;

public class LoaiSach {
    private int maLoai;
    private String tenLoai;

    public LoaiSach() {
    }

    public LoaiSach( String tenLoai) {
        this.maLoai = maLoai;
        this.tenLoai = tenLoai;
    }

    public int getMaLoai() {
        return maLoai;
    }

    public void setMaLoai(int maLoai) {
        this.maLoai = maLoai;
    }

    public String getTenLoai() {
        return tenLoai;
    }

    public void setTenLoai(String tenLoai) {
        this.tenLoai = tenLoai;
    }
}
