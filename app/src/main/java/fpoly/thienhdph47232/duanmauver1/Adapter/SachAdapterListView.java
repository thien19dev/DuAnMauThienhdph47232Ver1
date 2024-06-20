package fpoly.thienhdph47232.duanmauver1.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

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
    TextView tvMaSach, tvTenSach, tvGiaThue, tvLoai;
    ImageView imgDel;
    public SachAdapterListView(@NonNull Context context, QuanLySach quanLySachFragment, List<Sach> sachList) {
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
            tvLoai = view.findViewById(R.id.tvLoaiSach);
            imgDel = view.findViewById(R.id.imgBtnDeleteSach);

            tvMaSach.setText("Mã Sách: " + item.getMaSach());
            tvTenSach.setText("Tên Sách: " + item.getTenSach());
            tvGiaThue.setText("Giá Thuê: " + item.getGiaThue());

//            LoaiSach loaiSach = loaiSachDAO.getID(String.valueOf(item.getMaLoai()));
//            tvLoai.setText("Loại Sách: " + loaiSach.getTenLoai());
            loaiSachDAO = new LoaiSachDAO(context);
            loaiSach = loaiSachDAO.getID(String.valueOf(item.getMaLoai()));
//            try {
//                loaiSach = loaiSachDAO.getID(String.valueOf(item.getMaLoai()));
//            } catch (IndexOutOfBoundsException e) {
//                // Log error and handle appropriately
//                tvLoai.setText("Loại Sách: Không tìm thấy Ma Loai Sach");
//            }
            if (loaiSach != null) {
                tvLoai.setText("Loại Sách: " + loaiSach.getTenLoai());
            } else {
                tvLoai.setText("Loại Sách: Không tìm thấy");
            }
        }

        imgDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // xóa
                Xoa(String.valueOf(item.getMaSach()));
            }
        });
        return view;
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
