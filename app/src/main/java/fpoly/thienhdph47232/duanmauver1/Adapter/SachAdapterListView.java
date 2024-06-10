package fpoly.thienhdph47232.duanmauver1.Adapter;

import android.content.Context;
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
import fpoly.thienhdph47232.duanmauver1.Fragment.QuanLySach;
import fpoly.thienhdph47232.duanmauver1.Model.LoaiSach;
import fpoly.thienhdph47232.duanmauver1.Model.Sach;
import fpoly.thienhdph47232.duanmauver1.R;

public class SachAdapterListView extends ArrayAdapter<Sach> {
    Context context;
    QuanLySach quanLySachFragment;
    List<Sach> sachList;
    TextView tvMaSach, tvTenSach, tvGiaThue, tvLoai;
    ImageView imgDel;
    public SachAdapterListView(@NonNull Context context, QuanLySach quanLySachFragment, List<Sach> sachList) {
        super(context, 0, sachList);
        this.context = context;
        this.quanLySachFragment = quanLySachFragment;
        this.sachList = sachList;
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
            LoaiSachDAO loaiSachDAO = new LoaiSachDAO(context);
            LoaiSach loaiSach = loaiSachDAO.getID(String.valueOf(item.getMaLoai()));
            tvMaSach = view.findViewById(R.id.tvMaSach);
            tvTenSach = view.findViewById(R.id.tvTenSach);
            tvGiaThue = view.findViewById(R.id.tvGiaThue);
            tvLoai = view.findViewById(R.id.tvLoaiSach);
            imgDel = view.findViewById(R.id.imgBtnDeleteSach);

            tvMaSach.setText("Mã Sách: " + item.getMaSach());
            tvTenSach.setText("Tên Sách: " + item.getTenSach());
            tvGiaThue.setText("Giá Thuê: " + item.getGiaThue());
            tvLoai.setText("Loại Sách: " + loaiSach.getTenLoai());
        }

        imgDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // xóa
                quanLySachFragment.Xoa(String.valueOf(item.getMaSach()));
            }
        });
        return view;
    }
}
