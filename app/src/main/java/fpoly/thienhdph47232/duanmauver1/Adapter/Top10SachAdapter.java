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
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

import fpoly.thienhdph47232.duanmauver1.Fragment.Top10Sach;
import fpoly.thienhdph47232.duanmauver1.Model.Top10;
import fpoly.thienhdph47232.duanmauver1.R;

public class Top10SachAdapter extends ArrayAdapter<Top10> {
    private Context context;
    Top10Sach top10Sach;
    ArrayList<Top10> lists;
    TextView tvSach, tvSoLuong;
    ImageView imgDel;

    public Top10SachAdapter(@NonNull Context context, Fragment fragment, ArrayList<Top10> lists) {
        super(context, 0,lists);
        this.context = context;
        this.top10Sach = top10Sach;
        this.lists = lists;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if (view == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_top_10_sach, null);
        }

        final Top10 item = lists.get(position);
        if (item != null){
            tvSach = view.findViewById(R.id.tvSach);
            tvSach.setText("Sách: " + item.getTenSach());
            tvSoLuong = view.findViewById(R.id.tvSL);
            tvSoLuong.setText("Số Lượng: " + item.getSoLuong());
        }
        return view;
    }

}
