package fpoly.thienhdph47232.duanmauver1.Activity;

import android.content.Intent;
import android.net.wifi.hotspot2.pps.HomeSp;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.google.android.material.navigation.NavigationView;

import fpoly.thienhdph47232.duanmauver1.DAO.ThuThuDAO;
import fpoly.thienhdph47232.duanmauver1.Fragment.ChangePassword;
import fpoly.thienhdph47232.duanmauver1.Fragment.QuanLyLoaiSach;
import fpoly.thienhdph47232.duanmauver1.Fragment.QuanLyPhieuMuon;
import fpoly.thienhdph47232.duanmauver1.Fragment.QuanLySach;
import fpoly.thienhdph47232.duanmauver1.Fragment.QuanLyThanhVien;
import fpoly.thienhdph47232.duanmauver1.Fragment.ThongKeDoanhThu;
import fpoly.thienhdph47232.duanmauver1.Fragment.Top10Sach;
import fpoly.thienhdph47232.duanmauver1.Model.ThuThu;
import fpoly.thienhdph47232.duanmauver1.R;

public class MainActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private NavigationView navigationView;
    private FrameLayout frameLayout;
    private DrawerLayout drawerLayout;
    String title;
    TextView tvWelcome_admin;
    ThuThuDAO thuThuDAO;
    private Fragment fragment = new QuanLyPhieuMuon();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Quản Lý Phiếu Mượn");

        navigationView = findViewById(R.id.navigationView);
        frameLayout = findViewById(R.id.framlayout);

        getSupportFragmentManager().beginTransaction().replace(R.id.framlayout, fragment).commit();
        drawerLayout = findViewById(R.id.drawlayout);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                if(menuItem.getItemId() == R.id.QuanLyPhieuMuon){
                    fragment = new QuanLyPhieuMuon();

                    toolbar.setTitle("Quản Lý Phiếu Mượn");
                } else if (menuItem.getItemId() == R.id.QuanLyLoaiSach) {
                    fragment = new QuanLyLoaiSach();
                    toolbar.setTitle("Quản Lý Loại Sách");
                } else if (menuItem.getItemId() == R.id.QuanLySach) {
                    fragment = new QuanLySach();
                    toolbar.setTitle("Quản Lý Sách");

                } else if (menuItem.getItemId() == R.id.QuanLyThanhVien) {
                    fragment = new QuanLyThanhVien();
                    toolbar.setTitle("Quản Lý Thành Viên");

                } else if (menuItem.getItemId() == R.id.Top10Sach) {
                    toolbar.setTitle("Top 10 Sách");

                    fragment = new Top10Sach();
                } else if (menuItem.getItemId() == R.id.DoanhThu) {
                    toolbar.setTitle("Doanh Thu");
                    fragment = new ThongKeDoanhThu();
                } else if (menuItem.getItemId() == R.id.DoiMatKhau) {
                    toolbar.setTitle("Đổi mật khẩu ");
                    fragment = new ChangePassword();
                } else if (menuItem.getItemId() == R.id.DangXuat) {
                    finish();
                    return true;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.framlayout,fragment).commit();

                drawerLayout.close();
                return false;
            }
        });
        DrawerLayout drawerLayout = findViewById(R.id.drawlayout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(MainActivity.this, drawerLayout
                , toolbar, 0,0);
        toggle.setDrawerIndicatorEnabled(true);
        toggle.syncState();
    }
}