package fpoly.thienhdph47232.duanmauver1.Fragment;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import fpoly.thienhdph47232.duanmauver1.Adapter.LoaiSachAdapter;
import fpoly.thienhdph47232.duanmauver1.Adapter.ThanhVienAdapter;
import fpoly.thienhdph47232.duanmauver1.DAO.ThanhVienDAO;
import fpoly.thienhdph47232.duanmauver1.Model.ThanhVien;
import fpoly.thienhdph47232.duanmauver1.R;

public class QuanLyThanhVien extends Fragment {

    private ThanhVienDAO thanhVienDAO;
    private RecyclerView recyclerViewThanhVien;
    private ThanhVienAdapter thanhVienAdapter;
    private FloatingActionButton floatAddThanhVien;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_quan_ly_thanh_vien, container, false);

        recyclerViewThanhVien = view.findViewById(R.id.recycleViewThanhVien);
        floatAddThanhVien = view.findViewById(R.id.floatAddThanhVien);
        thanhVienDAO = new ThanhVienDAO(getContext());
        ArrayList<ThanhVien> list = thanhVienDAO.getDSThanhVien();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        recyclerViewThanhVien.setLayoutManager(linearLayoutManager);
        thanhVienAdapter = new ThanhVienAdapter(getContext(), list);
        recyclerViewThanhVien.setAdapter(thanhVienAdapter);

        floatAddThanhVien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ThemThanhVien();
            }
        });

        return view;
    }

    public void ThemThanhVien(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = this.getLayoutInflater();
        View view = inflater.inflate(R.layout.add_thanhvien_dialog, null);
        builder.setView(view);

        EditText edHoTen = view.findViewById(R.id.edThemTenThanhVien);
        EditText edNamSinh = view.findViewById(R.id.edThemSinhThanhVien);
        Button btnThemThanhVien = view.findViewById(R.id.btnThemThanhVien);
        Button btnHuyThemThanhVien = view.findViewById(R.id.btnHuyThemThanhVien);

        AlertDialog dialog = builder.create();

        btnThemThanhVien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ten = edHoTen.getText().toString();
                String namSinh = edNamSinh.getText().toString();
                if (!ten.isEmpty() && !namSinh.isEmpty()){
                    long check = thanhVienDAO.insertThanhVien(new ThanhVien(ten,namSinh));
                    if (check != 0) {
                        Toast.makeText(getContext(), "Thêm Thành Công", Toast.LENGTH_SHORT).show();
                        thanhVienAdapter = new ThanhVienAdapter(getContext(), thanhVienDAO.getDSThanhVien());
                        recyclerViewThanhVien.setAdapter(thanhVienAdapter);
                    } else {
                        Toast.makeText(getContext(), "Thêm Thất Bại!", Toast.LENGTH_SHORT).show();
                    }
                    dialog.dismiss();
                } else {
                    Toast.makeText(getContext(), "Thông tin không được để trống", Toast.LENGTH_SHORT).show();
                    if (ten.isEmpty()) {
                        edHoTen.setError("ID không được để trống");
                    }
                    if (namSinh.isEmpty()) {
                        edNamSinh.setError("Tên loại không được để trống");
                    }

                }
            }
        });

        btnHuyThemThanhVien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }


}