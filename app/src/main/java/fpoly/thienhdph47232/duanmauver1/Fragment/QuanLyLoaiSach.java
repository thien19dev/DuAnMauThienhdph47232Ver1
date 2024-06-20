package fpoly.thienhdph47232.duanmauver1.Fragment;

import static java.lang.Integer.parseInt;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import fpoly.thienhdph47232.duanmauver1.Adapter.LoaiSachAdapter;
import fpoly.thienhdph47232.duanmauver1.DAO.LoaiSachDAO;
import fpoly.thienhdph47232.duanmauver1.Model.LoaiSach;
import fpoly.thienhdph47232.duanmauver1.R;

public class QuanLyLoaiSach extends Fragment {
    private LoaiSachDAO loaiSachDAO;
    private RecyclerView recyclerViewLoaiSach;
    private LoaiSachAdapter loaiSachAdapter;
    private FloatingActionButton floatAdd;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_quan_ly_loai_sach, container, false);
        //thiết kế giao diện chính + giao diện item
        recyclerViewLoaiSach = view.findViewById(R.id.recycleViewLoaiSach);
        floatAdd = view.findViewById(R.id.floatAddLoaiSach);
//        data
        loaiSachDAO = new LoaiSachDAO(getContext());
        ArrayList<LoaiSach> list = loaiSachDAO.getDSLoaiSach();
//        adapter
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerViewLoaiSach.setLayoutManager(linearLayoutManager);
        loaiSachAdapter = new LoaiSachAdapter(getContext(), list);
        recyclerViewLoaiSach.setAdapter(loaiSachAdapter);
        
        floatAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ThemLoaiSachDialog();
            }
        });
        return view;
    }

    private void ThemLoaiSachDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.add_loai_sach_dialog, null);
        builder.setView(dialogView);
        TextView editTextId = dialogView.findViewById(R.id.edIdLoaiSach);
        EditText editTextName = dialogView.findViewById(R.id.edTenLoaiSach);
        Button buttonAdd = dialogView.findViewById(R.id.buttonAdd);
        Button buttonCancel = dialogView.findViewById(R.id.buttonCancel);
        AlertDialog dialog = builder.create();
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                String id = editTextId.getText().toString();
                String name = editTextName.getText().toString();
                if (!name.isEmpty()) {
                    long check = loaiSachDAO.insert(new LoaiSach(name));
                    if (check!=0){
                        Toast.makeText(getContext(), "Thêm Thành Công!", Toast.LENGTH_SHORT).show();
                        loaiSachAdapter = new LoaiSachAdapter(getContext(), loaiSachDAO.getDSLoaiSach());
                        recyclerViewLoaiSach.setAdapter(loaiSachAdapter);
                    } else {
                        Toast.makeText(getContext(), "Thêm Thất Bại!", Toast.LENGTH_SHORT).show();
                    }
                    dialog.dismiss();
                }
//                if (!id.isEmpty() && !name.isEmpty()) {
//                     long check = loaiSachDAO.insert(new LoaiSach(name));
//                     if (check!=0){
//                         Toast.makeText(getContext(), "Thêm Thành Công!", Toast.LENGTH_SHORT).show();
//                         loaiSachAdapter = new LoaiSachAdapter(getContext(), loaiSachDAO.getDSLoaiSach());
//                         recyclerViewLoaiSach.setAdapter(loaiSachAdapter);
//                     } else {
//                         Toast.makeText(getContext(), "Thêm Thất Bại!", Toast.LENGTH_SHORT).show();
//                     }
//                    dialog.dismiss();
//                } else {
//                    if (id.isEmpty()) {
//                        editTextId.setError("ID không được để trống");
//                    }
//                    if (name.isEmpty()) {
//                        editTextName.setError("Tên loại không được để trống");
//                    }
//                }
            }
        });
        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}