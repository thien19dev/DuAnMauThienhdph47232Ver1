package fpoly.thienhdph47232.duanmauver1.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import fpoly.thienhdph47232.duanmauver1.Database.DbHelper;
import fpoly.thienhdph47232.duanmauver1.Model.ThanhVien;

public class ThanhVienDAO {
    private DbHelper dbHelper;
    private SQLiteDatabase db;

    public ThanhVienDAO(Context context) {
        dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public long insertThanhVien(ThanhVien thanhVien){
        ContentValues values = new ContentValues();
        values.put("hoTen", thanhVien.hoTen);
        values.put("namSinh", thanhVien.namSinh);
        return db.insert("ThanhVien", null, values);
    }
    public int updateThanhVien(ThanhVien thanhVien){
        ContentValues values = new ContentValues();
        values.put("hoTen", thanhVien.hoTen);
        values.put("namSinh", thanhVien.namSinh);
        return db.update("ThanhVien",values, "maTV=?", new String[]{String.valueOf(thanhVien.maThanhVien)});
    }
    public boolean deleteThanhVien(int id){
        long check = db.delete("ThanhVien", "maTV=?", new String[]{String.valueOf(id)});
        if (check > 0){
            return true;
        } else {
            return false;
        }
    }

    public List<ThanhVien> getAll(){
        String sql = "SELECT * FROM ThanhVien";
        return  getData(sql);
    }
    public ThanhVien getID(String id){
        String sql = "SELECT * FROM ThanhVien WHERE maTV=?";
        List<ThanhVien> list = getData(sql, id);
        return list.get(0);
    }

    public ArrayList<ThanhVien> getDSThanhVien(){
        ArrayList<ThanhVien> thanhVienList = new ArrayList<>();
        db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM ThanhVien", null);
        if (cursor != null && cursor.getCount() > 0){
            cursor.moveToFirst();
            do {
                ThanhVien obj = new ThanhVien(cursor.getString(1),cursor.getString(2));
                obj.setMaThanhVien(cursor.getInt(0));
                thanhVienList.add(obj);
            } while (cursor.moveToNext());
            cursor.close();
        }
        return thanhVienList;
    }

    private List<ThanhVien> getData(String sql, String...selectionArgs){
        List<ThanhVien> list = new ArrayList<>();
        Cursor c = db.rawQuery(sql, selectionArgs);
        while (c.moveToNext()){
            ThanhVien obj = new ThanhVien(c.getString(1),c.getString(2));
            obj.setMaThanhVien(c.getInt(0));
            list.add(obj);
        }
        return list;
    }
}
