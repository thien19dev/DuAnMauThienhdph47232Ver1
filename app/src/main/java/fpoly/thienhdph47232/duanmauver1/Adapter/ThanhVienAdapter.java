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

import fpoly.thienhdph47232.duanmauver1.DAO.ThanhVienDAO;
import fpoly.thienhdph47232.duanmauver1.Model.ThanhVien;
import fpoly.thienhdph47232.duanmauver1.R;

public class ThanhVienAdapter extends RecyclerView.Adapter<ThanhVienAdapter.ViewHolder>{
    private Context context;
    private ArrayList<ThanhVien> thanhVienList;
    private ThanhVienDAO thanhVienDAO;

    public ThanhVienAdapter(Context context, ArrayList<ThanhVien> list) {
        this.context = context;
        this.thanhVienList = list;
        this.thanhVienDAO = new ThanhVienDAO(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = ((Activity) context).getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.item_thanh_vien, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvIDThanhVien.setText("ID: " + thanhVienList.get(position).getMaThanhVien());
        holder.tvTenThanhVien.setText("Tên: " + thanhVienList.get(position).getHoTen());
        holder.tvNamSinh.setText("Năm Sinh: " + thanhVienList.get(position).getNamSinh());

        ThanhVien thanhVien = thanhVienList.get(position);
        holder.ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteThanhVien(position, thanhVien);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (thanhVienList!=null){
            return thanhVienList.size();
        } else {
            return 0;
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView tvIDThanhVien, tvTenThanhVien, tvNamSinh;
        private ImageView ivEdit, ivDelete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvIDThanhVien = itemView.findViewById(R.id.tvIDThanhVien);
            tvTenThanhVien = itemView.findViewById(R.id.tvTenThanhVien);
            tvNamSinh = itemView.findViewById(R.id.tvNamSinh);
            ivDelete = itemView.findViewById(R.id.ivDeleteThanhVien);
            ivEdit = itemView.findViewById(R.id.ivEditThanhVien);
        }
    }
    public void deleteThanhVien(int maTV, ThanhVien thanhVien){
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
                if (thanhVienDAO.deleteThanhVien(thanhVien.getMaThanhVien())){
                    Toast.makeText(context, "Xóa Thành Công", Toast.LENGTH_SHORT).show();
                    thanhVienList.remove(maTV);
                    notifyDataSetChanged();
                } else {
                    Toast.makeText(context, "Xóa Không Thành Công!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        builder.show();
    }
}
