package fpoly.thienhdph47232.duanmauver1.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import fpoly.thienhdph47232.duanmauver1.DAO.ThuThuDAO;
import fpoly.thienhdph47232.duanmauver1.R;

public class ManHinhDangNhap extends AppCompatActivity {
    private Button btnLogin, btnCancleLogin;
    private EditText userName, passWord;
    private CheckBox cbRemember;
    private ThuThuDAO thuThuDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_man_hinh_dang_nhap);
        btnLogin = findViewById(R.id.btnLogin);
        btnCancleLogin = findViewById(R.id.btnCancelLogin);
        userName = findViewById(R.id.edUsername);
        passWord = findViewById(R.id.edPassword);
        cbRemember = findViewById(R.id.cbSavePassword);
        thuThuDAO = new ThuThuDAO(this);

        SharedPreferences preferences = getSharedPreferences("USER_FILE", MODE_PRIVATE);
        String user = preferences.getString("USERNAME", "");
        String pass = preferences.getString("PASSWORD", "");
        Boolean rem = preferences.getBoolean("REMEMBER", false);

        userName.setText(user);
        passWord.setText(pass);
        cbRemember.setChecked(rem);

        btnCancleLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userName.setText("");
                passWord.setText("");
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkLogin();
            }
        });

    }
    public void rememberAccount(String userName2, String passWord2, boolean cbRem){
        SharedPreferences preferences = getSharedPreferences("USER_FILE", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        if (!cbRem) {
            userName.setText("");
            passWord.setText("");
            editor.clear();
        } else {
            editor.putString("USERNAME", userName2);
            editor.putString("PASSWORD", passWord2);
            editor.putBoolean("REMEMBER", cbRem);
        }
        editor.commit();
    }
    public void checkLogin(){
        String user = userName.getText().toString();
        String pass = passWord.getText().toString();
        if (user.isEmpty() || pass.isEmpty()){
            Toast.makeText(this, "Tên đăng nhập và mật khẩu không được bỏ trống", Toast.LENGTH_SHORT).show();
        } else {
            if (thuThuDAO.checkLogin(user,pass)){
                Toast.makeText(this, "Login Thành Công!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(ManHinhDangNhap.this, MainActivity.class));
                rememberAccount(user, pass, cbRemember.isChecked());
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                i.putExtra("user", user);
                startActivity(i);
            } else {
                Toast.makeText(this, "Tên đăng nhập hoặc mật khẩu không đúng", Toast.LENGTH_SHORT).show();
            }
        }
    }
}