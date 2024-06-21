package fpoly.thienhdph47232.duanmauver1.Adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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
        holder.tvSoTaiKhoan.setText("Số Tài Khoản: " + thanhVienList.get(position).getSoTaiKhoan());

        if (thanhVienList.get(position).getSoTaiKhoan() % 5 == 0 ){
            holder.tvSoTaiKhoan.setTypeface(null, Typeface.BOLD);
        } else {
            holder.tvSoTaiKhoan.setTypeface(null, Typeface.NORMAL);
        }

        ThanhVien thanhVien = thanhVienList.get(position);
        holder.ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteThanhVien(position, thanhVien);
            }
        });
        holder.ivEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateThanhVien(thanhVien);
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

        private TextView tvIDThanhVien, tvTenThanhVien, tvNamSinh, tvSoTaiKhoan;
        private ImageView ivEdit, ivDelete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvIDThanhVien = itemView.findViewById(R.id.tvIDThanhVien);
            tvTenThanhVien = itemView.findViewById(R.id.tvTenThanhVien);
            tvNamSinh = itemView.findViewById(R.id.tvNamSinh);
            tvSoTaiKhoan = itemView.findViewById(R.id.tvSoTaiKhoan);
            ivDelete = itemView.findViewById(R.id.ivDeleteThanhVien);
            ivEdit = itemView.findViewById(R.id.ivEditThanhVien);

        }
    }

    void updateThanhVien(ThanhVien thanhVien) {
        Dialog builder = new Dialog(context);
        View view = LayoutInflater.from(context).inflate(R.layout.update_thanh_vien_dialog, null);
        builder.setContentView(view);
        TextView idTVUpdate = view.findViewById(R.id.idTVUpdate);
        EditText edUpdateTenThanhVien = view.findViewById(R.id.edUpdateTenThanhVien);
        EditText edUpdateNamSinhThanhVien = view.findViewById(R.id.edUpdateNamSinhThanhVien);
        Button btnLuuThanhVien = view.findViewById(R.id.btnLuuThanhVien);
        Button btnHuyLuuThanhVien = view.findViewById(R.id.btnHuyLuuThanhVien);

        idTVUpdate.setText(String.valueOf(thanhVien.getMaThanhVien()));
        edUpdateTenThanhVien.setText(thanhVien.getHoTen());
        edUpdateNamSinhThanhVien.setText(thanhVien.getNamSinh());

        btnLuuThanhVien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ten = edUpdateTenThanhVien.getText().toString();
                String namSinh = edUpdateNamSinhThanhVien.getText().toString();

                if (ten.isEmpty() || namSinh.isEmpty()) {
                    Toast.makeText(context, "Tên Và Năm Sinh Không Được Để Trống!", Toast.LENGTH_SHORT).show();
                } else {
                    thanhVien.setHoTen(ten);
                    thanhVien.setNamSinh(namSinh);

                    ThanhVien thanhVien = new ThanhVien(ten, namSinh );
                    thanhVienDAO.updateThanhVien(thanhVien);
                    notifyDataSetChanged();
                    builder.dismiss();
                }
            }
        });
        btnHuyLuuThanhVien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder.dismiss();
            }
        });
        builder.show();

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
