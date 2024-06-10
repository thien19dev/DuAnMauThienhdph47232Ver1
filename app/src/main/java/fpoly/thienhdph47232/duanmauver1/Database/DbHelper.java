package fpoly.thienhdph47232.duanmauver1.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "Ver1";
    private static final int DB_VERSION = 2;
    public DbHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE_THU_THU = "create table ThuThu(" +
                "maTT TEXT PRIMARY KEY," +
                "hoTen TEXT NOT NULL," +
                "matKhau TEXT NOT NULL)";
        db.execSQL(CREATE_TABLE_THU_THU);
        db.execSQL("INSERT INTO ThuThu VALUES ('admin', 'Thien', '123')");
        String CREATE_TABLE_THANH_VIEN = "create table ThanhVien(" +
                "maTV INTEGER PRIMARY KEY AUTOINCREMENT," +
                "hoTen TEXT NOT NULL," +
                "namSinh TEXT NOT NULL)";
        db.execSQL(CREATE_TABLE_THANH_VIEN);
        db.execSQL("INSERT INTO ThanhVien VALUES (1, 'Hoàng Đức Thiện', '2003'), (2, 'Mai Vũ Dũng', '2003'), (3, 'Vũ Hoàng Anh', '2002'), (4,'Nguyễn Văn A', '2001'), (5, 'Nguyễn Thị B', '2000')");
        String CREATE_TABLE_LOAI_SACH = "create table LoaiSach(" +
                "maLoai INTEGER PRIMARY KEY AUTOINCREMENT," +
                "tenLoai TEXT NOT NULL)";
        db.execSQL(CREATE_TABLE_LOAI_SACH);
        db.execSQL("INSERT INTO LoaiSach VALUES (1, 'Lập Trình Android'), (2, 'Lập Trình IOS'), (3,'Ứng Dụng Phần Mềm'), (4, 'Tiếng Anh')");
        String CREATE_TABLE_SACH = "create table Sach(" +
                "maSach INTEGER PRIMARY KEY AUTOINCREMENT," +
                "tenSach TEXT NOT NULL," +
                "giaThue INTEGER NOT NULL," +
                "maLoai INTEGER REFERENCES LoaiSach(maLoai))";
        db.execSQL(CREATE_TABLE_SACH);
        db.execSQL("INSERT INTO Sach VALUES (1, 'Lập Trình Website', 2000, 3), (2, 'Lập Trình Swift', 3000, 2), (3, 'UDPM Cơ Bản', 2500, 3), (4, 'Ứng Dụng Phần Mềm Nâng Cao', 4000, 3), (5, 'Tiếng Anh 1', 1500, 4), (6, 'Tiếng Anh 2', 1500, 4),(7, 'Tiếng Anh 3', 1500, 4), (8, 'Tiếng Anh 4', 1700, 4), (9, 'Lập Trình Android 1', 1700, 1), (10, 'Lập Trình Android 2', 1850, 1), (11, 'Lập Trình Android 3', 2700, 1), (12, 'Lập Trình PHP1', 1700, 3), (13, 'Lập Trình PHP2', 3700, 3), (14, 'Lập Trình PHP3', 3700, 3), (15, 'Lập Trình PHP4', 3700, 3)");

        String CREATE_TABLE_PHIEU_MUON = "create table PhieuMuon(" +
                "maPM INTEGER PRIMARY KEY AUTOINCREMENT," +
                "maTT TEXT REFERENCES ThuThu(maTT)," +
                "maTV INTEGER REFERENCES ThanhVien(maTV)," +
                "maSach INTEGER REFERENCES Sach(maSach)," +
                "tienThue INTEGER NOT NULL," +
                "ngay DATE NOT NULL," +
                "traSach INTEGER NOT NULL)";
        db.execSQL(CREATE_TABLE_PHIEU_MUON);
        db.execSQL("INSERT INTO PhieuMuon VALUES (1, '1', 1, 1, 4000, '21/02/2024', 2), (2, '1', 1, 1, 4000, '21/02/2024', 0), (3, '1', 1, 1, 4000, '21/02/2024', 1), (4, '1', 1, 1, 4000, '21/02/2024', 1), (5, '1', 1, 1, 4000, '21/02/2024', 1), (6, '1', 1, 1, 4000, '21/02/2024', 1), (7, '1', 1, 1, 4000, '21/02/2024', 1), (8, '1', 1, 1, 4000, '21/02/2024', 1), (9, '1', 1, 1, 4000, '21/02/2024', 1), (10, '1', 1, 1, 4000, '21/02/2024', 1), (11, '1', 1, 1, 4000, '21/02/2024', 1)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String dropTableThuThu = "DROP TABLE IF EXISTS ThuThu";
        db.execSQL(dropTableThuThu);
        String dropTableThanhVien = "DROP TABLE IF EXISTS ThanhVien";
        db.execSQL(dropTableThanhVien);
        String dropTableLoaiSach = "DROP TABLE IF EXISTS LoaiSach";
        db.execSQL(dropTableLoaiSach);
        String dropTableSach = "DROP TABLE IF EXISTS Sach";
        db.execSQL(dropTableSach);
        String dropTablePhieuMuon = "DROP TABLE IF EXISTS PhieuMuon";
        db.execSQL(dropTablePhieuMuon);
        onCreate(db);
    }
}
