package fpoly.thienhdph47232.duanmauver1.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

import fpoly.thienhdph47232.duanmauver1.DAO.SachDAO;
import fpoly.thienhdph47232.duanmauver1.DAO.ThanhVienDAO;
import fpoly.thienhdph47232.duanmauver1.Fragment.QuanLyPhieuMuon;
import fpoly.thienhdph47232.duanmauver1.Model.PhieuMuon;
import fpoly.thienhdph47232.duanmauver1.Model.Sach;
import fpoly.thienhdph47232.duanmauver1.Model.ThanhVien;
import fpoly.thienhdph47232.duanmauver1.R;

public class PhieuMuonListViewAdapter extends ArrayAdapter<PhieuMuon> implements Filterable {
    private Context context;
    QuanLyPhieuMuon phieuMuonFragment;
    private ArrayList<PhieuMuon> phieuMuonArrayList;
    TextView tvMaPM, tvTenTVMuon, tvTenSach, tvTienThue, tvNgay, tvTraSach, tvGioTao;
    ImageView imgDel;
    SachDAO sachDAO;
    ThanhVienDAO thanhVienDAO;
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
    SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());

    public PhieuMuonListViewAdapter(@NonNull Context context,
                                    QuanLyPhieuMuon phieuMuonFragment, View.OnClickListener onClickListener, ArrayList<PhieuMuon> phieuMuonArrayList) {
        super(context, 0, phieuMuonArrayList);
        this.context = context;
        this.phieuMuonFragment = phieuMuonFragment;
        this.phieuMuonArrayList = phieuMuonArrayList;
    }

    public PhieuMuonListViewAdapter(@NonNull Context context,
                                    QuanLyPhieuMuon phieuMuonFragment, ArrayList<PhieuMuon> phieuMuonArrayList) {
        super(context, 0, phieuMuonArrayList);
        this.context = context;
        this.phieuMuonFragment = phieuMuonFragment;
        this.phieuMuonArrayList = phieuMuonArrayList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_phieu_muon, null);
        }

        final PhieuMuon item = phieuMuonArrayList.get(position);
        if (item != null){
            tvMaPM = view.findViewById(R.id.tvMaPM);
            tvMaPM.setText("Mã Phiếu Mượn: " + item.getMaPhieuMuon());

            sachDAO = new SachDAO(context);
            Sach sach = sachDAO.getID(String.valueOf(item.getMaSach()));
            tvTenSach = view.findViewById(R.id.tvTenSachofPhieuMuon);
            tvTenSach.setText("Tên Sách: " + sach.getTenSach());

            thanhVienDAO= new ThanhVienDAO(context);
            ThanhVien thanhVien = thanhVienDAO.getID(String.valueOf(item.getMaThanhVien()));
            tvTenTVMuon = view.findViewById(R.id.tvTenTVofPhieuMuon);
            tvTenTVMuon.setText("Thành Viên: " + thanhVien.getHoTen());

            tvTienThue = view.findViewById(R.id.tvTienThueSachMuon);
            tvTienThue.setText("Tiền Thuê: " + item.getTienThue());

            tvNgay = view.findViewById(R.id.tvNgayMuon);

            if (item.getNgay() != null){
                tvNgay.setText("Ngày: " + simpleDateFormat.format(item.getNgay()));
            } else {
                tvNgay.setText("Ngày: N/A");
            }

            tvTraSach = view.findViewById(R.id.tvTraSachMuon);
            if (item.getTraSach() == 1){
                tvTraSach.setTextColor(Color.BLUE);
                tvTraSach.setText("Đã Trả Sách");
            } else {
                tvTraSach.setTextColor(Color.RED);
                tvTraSach.setText("Chưa Trả Sách");
            }

            tvGioTao = view.findViewById(R.id.tvGioTao);
            if (item.getGioMuon() != null){
                tvGioTao.setText("Giờ Tạo:  " + timeFormat.format(item.getGioMuon()));
            } else {
                tvNgay.setText("Giờ Tạo: : N/A");
            }

            imgDel = view.findViewById(R.id.imgDeletePM);

        }

        imgDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // gọi phương thức xóa
                showDeleteConfirmationDialog(position);
            }
        });
        return view;
    }
    private void showDeleteConfirmationDialog(int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Xác nhận xóa");
        builder.setMessage("Bạn có chắc muốn xóa mục này?");

        builder.setPositiveButton("Xóa", (dialog, which) -> {
            phieuMuonArrayList.remove(position);
            Toast.makeText(context, "Xóa thành công!", Toast.LENGTH_SHORT).show();
            notifyDataSetChanged();
            dialog.dismiss();
        });

        builder.setNegativeButton("Hủy", (dialog, which) -> dialog.dismiss());

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
