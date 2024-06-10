package fpoly.thienhdph47232.duanmauver1.Adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import fpoly.thienhdph47232.duanmauver1.DAO.SachDAO;
import fpoly.thienhdph47232.duanmauver1.Model.Sach;
import fpoly.thienhdph47232.duanmauver1.R;

public class SachAdapter extends RecyclerView.Adapter<SachAdapter.ViewHolder>{
    private Context context;
    private ArrayList<Sach> sachArrayList;
    private SachDAO sachDAO;

    public SachAdapter(Context context, ArrayList<Sach> sachArrayList) {
        this.context = context;
        this.sachArrayList = sachArrayList;
        this.sachDAO = new SachDAO(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = ((Activity) context).getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.item_sach, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvIDSach.setText("Mã Sách: "+ sachArrayList.get(position).getMaSach());
        holder.tvTenSach.setText("Tên Sách: " + sachArrayList.get(position).getTenSach());
        holder.tvGiaThue.setText("Giá Thuê: " + sachArrayList.get(position).getGiaThue());
        holder.tvMaLoaiSach.setText("Mã Loại Sách: " + sachArrayList.get(position).getMaLoai());

        Sach sach = sachArrayList.get(position);
        holder.ivDeleteSach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteSach(position, sach);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (sachArrayList != null)
            return sachArrayList.size();
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvIDSach, tvTenSach, tvGiaThue, tvMaLoaiSach;
        private ImageView ivEditSach, ivDeleteSach;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvIDSach = itemView.findViewById(R.id.tvIDSach);
            tvTenSach = itemView.findViewById(R.id.tvTenSach);
            tvGiaThue = itemView.findViewById(R.id.tvGiaThue);
            tvMaLoaiSach = itemView.findViewById(R.id.tvMaLoaiSach);

            ivDeleteSach = itemView.findViewById(R.id.ivDeleteSach);
            ivEditSach = itemView.findViewById(R.id.ivDeleteSach);
        }
    }

    public void deleteSach(int maSach, Sach sach){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Bạn có chắc chắn muốn xóa không?");
        builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (sachDAO.deleteSach(sach.getMaSach())){
                    Toast.makeText(context, "Xóa Thành Công", Toast.LENGTH_SHORT).show();
                    sachArrayList.remove(maSach);
                    notifyDataSetChanged();
                } else {
                    Toast.makeText(context, "Xóa Không Thành Công!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        builder.show();
    }
}
