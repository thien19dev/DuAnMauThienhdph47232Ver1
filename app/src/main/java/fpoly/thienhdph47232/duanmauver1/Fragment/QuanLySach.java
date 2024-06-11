package fpoly.thienhdph47232.duanmauver1.Fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import fpoly.thienhdph47232.duanmauver1.Adapter.LoaiSachSpinnerAdapter;
import fpoly.thienhdph47232.duanmauver1.Adapter.SachAdapter;
import fpoly.thienhdph47232.duanmauver1.Adapter.SachAdapterListView;
import fpoly.thienhdph47232.duanmauver1.DAO.LoaiSachDAO;
import fpoly.thienhdph47232.duanmauver1.DAO.SachDAO;
import fpoly.thienhdph47232.duanmauver1.Model.LoaiSach;
import fpoly.thienhdph47232.duanmauver1.Model.Sach;
import fpoly.thienhdph47232.duanmauver1.R;

public class QuanLySach extends Fragment {

//    private RecyclerView recyclerViewSach;
    private SachAdapterListView sachAdapter;
    private FloatingActionButton floatAddSach;
    private ListView lvSach;
    private SachDAO sachDAO;
    private List<Sach> sachList;
    private LoaiSachSpinnerAdapter loaiSachSpinnerAdapter;
    Dialog dialog;
    Spinner LoaiSachspinner;
    EditText edMaSach, edTenSach, edGiaThue;
    Button btnAddSach, btnCancelAddSach;
    ArrayList<LoaiSach> loaiSachArrayList;
    LoaiSachDAO loaiSachDAO;
    LoaiSach loaiSach;
    int maLoaiSach, position;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_quan_ly_sach, container, false);

        floatAddSach = view.findViewById(R.id.fabSach);
        lvSach = view.findViewById(R.id.lvSach);
        sachDAO = new SachDAO(getActivity());
        capNhatLv();
        
        floatAddSach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Thêm!", Toast.LENGTH_SHORT).show();
                OpenDialog(getActivity(), 0);
            }
        });

        lvSach.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Sach item = sachList.get(position);
                OpenDialog(getActivity(), 1);
                return false;
            }
        });
        return view;
    }

    public void capNhatLv(){
        sachList = (List<Sach>) sachDAO.getAll();
        sachAdapter = new SachAdapterListView(getActivity(), this, sachList);
        lvSach.setAdapter(sachAdapter);
    }

    public void Xoa(final String id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Delete");
        builder.setMessage("Bạn có muốn Xóa Không?");
        builder.setCancelable(true);

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                sachDAO.deleteSach(Integer.parseInt(id));
                capNhatLv();
                dialog.cancel();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }
    public void OpenDialog(final Context context, final int type){
        dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.add_item_sach_dialog);
        TextView tvTitleSach = dialog.findViewById(R.id.tvTitleSach);
        edMaSach = dialog.findViewById(R.id.edThemMaSach);
        edTenSach = dialog.findViewById(R.id.edThemTenSach);
        edGiaThue = dialog.findViewById(R.id.edThemGiaThueSach);
        LoaiSachspinner = dialog.findViewById(R.id.spLoaiSach);
        btnAddSach = dialog.findViewById(R.id.btnSaveSach);
        btnCancelAddSach = dialog.findViewById(R.id.btnCancelSaveSach);
        Sach item = sachList.get(position);


        loaiSachArrayList = new ArrayList<LoaiSach>();
        loaiSachDAO = new LoaiSachDAO(getContext());
        loaiSachArrayList = (ArrayList<LoaiSach>) loaiSachDAO.getAll();

        loaiSachSpinnerAdapter = new LoaiSachSpinnerAdapter(getContext(), loaiSachArrayList);
        LoaiSachspinner.setAdapter(loaiSachSpinnerAdapter);

        LoaiSachspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                maLoaiSach = loaiSachArrayList.get(position).getMaLoai();
                Toast.makeText(getContext(), "Chọn: " + loaiSachArrayList.get(position).getTenLoai(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        edMaSach.setEnabled(false);
        if (type != 0){
            tvTitleSach.setText("Cập Nhật Thông Tin Sách");
            edMaSach.setText(String.valueOf(item.getMaSach()));
            edTenSach.setText(item.getTenSach());
            edGiaThue.setText(String.valueOf(item.getGiaThue()));
            for (int i = 0; i < loaiSachArrayList.size(); i++){
                if (item.getMaLoai() == (loaiSachArrayList.get(i).getMaLoai())){
                    position = i;
                }
                Log.i("demo", "posSach " + position);
                LoaiSachspinner.setSelection(position);
            }
        }

        btnCancelAddSach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        btnAddSach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edMaSach.setText(String.valueOf(item.getMaSach()));
                edTenSach.setText(item.getTenSach());
                edGiaThue.setText(String.valueOf(item.getGiaThue()));
                if (vadidate() > 0){
                    if (type == 0){
                        if (sachDAO.insertSach(item)>0){
                            Toast.makeText(context, "Thêm Thành Công!", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(context, "Thêm Thất Bại!", Toast.LENGTH_SHORT).show();

                    }
                } else {
                    item.setMaSach(Integer.parseInt(edMaSach.getText().toString()));
                    if (sachDAO.updateSach(item) > 0){
                        Toast.makeText(context, "Sửa Thành Công!", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(context, "Sửa Thất Bại!", Toast.LENGTH_SHORT).show();
                    }
                }
                capNhatLv();
                dialog.dismiss();
            }
        });
        dialog.show();

    }
    public int vadidate(){
        int check = 1;
        if (edTenSach.getText().length() == 0 || edGiaThue.getText().length() == 0) {
            Toast.makeText(getContext(), "Bạn Phải Nhập Đủ Thông Tin!", Toast.LENGTH_SHORT).show();
            check = -1;
        }
        return check;
    }

}