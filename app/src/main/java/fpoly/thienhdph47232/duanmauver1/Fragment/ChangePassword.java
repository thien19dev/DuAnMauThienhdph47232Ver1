package fpoly.thienhdph47232.duanmauver1.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import fpoly.thienhdph47232.duanmauver1.DAO.ThuThuDAO;
import fpoly.thienhdph47232.duanmauver1.Model.ThuThu;
import fpoly.thienhdph47232.duanmauver1.R;

public class ChangePassword extends Fragment {
    EditText oldPassWord, newPassWord, repeateNewPassWord;
    Button btnChangePassword, btnCancelChangePassword;
    ThuThuDAO thuThuDAO;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_change_password, container, false);

        oldPassWord = view.findViewById(R.id.oldPassWord);
        newPassWord = view.findViewById(R.id.newPassWord);
        thuThuDAO = new ThuThuDAO(getActivity());
        repeateNewPassWord = view.findViewById(R.id.repeatNewPassword);
        btnChangePassword = view.findViewById(R.id.btnChangePassword);
        btnCancelChangePassword = view.findViewById(R.id.btnCancelChangePassword);
        btnCancelChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                oldPassWord.setText("");
                newPassWord.setText("");
                repeateNewPassWord.setText("");
            }
        });
        btnChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences = getActivity().getSharedPreferences("USER_FILE", Context.MODE_PRIVATE);
                String user = preferences.getString("USERNAME", "");

                if (vadidate()>0){
                    ThuThu thuThu = thuThuDAO.getID(user);
                        thuThu.setMatKhau(newPassWord.getText().toString());
                        if (thuThuDAO.updatePass(thuThu) > 0){
                            Toast.makeText(getActivity(), "Thay Đổi Mật Khẩu Thành Công", Toast.LENGTH_SHORT).show();
                            oldPassWord.setText("");
                            newPassWord.setText("");
                            repeateNewPassWord.setText("");
                        } else {
                            Toast.makeText(getActivity(), "Thay Đổi Mật Khẩu Thất Bại", Toast.LENGTH_SHORT).show();

                        }
                }

            }
        });
        return view;
    }

    public int vadidate(){
        int check = 1;
        if (oldPassWord.getText().length() == 0 || newPassWord.getText().length() == 0 || repeateNewPassWord.getText().length() == 0){
            Toast.makeText(getContext(), "Bạn Phải Nhập Đầy Đủ Thông Tin!", Toast.LENGTH_SHORT).show();
            check = -1;
        } else {
            SharedPreferences preferences = getActivity().getSharedPreferences("USER_FILE", Context.MODE_PRIVATE);
            String oldPass = preferences.getString("PASSWORD", "");
            String newPass = newPassWord.getText().toString();
            String repeatNewPass = repeateNewPassWord.getText().toString();

            if (!oldPass.equals(oldPassWord.getText().toString())){
                Toast.makeText(getContext(), "Mật Khẩu Sai!", Toast.LENGTH_SHORT).show();
                check = -1;
            }
            if (!newPass.equals(repeatNewPass)){
                Toast.makeText(getContext(), "Mật Khẩu Mới không Khớp", Toast.LENGTH_SHORT).show();
                check = -1;
            }
        }

        return check;
    }
}