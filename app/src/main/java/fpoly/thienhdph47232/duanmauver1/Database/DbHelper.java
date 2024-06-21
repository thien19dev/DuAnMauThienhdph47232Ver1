package fpoly.thienhdph47232.duanmauver1.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "Ver1";
    private static final int DB_VERSION = 1;
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
                "namSinh TEXT NOT NULL," +
                "soTaiKhoan INTEGER NOT NULL)";
        db.execSQL(CREATE_TABLE_THANH_VIEN);
        db.execSQL("INSERT INTO ThanhVien VALUES " +
                "(1, 'Hoàng Đức Thiện', '2003',56465880), " +
                "(2, 'Mai Vũ Dũng', '2003',56465881), " +
                "(3, 'Vũ Hoàng Anh', '2002',56465882), " +
                "(4, 'Nguyễn Văn An', '2001',56465883), " +
                "(5, 'Ngô Văn Lợi', '2000',56465884)," +
                "(6, 'Nguyễn Thị Thu', '1999',56465885)," +
                "(7, 'Hoàng Thị Thảo', '1998',56465886)," +
                "(8, 'Nguyễn Thị Thu Huyền', '1997',56465887)," +
                "(9, 'Bùi Đức Duy', '2001',56465888)");

        String CREATE_TABLE_LOAI_SACH = "create table LoaiSach(" +
                "maLoai INTEGER PRIMARY KEY AUTOINCREMENT," +
                "tenLoai TEXT NOT NULL)";
        db.execSQL(CREATE_TABLE_LOAI_SACH);
        db.execSQL("INSERT INTO LoaiSach VALUES " +
                "(0, 'Nhà Hàng - Khách Sạn'), " +
                "(1, 'Lập Trình Android'), " +
                "(2, 'Lập Trình IOS'), " +
                "(3, 'Ứng Dụng Phần Mềm'), " +
                "(4, 'Tiếng Anh')," +
                "(5, 'Điện - Điện Tử')," +
                "(6, 'Nhập Môn CNTT')," +
                "(7, 'Kinh Tế')," +
                "(8, 'Văn Học')," +
                "(9, 'Chính Trị - Pháp Luật')");

        String CREATE_TABLE_SACH = "create table Sach(" +
                "maSach INTEGER PRIMARY KEY AUTOINCREMENT," +
                "tenSach TEXT NOT NULL," +
                "giaThue INTEGER NOT NULL," +
                "maLoai INTEGER REFERENCES LoaiSach(maLoai)," +
                "soLuong INTEGER NOT NULL )";
        db.execSQL(CREATE_TABLE_SACH);
        db.execSQL("INSERT INTO Sach VALUES " +
                "(1, 'Lập Trình Website', 52000, 3,11), " +
                "(2, 'Lập Trình Swift', 63000, 2,9)," +
                " (3, 'UDPM Cơ Bản', 25200, 3,8), " +
                "(4, 'Ứng Dụng Phần Mềm Nâng Cao', 54000, 3,9), " +
                "(5, 'Tiếng Anh 1', 25000, 4,8), " +
                "(6, 'Tiếng Anh 2', 60500, 4,0)," +
                "(7, 'Tiếng Anh 3', 41500, 4,7), " +
                "(8, 'Tiếng Anh 4', 31700, 4,12), " +
                "(9, 'Lập Trình Android 1', 31700, 1,13), " +
                "(10, 'Lập Trình Android 2', 51850, 1,14), " +
                "(11, 'Lập Trình Android 3', 22700, 1,15), " +
                "(12, 'Lập Trình PHP1', 21700, 3,16), " +
                "(13, 'Lập Trình PHP2', 23700, 3,17), " +
                "(14, 'Lập Trình PHP3', 23700, 3,18)," +
                "(15, 'Lập Trình PHP4', 23700, 3,24)");

        String CREATE_TABLE_PHIEU_MUON = "create table PhieuMuon(" +
                "maPM INTEGER PRIMARY KEY AUTOINCREMENT," +
                "maTT TEXT REFERENCES ThuThu(maTT)," +
                "maTV INTEGER REFERENCES ThanhVien(maTV)," +
                "maSach INTEGER REFERENCES Sach(maSach)," +
                "tienThue INTEGER NOT NULL," +
                "ngay DATE NOT NULL," +
                "traSach INTEGER NOT NULL)";
        db.execSQL(CREATE_TABLE_PHIEU_MUON);
        db.execSQL("INSERT INTO PhieuMuon VALUES " +
                "(1, '1', 1, 1, 4000, '2024-02-21', 2), " +
                "(2, '1', 1, 1, 4000, '2024-02-21', 0), " +
                "(3, '1', 1, 1, 4000, '2024-02-21', 1), " +
                "(4, '1', 1, 1, 4000, '2024-02-21', 1), " +
                "(5, '1', 1, 1, 4000, '2024-02-21', 1), " +
                "(6, '1', 1, 1, 4000, '2024-02-21', 1), " +
                "(7, '1', 1, 1, 4000, '2024-02-21', 1), " +
                "(8, '1', 1, 1, 4000, '2024-02-21', 1), " +
                "(9, '1', 1, 1, 4000, '2024-02-21', 1), " +
                "(10, '1', 1, 1, 4000, '2024-02-21', 1), " +
                "(11, '1', 1, 1, 4000, '2024-02-21', 1)");
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
