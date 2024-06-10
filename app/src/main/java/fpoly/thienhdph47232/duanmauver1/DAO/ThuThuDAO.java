package fpoly.thienhdph47232.duanmauver1.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import fpoly.thienhdph47232.duanmauver1.Database.DbHelper;
import fpoly.thienhdph47232.duanmauver1.Model.ThuThu;

public class ThuThuDAO {
    public EditText strUser, strPassword;
    public CheckBox cbRemember;
    private DbHelper dbHelper;
    private Context context;
    private SQLiteDatabase database;

    public ThuThuDAO(Context context) {
        this.context = context;
        dbHelper = new DbHelper(context);
        database = dbHelper.getWritableDatabase();
    }

    public int updatePass(ThuThu obj){
        ContentValues values = new ContentValues();
        values.put("maTT", obj.getMaTT());
        values.put("hoTen", obj.getHoTen());
        values.put("matKhau", obj.getMatKhau());
        return database.update("ThuThu", values, "maTT=?", new String[]{obj.getMaTT()});
    }

    public ThuThu getID(String id){
        String sql = "SELECT * FROM ThuThu WHERE maTT=?";
        List<ThuThu> list = getData(sql, id);
        return list.get(0);
    }
    public boolean checkAccount(String userName, String passWord) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase(); // truy vấn CSDL
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM ThuThu WHERE maTT = ? AND matKhau = ?", new String[]{userName, passWord}); // con trỏ
        return cursor.getCount() > 0;
    }

    @SuppressLint("Range")
    public List<ThuThu> getData(String sql, String...selectionArgs){
        List<ThuThu> list = new ArrayList<>();
        Cursor c = database.rawQuery(sql, selectionArgs);
        while (c.moveToNext()){
            ThuThu obj = new ThuThu();
            obj.setMaTT(c.getString(c.getColumnIndex("maTT")));
            obj.setHoTen(c.getString(c.getColumnIndex("hoTen")));
            obj.setMatKhau(c.getString(c.getColumnIndex("matKhau")));
            Log.i("/=======", obj.toString());
            list.add(obj);
        }
        return list;
    }
    public boolean checkLogin(String id, String passWord){
        String sql = "SELECT * FROM ThuThu WHERE maTT=? AND matKhau=?";
        List<ThuThu> list = getData(sql, id, passWord);
        if (list.size() == 0)
            return false;
        return true;
    }
}
