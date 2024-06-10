package fpoly.thienhdph47232.duanmauver1.Adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import fpoly.thienhdph47232.duanmauver1.DAO.ThanhVienDAO;
import fpoly.thienhdph47232.duanmauver1.Model.LoaiSach;
import fpoly.thienhdph47232.duanmauver1.Model.ThanhVien;
import fpoly.thienhdph47232.duanmauver1.R;

public class LoaiSachAdapter extends RecyclerView.Adapter<LoaiSachAdapter.ViewHolder>{
    private Context context;
    private ArrayList<LoaiSach> list;
    public ThanhVienDAO thanhVienDAO;

    public LoaiSachAdapter(Context context, ArrayList<LoaiSach> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = ((Activity)context).getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.item_loai_sach, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvIDLoaiSach.setText("ID: " + list.get(position).getMaLoai());
        holder.tvTenLoaiSach.setText("Tên Loại Sách: " + list.get(position).getTenLoai());
        holder.imgBtnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvIDLoaiSach, tvTenLoaiSach;
        ImageView imgBtnEdit, imgBtnDelete;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvIDLoaiSach = itemView.findViewById(R.id.tvIDLoaiSach);
            tvTenLoaiSach = itemView.findViewById(R.id.tvTenLoaiSach);
            imgBtnEdit = itemView.findViewById(R.id.imgBtnEdit);
            imgBtnDelete = itemView.findViewById(R.id.imgBtnDelete);
        }
    }


}
