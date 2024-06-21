package fpoly.thienhdph47232.duanmauver1.Adapter;

import static android.app.PendingIntent.getActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import fpoly.thienhdph47232.duanmauver1.DAO.LoaiSachDAO;
import fpoly.thienhdph47232.duanmauver1.DAO.SachDAO;
import fpoly.thienhdph47232.duanmauver1.Fragment.QuanLySach;
import fpoly.thienhdph47232.duanmauver1.Model.LoaiSach;
import fpoly.thienhdph47232.duanmauver1.Model.Sach;
import fpoly.thienhdph47232.duanmauver1.R;

public class SachAdapterListView extends ArrayAdapter<Sach> {
    Context context;
    QuanLySach quanLySachFragment;
    LoaiSach loaiSach;
    LoaiSachDAO loaiSachDAO;
    SachDAO sachDAO;
    List<Sach> sachList;
    TextView tvMaSach, tvTenSach, tvGiaThue, tvLoai, tvSoLuongSach;
    ImageView imgDel;
    public SachAdapterListView(@NonNull Context context, QuanLySach quanLySachFragment, List<Sach> sachList) {
        super(context, 0, sachList);
        this.context = context;
        this.quanLySachFragment = quanLySachFragment;
        this.sachList = sachList;
        this.loaiSachDAO = new LoaiSachDAO(context);
        this.sachDAO = new SachDAO(context);
    }
    public SachAdapterListView(@NonNull Context context, View.OnClickListener onClickListener, List<Sach> sachList) {
        super(context, 0, sachList);
        this.context = context;
        this.quanLySachFragment = quanLySachFragment;
        this.sachList = sachList;
        this.loaiSachDAO = new LoaiSachDAO(context);
        this.sachDAO = new SachDAO(context);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if (view == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_sach_list_view, null);
        }

        final Sach item = sachList.get(position);
        if (item != null){
            tvMaSach = view.findViewById(R.id.tvMaSach);
            tvTenSach = view.findViewById(R.id.tvTenSach);
            tvGiaThue = view.findViewById(R.id.tvGiaThue);
            tvSoLuongSach = view.findViewById(R.id.tvSoLuongSach);
            tvLoai = view.findViewById(R.id.tvLoaiSach);
            imgDel = view.findViewById(R.id.imgBtnDeleteSach);

            tvMaSach.setText("Mã Sách: " + item.getMaSach());
            tvTenSach.setText("Tên Sách: " + item.getTenSach());
            tvGiaThue.setText("Giá Thuê: " + item.getGiaThue());
            tvSoLuongSach.setText("Số Lượng: " + item.getSoLuong());

            loaiSachDAO = new LoaiSachDAO(context);
            loaiSach = loaiSachDAO.getID(String.valueOf(item.getMaLoai()));

            if (loaiSach != null) {
                tvLoai.setText("Loại Sách: " + loaiSach.getTenLoai());
            } else {
                tvLoai.setText("Loại Sách: Không tìm thấy");
            }
        }

        tvSoLuongSach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SuaSoLuong(item);
            }
        });

        imgDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // xóa
                Xoa(String.valueOf(item.getMaSach()));
            }
        });
        return view;
    }
    public void SuaSoLuong(final Sach sach){
        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.item_so_luong_sach_dialog);

        TextView tvTenSach = dialog.findViewById(R.id.tvSachCanSuaSoLuong);
        EditText edSoLuongSach = dialog.findViewById(R.id.edSuaSoLuongSach);
        Button btnLuu = dialog.findViewById(R.id.btnLuuSoLuongSach);
        Button btnHuy = dialog.findViewById(R.id.btnHuyLuuSoLuongSach);

        tvTenSach.setText("Sách: " + sach.getTenSach());
        edSoLuongSach.setText(String.valueOf(sach.getSoLuong()));
        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String soLuong = (edSoLuongSach.getText().toString());
                if (!soLuong.isEmpty()){
                    try {
                        int soLuongSach = Integer.parseInt(soLuong);
                        sach.setSoLuong(soLuongSach);
                        if (sachDAO.updateSach(sach)>0){
                            Toast.makeText(context, "Cập Nhật số lượng thành công!", Toast.LENGTH_SHORT).show();
                            quanLySachFragment.capNhatLv();
                            dialog.dismiss();
                        }
                        else {
                            Toast.makeText(context, "Cập Nhật số lượng thất bại!", Toast.LENGTH_SHORT).show();
                        }
                    }catch (NumberFormatException e){
                        Toast.makeText(context, "Số lượng không hợp lệ", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(context, "Bạn phải nhập số lượng", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
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
                quanLySachFragment.capNhatLv();
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
}
