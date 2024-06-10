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


import fpoly.thienhdph47232.duanmauver1.Model.Sach;
import fpoly.thienhdph47232.duanmauver1.Model.ThanhVien;
import fpoly.thienhdph47232.duanmauver1.R;

public class ThanhVienSpinnerAdapter extends ArrayAdapter<ThanhVien> {
    private Context context;
    private ArrayList<ThanhVien> lists;

    TextView tvmaTV, tvtenTV;

    public ThanhVienSpinnerAdapter(@NonNull Context context, ArrayList<ThanhVien> lists) {
        super(context, 0, lists);
        this.context = context;
        this.lists = lists;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if (view == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_spinner_thanh_vien, null);
        }
        final ThanhVien item = lists.get(position);
        if (item != null){
            tvmaTV = view.findViewById(R.id.tvMaTVSp);
            tvmaTV.setText(item.getMaThanhVien()+ ". ");
            tvtenTV = view.findViewById(R.id.tvTenTVSp);
            tvtenTV.setText(item.getHoTen());
        }
        return view;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if (view == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_spinner_thanh_vien, null);
        }
        final ThanhVien item = lists.get(position);
        if (item != null){
            tvmaTV = view.findViewById(R.id.tvMaTVSp);
            tvmaTV.setText(item.getMaThanhVien()+ ". ");
            tvtenTV = view.findViewById(R.id.tvTenTVSp);
            tvtenTV.setText(item.getHoTen());
        }
        return view;
    }
}
