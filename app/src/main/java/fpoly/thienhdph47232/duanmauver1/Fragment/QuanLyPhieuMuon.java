package fpoly.thienhdph47232.duanmauver1.Fragment;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import fpoly.thienhdph47232.duanmauver1.Adapter.PhieuMuonListViewAdapter;
import fpoly.thienhdph47232.duanmauver1.Adapter.SachSpinnerAdapter;
import fpoly.thienhdph47232.duanmauver1.Adapter.ThanhVienSpinnerAdapter;
import fpoly.thienhdph47232.duanmauver1.DAO.PhieuMuonDAO;
import fpoly.thienhdph47232.duanmauver1.DAO.SachDAO;
import fpoly.thienhdph47232.duanmauver1.DAO.ThanhVienDAO;
import fpoly.thienhdph47232.duanmauver1.Model.PhieuMuon;
import fpoly.thienhdph47232.duanmauver1.Model.Sach;
import fpoly.thienhdph47232.duanmauver1.Model.ThanhVien;
import fpoly.thienhdph47232.duanmauver1.R;


public class QuanLyPhieuMuon extends Fragment {

    private PhieuMuonDAO phieuMuonDAO;
    ArrayList<PhieuMuon> phieuMuonList;
    private FloatingActionButton floatAddPhieuMuon;

    ListView lvPhieuMuon;
    PhieuMuonListViewAdapter phieuMuonListViewAdapter;
    PhieuMuon item;

    Dialog dialog;
    Spinner spTV, spSach;
    EditText edMaPM;
    CheckBox chkTraSach;
    Button btnLuuPhieuMuon, btnHuyLuuPhieuMuon;
    TextView tvTienThueSach;
    TextView tvNgayThueSach;

    ThanhVienSpinnerAdapter thanhVienSpinnerAdapter;
    ArrayList<ThanhVien> listThanhVien;
    ThanhVienDAO thanhVienDAO;
    ThanhVien thanhVien;
    int maThanhVien;

    SachSpinnerAdapter sachSpinnerAdapter;
    ArrayList<Sach> listSach;
    SachDAO sachDAO;

    int maSach;
    int tienThue;
    int positionTV, positionSach;
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_quan_ly_phieu_muon, container, false);

        
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        

        lvPhieuMuon = view.findViewById(R.id.lvQuanLyPhieuMuon);
        floatAddPhieuMuon = view.findViewById(R.id.fabPhieuMuon);
        phieuMuonDAO = new PhieuMuonDAO(getActivity());

        floatAddPhieuMuon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog(getActivity(),0);
            }
        });

        lvPhieuMuon.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                item = phieuMuonList.get(position);
                openDialog(getActivity(), 1); // update
                return false;
            }
        });
        CapNhatLV();
        return view;
    }

    void CapNhatLV(){
        phieuMuonList = (ArrayList<PhieuMuon>) phieuMuonDAO.getAll();
        phieuMuonListViewAdapter = new PhieuMuonListViewAdapter(getActivity(), this, phieuMuonList);
        lvPhieuMuon.setAdapter(phieuMuonListViewAdapter);
    }

    protected void openDialog(final Context context, final int type){
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.add_phieu_muon_dialog);
        edMaPM = dialog.findViewById(R.id.edMaPM);
        spTV = dialog.findViewById(R.id.spMaTV);
        spSach = dialog.findViewById(R.id.spMaSach);
        tvTienThueSach = dialog.findViewById(R.id.tvTienThueSach);
        tvNgayThueSach = dialog.findViewById(R.id.tvNgayThueSach);

        // set ngày thuê, mặc định ngày hiện hành

        tvNgayThueSach.setText("Ngày Thuê: " + simpleDateFormat.format(new Date()));



        chkTraSach = dialog.findViewById(R.id.chkTraSach);
        btnLuuPhieuMuon = dialog.findViewById(R.id.btnLuuPhieuMuon);
        btnHuyLuuPhieuMuon = dialog.findViewById(R.id.btnHuyLuuPhieuMuon);

        thanhVienDAO = new ThanhVienDAO(context);
        listThanhVien = (ArrayList<ThanhVien>) thanhVienDAO.getAll();
        thanhVienSpinnerAdapter = new ThanhVienSpinnerAdapter(context, listThanhVien);
        spTV.setAdapter(thanhVienSpinnerAdapter);
        spTV.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                maThanhVien = listThanhVien.get(position).getMaThanhVien();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        sachDAO = new SachDAO(context);
        listSach = new ArrayList<Sach>();
        listSach = (ArrayList<Sach>) sachDAO.getAll();
        sachSpinnerAdapter = new SachSpinnerAdapter(context, listSach);
        spSach.setAdapter(sachSpinnerAdapter);
        spSach.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                maSach = listSach.get(position).getMaSach();
                tienThue = listSach.get(position).getGiaThue();
                tvTienThueSach.setText("Tiền Thuê: " + tienThue);
//                Toast.makeText(context, "Chọn " + listSach.get(position).getTenSach(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        // edit -- set data lên form
        if (type != 0){
            edMaPM.setText(String.valueOf(item.getMaPhieuMuon()));
            for (int i = 0; i < listThanhVien.size(); i++) {
                if (item.getMaThanhVien() == (listThanhVien.get(i).getMaThanhVien())){
                    positionTV = i;
                }
                spTV.setSelection(positionTV);
            }
            for (int i = 0; i < listThanhVien.size(); i++) {
                if (item.getMaSach() == (listSach.get(i).getMaSach())){
                    positionSach = i;
                }
                spSach.setSelection(positionSach);
            }
//            tvNgayThueSach.setText("Ngày Thuê: " + simpleDateFormat.format(item.getNgay()));
            tvTienThueSach.setText("Tiền Thuê: " + item.getTienThue());

            if (item.getTraSach() == 1){
                chkTraSach.setChecked(true);
            } else {
                chkTraSach.setChecked(false);
            }
        }
        btnHuyLuuPhieuMuon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btnLuuPhieuMuon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item = new PhieuMuon();
                item.setMaSach(maSach);
                item.setMaThanhVien(maThanhVien);
                item.setNgay(new Date());
                item.setTienThue(tienThue);
                if (chkTraSach.isChecked()){
                    item.setTraSach(1);
                } else {
                    item.setTraSach(0);
                }

                if (type == 0) {
                    // type = 0 ---> insert
                    if (phieuMuonDAO.insertPhieuMuon(item) >0){
                        Toast.makeText(context, "Thêm Thành Công!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(context, "Thêm Thất Bại!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    // type = 1 ---> update
                    item.setMaPhieuMuon(Integer.parseInt(edMaPM.getText().toString()));
                    if (phieuMuonDAO.updatePhieuMuon(item) >0){
                        Toast.makeText(context, "Update Thành Công!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(context, "Update Thất Bại!", Toast.LENGTH_SHORT).show();
                    }
                }
                CapNhatLV();

                dialog.dismiss();
            }
        });
        dialog.show();
    }

}