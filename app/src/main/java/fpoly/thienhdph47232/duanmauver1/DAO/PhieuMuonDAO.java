package fpoly.thienhdph47232.duanmauver1.DAO;
import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.SimpleFormatter;
import fpoly.thienhdph47232.duanmauver1.Database.DbHelper;
import fpoly.thienhdph47232.duanmauver1.Model.PhieuMuon;
import fpoly.thienhdph47232.duanmauver1.Model.Sach;
import fpoly.thienhdph47232.duanmauver1.Model.Top10;

public class PhieuMuonDAO {
    private SQLiteDatabase sqLiteDatabase;
    DbHelper dbHelper;
    private Context context;
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public PhieuMuonDAO(Context context) {
        this.context = context;
        dbHelper = new DbHelper(context);
        sqLiteDatabase = dbHelper.getWritableDatabase();
    }

    public long insertPhieuMuon(PhieuMuon obj) {
        ContentValues values = new ContentValues();
        values.put("maTT", obj.getMaThuThu());
        values.put("maTV", obj.getMaThanhVien());
        values.put("maSach", obj.getMaSach());
        values.put("ngay", dateFormat.format(obj.getNgay()));
        values.put("tienThue", obj.getTienThue());
        values.put("traSach", obj.getTraSach());
        return sqLiteDatabase.insert("PhieuMuon", null, values);
    }

    public int updatePhieuMuon(PhieuMuon obj) {
        ContentValues values = new ContentValues();
        values.put("maTT", obj.getMaThuThu());
        values.put("maTV", obj.getMaThanhVien());
        values.put("maSach", obj.getMaSach());
        values.put("ngay", dateFormat.format(obj.getNgay()));
        values.put("tienThue", obj.getTienThue());
        values.put("traSach", obj.getTraSach());
        return sqLiteDatabase.update("PhieuMuon", values, "maPM=?", new String[]{String.valueOf(obj.getMaPhieuMuon())});
    }

    public int delete(String id) {
        return sqLiteDatabase.delete("PhieuMuon", "maPM=?", new String[]{id});
    }

    @SuppressLint("Range")
    private List<PhieuMuon> getData(String sql, String... selectionArgs) {
        List<PhieuMuon> list = new ArrayList<>();
        Cursor c = sqLiteDatabase.rawQuery(sql, selectionArgs);
        while (c.moveToNext()) {
            PhieuMuon obj = new PhieuMuon();
            obj.setMaPhieuMuon(Integer.parseInt(c.getString(c.getColumnIndex("maPM"))));
            obj.setMaThuThu(c.getString(c.getColumnIndex("maTT")));
            obj.setMaThanhVien(Integer.parseInt(c.getString(c.getColumnIndex("maTV"))));
            obj.setMaSach(Integer.parseInt(c.getString(c.getColumnIndex("maSach"))));
            obj.setTienThue(Integer.parseInt(c.getString(c.getColumnIndex("tienThue"))));
            try {
                obj.setNgay(dateFormat.parse(c.getString(c.getColumnIndex("ngay"))));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            obj.setTraSach(Integer.parseInt(c.getString(c.getColumnIndex("traSach"))));
            list.add(obj);
        }
        return list;
    }

    public List<PhieuMuon> getAll() {
        String sql = "SELECT * FROM PhieuMuon";
        return getData(sql);
    }

    public PhieuMuon getID(String id) {
        String sql = "SELECT * FROM PhieuMuon WHERE maPM=?";
        List<PhieuMuon> list = getData(sql, id);
        return list.get(0);
    }

    @SuppressLint("Range")
    public List<Top10> getTop() {
        String sqlTop10 = "SELECT maSach, count(maSach) as soLuong FROM PhieuMuon GROUP BY maSach ORDER BY soLuong DESC LIMIT 10";
        List<Top10> list = new ArrayList<Top10>();
        SachDAO sachDAO = new SachDAO(context);
        Cursor c = sqLiteDatabase.rawQuery(sqlTop10, null);

        while (c.moveToNext()) {
            Top10 top = new Top10();
            @SuppressLint("Range") Sach sach = sachDAO.getID(c.getString(c.getColumnIndex("maSach")));
            top.setTenSach(sach.getTenSach());
            top.setSoLuong(Integer.parseInt(c.getString(c.getColumnIndex("soLuong"))));
            list.add(top);
        }
        return list;
    }

    @SuppressLint("Range")
    public int getDoanhThu(String tuNgay, String denNgay) {
        String sqlDoanhThu = "SELECT SUM(tienThue) as DoanhThu FROM PhieuMuon WHERE ngay BETWEEN ? and ?";
        List<Integer> list = new ArrayList<>();
        Cursor c = sqLiteDatabase.rawQuery(sqlDoanhThu, new String[]{tuNgay, denNgay});

        while (c.moveToNext()) {
            try {
                list.add(Integer.parseInt(c.getString(c.getColumnIndex("DoanhThu"))));
            } catch (Exception e) {
                list.add(0);
            }
        }
        return list.get(0);
    }
}