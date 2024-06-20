package fpoly.thienhdph47232.duanmauver1.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

import fpoly.thienhdph47232.duanmauver1.Model.LoaiSach;
import fpoly.thienhdph47232.duanmauver1.R;

public class LoaiSachSpinnerAdapter extends ArrayAdapter<LoaiSach> {
    Context context;
    ArrayList<LoaiSach> loaiSachArrayList;
    TextView tvMaLoaiSach, tvTenLoaiSach;

    public LoaiSachSpinnerAdapter(@NonNull Context context, ArrayList<LoaiSach> loaiSachArrayList) {
        super(context, 0, loaiSachArrayList);
        this.context = context;
        this.loaiSachArrayList = loaiSachArrayList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if (view == null){
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.item_spinner_loai_sach, null);
        }

        final LoaiSach item = loaiSachArrayList.get(position);
        if (item != null) {
            tvMaLoaiSach = view.findViewById(R.id.tvMaLoaiSachSpinner);
            tvMaLoaiSach.setText(item.getMaLoai() + ". ");

            tvTenLoaiSach = view.findViewById(R.id.tvTenLoaiSachSpinner);
            tvTenLoaiSach.setText(item.getTenLoai() + ". ");
        }
        return view;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if (view == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_spinner_loai_sach, null);
        }

        final LoaiSach item = loaiSachArrayList.get(position);
        if (item != null){
            tvMaLoaiSach = view.findViewById(R.id.tvMaLoaiSachSpinner);
            tvMaLoaiSach.setText(item.getMaLoai() + ". ");

            tvTenLoaiSach = view.findViewById(R.id.tvTenLoaiSachSpinner);
            tvTenLoaiSach.setText(item.getTenLoai() + ". ");
        }
        return view;
    }
}
