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
import fpoly.thienhdph47232.duanmauver1.R;

public class SachSpinnerAdapter extends ArrayAdapter<Sach> {
    private Context context;
    private ArrayList<Sach> lists;

    TextView tvmaSach, tvtenSach;

    public SachSpinnerAdapter(Context context, ArrayList<Sach> list) {
        super(context, 0 , list);
        this.context = context;
        this.lists = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if (view == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_spinner_sach, null);
        }
        final Sach item = lists.get(position);
        if (item != null){
            tvmaSach = view.findViewById(R.id.tvMaSachSp);
            tvmaSach.setText(item.getMaSach() + ". ");
            tvtenSach = view.findViewById(R.id.tvTenSachSp);
            tvtenSach.setText(item.getTenSach());
        }


        return view;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if (view == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_spinner_sach, null);
        }
        final Sach item = lists.get(position);
        if (item != null){
            tvmaSach = view.findViewById(R.id.tvMaSachSp);
            tvmaSach.setText(item.getMaSach() + ". ");
            tvtenSach = view.findViewById(R.id.tvTenSachSp);
            tvtenSach.setText(item.getTenSach());
        }


        return view;
    }
}
