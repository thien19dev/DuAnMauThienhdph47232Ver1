package fpoly.thienhdph47232.duanmauver1.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import fpoly.thienhdph47232.duanmauver1.Database.DbHelper;
import fpoly.thienhdph47232.duanmauver1.Model.Sach;

public class SachDAO {
    private DbHelper dbHelper;
    private SQLiteDatabase db;

    public SachDAO(Context context) {
        dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public long insertSach(Sach sach){
        ContentValues values = new ContentValues();
        values.put("tenSach", sach.tenSach);
        values.put("maLoai", sach.maLoai);
        values.put("giaThue", sach.giaThue);
        return db.insert("Sach", null, values);
    }

    public long updateSach(Sach sach){
        ContentValues values = new ContentValues();
        values.put("tenSach", sach.tenSach);
        values.put("maLoai", sach.maLoai);
        values.put("giaThue", sach.giaThue);

        return db.update("Sach", values, "maSach=?", new String[]{String.valueOf(sach.maSach)});
    }
    public boolean deleteSach(int id){
        long check = db.delete("Sach", "maSach=?", new String[]{String.valueOf(id)});
        if (check > 0)
            return true;
        return false;
    }
    @SuppressLint("Range")
    private List<Sach> getData(String sql, String...selectionArgs){
        List<Sach> list = new ArrayList<>();
        Cursor c = db.rawQuery(sql, selectionArgs);
        while (c.moveToNext()){
            Sach obj = new Sach();
            obj.maSach = Integer.parseInt(c.getString(c.getColumnIndex("maSach")));
            obj.tenSach = c.getString(c.getColumnIndex("tenSach"));
            obj.giaThue = Integer.parseInt(c.getString(c.getColumnIndex("giaThue")));
            obj.maLoai = Integer.parseInt(c.getString(c.getColumnIndex("maLoai")));
            list.add(obj);
        }
        return list;
    }
    public List<Sach> getAll(){
        String sql = "SELECT * FROM Sach";
        return  getData(sql);
    }

    public Sach getID(String id){
        String sql = "SELECT * FROM Sach WHERE maSach = ?";
        List<Sach> list = getData(sql, id);
        return list.get(0);
    }

    public ArrayList<Sach> getDSSach() {
        ArrayList<Sach> sachArrayList = new ArrayList<>();
        if (db == null) {
            Log.e("SachDAO", "DbHelper is null, cannot get readdle database");
            return sachArrayList;
        }
        db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Sach", null);

        if (cursor != null && cursor.getCount() > 0){
            cursor.moveToFirst();
            do {
                Sach sach = new Sach(cursor.getString(1),
                        cursor.getInt(2),
                        cursor.getInt(3));
                sach.setMaSach(cursor.getInt(0));
                sachArrayList.add(sach);
            } while (cursor.moveToNext());
            cursor.close();
        }
        return sachArrayList;
    }

}
