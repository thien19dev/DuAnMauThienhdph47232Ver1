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
import fpoly.thienhdph47232.duanmauver1.Model.LoaiSach;

public class LoaiSachDAO {
    private DbHelper dbHelper;
    private SQLiteDatabase sqLiteDatabase;

    public LoaiSachDAO(Context context) {
        dbHelper = new DbHelper(context);
        sqLiteDatabase = dbHelper.getWritableDatabase();
    }

    public long insert(LoaiSach obj){
        ContentValues values = new ContentValues();
        values.put("maLoai", obj.getMaLoai());
        values.put("tenLoai", obj.getTenLoai());
        return sqLiteDatabase.insert("LoaiSach",null, values);
    }

    public int updateLoaiSach(LoaiSach obj){
        ContentValues values = new ContentValues();
        values.put("maLoai", obj.getMaLoai());
        values.put("tenLoai", obj.getTenLoai());
        return sqLiteDatabase.update("LoaiSach", values, "maLoai = ?", new String[]{String.valueOf(obj.getMaLoai())});
    }
    public int delete(String id){
        return sqLiteDatabase.delete("LoaiSach", "maLoai = ?", new String[]{id});
    }

    @SuppressLint("Range")
    public List<LoaiSach> getData(String sql, String...selectionArgs){
        List<LoaiSach> list = new ArrayList<LoaiSach>();
        Cursor cursor = sqLiteDatabase.rawQuery(sql, selectionArgs);
        while (cursor.moveToNext()){
            LoaiSach obj = new LoaiSach();
            obj.setMaLoai(Integer.parseInt(cursor.getString(cursor.getColumnIndex("maLoai"))));
            obj.setTenLoai(cursor.getString(cursor.getColumnIndex("tenLoai")));
            list.add(obj);
        }
        return list;
    }

    public ArrayList<LoaiSach> getAll() {
        String slq = "SELECT * FROM LoaiSach";
        return (ArrayList<LoaiSach>) getData(slq);
    }

    public LoaiSach getID(String id){
        String sql = "SELECT * FROM LoaiSach WHERE maLoai=?";
        List<LoaiSach> list = getData(sql, id);
        return list.get(0);
    }

    public ArrayList<LoaiSach> getDSLoaiSach(){
        ArrayList<LoaiSach> loaiSachArrayList = new ArrayList<>();

        if (dbHelper == null) {
            Log.e("LoaiSachDAO", "DbHelper is null, cannot get readable database");
            return loaiSachArrayList;
        }
        sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM LoaiSach", null);

        if (cursor != null && cursor.getCount() > 0){
            cursor.moveToFirst();
            do{
                loaiSachArrayList.add(new LoaiSach(cursor.getInt(0), cursor.getString(1)));
            } while (cursor.moveToNext());
            cursor.close();
        } else {
            Log.e("LoaiSachDAO", "DbHelper is nulllllll");
        }
        return loaiSachArrayList;
    }
}


