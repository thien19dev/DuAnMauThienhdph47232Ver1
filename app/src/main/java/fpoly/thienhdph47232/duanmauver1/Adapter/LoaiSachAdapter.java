package fpoly.thienhdph47232.duanmauver1.Adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Service;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import fpoly.thienhdph47232.duanmauver1.DAO.LoaiSachDAO;
import fpoly.thienhdph47232.duanmauver1.DAO.ThanhVienDAO;
import fpoly.thienhdph47232.duanmauver1.Model.LoaiSach;
import fpoly.thienhdph47232.duanmauver1.Model.ThanhVien;
import fpoly.thienhdph47232.duanmauver1.R;

public class LoaiSachAdapter extends RecyclerView.Adapter<LoaiSachAdapter.ViewHolder>{
    private Context context;
    private ArrayList<LoaiSach> loaiSachlist;
    public LoaiSachDAO loaiSachDAO;

    public LoaiSachAdapter(Context context, ArrayList<LoaiSach> list) {
        this.context = context;
        this.loaiSachlist = list;
        this.loaiSachDAO = new LoaiSachDAO(context);
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
        holder.tvIDLoaiSach.setText("ID: " + loaiSachlist.get(position).getMaLoai());
        holder.tvTenLoaiSach.setText("Tên Loại Sách: " + loaiSachlist.get(position).getTenLoai());

        LoaiSach loaiSach = loaiSachlist.get(position);
        holder.imgBtnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDeleteConfirmationDialog(position, loaiSach);
            }
        });
        holder.imgBtnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showUpdateLoaiSachDialog(loaiSach);
            }
        });
    }

    @Override
    public int getItemCount() {
        return loaiSachlist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvIDLoaiSach, tvTenLoaiSach;
        ImageView imgBtnEdit, imgBtnDelete;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvIDLoaiSach = itemView.findViewById(R.id.tvIDLoaiSach);
            tvTenLoaiSach = itemView.findViewById(R.id.tvTenLoaiSach);
            imgBtnEdit = itemView.findViewById(R.id.imgBtnEditLoaiSach);
            imgBtnDelete = itemView.findViewById(R.id.imgBtnDeleteLoaiSach);
        }
    }
    private void showDeleteConfirmationDialog(int maLoai, LoaiSach loaiSach) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage("Bạn có muốn xóa item này không?");
        builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // Xóa item từ database
                if (loaiSachDAO.delete(loaiSach.getMaLoai())){
                    Toast.makeText(context, "Xóa Loại Sách Thành Công!", Toast.LENGTH_SHORT).show();
                    loaiSachlist.remove(maLoai);
                    notifyDataSetChanged();
                } else {
                    Toast.makeText(context, "Xóa Không Thành Công!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void showUpdateLoaiSachDialog(LoaiSach loaiSach) {
        // Tạo view từ layout dialog
        Dialog builder = new Dialog(context);
        View view = LayoutInflater.from(context).inflate(R.layout.update_item_loai_sach, null);
        builder.setContentView(view);

        // Tham chiếu tới các thành phần trong view
        EditText etMaLoai = view.findViewById(R.id.edMaLoai);
        EditText etTenLoai = view.findViewById(R.id.edTenLoai);
        Button btnUpdate = view.findViewById(R.id.btnUpdateLoaiSach);
        Button btnCancel = view.findViewById(R.id.btnCancalUpdateLoaiSach);

        // Thiết lập giá trị ban đầu cho các thành phần
        etMaLoai.setText(String.valueOf(loaiSach.getMaLoai()));
        etTenLoai.setText(loaiSach.getTenLoai());

        //

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tenLoaiSach = etTenLoai.getText().toString();
                if (tenLoaiSach.isEmpty()){
                    Toast.makeText(context, "Tên loại sách không được để trống!!!", Toast.LENGTH_SHORT).show();
                } else {
                    loaiSach.setTenLoai(tenLoaiSach);
                    LoaiSach loaiSach = new LoaiSach(tenLoaiSach);
                    loaiSachDAO.updateLoaiSach(loaiSach);
                    notifyDataSetChanged();
                    builder.dismiss();
                }
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder.dismiss();
            }
        });
        builder.show();





//        // Tạo dialog
//        AlertDialog.Builder builder = new AlertDialog.Builder(context);
//        builder.setView(view)
//                .setTitle("Cập nhật Loại Sách")
//                .setPositiveButton("Cập nhật", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        // Lấy dữ liệu từ EditText
//                        String tenLoai = etTenLoai.getText().toString();
//                        if (tenLoai == null){
//                            Toast.makeText(context, "Tên Loại Không Được Để Trống!!!", Toast.LENGTH_SHORT).show();
//                        } else {
//                            LoaiSach loaiSach = new LoaiSach(tenLoai);
//                            loaiSachDAO.updateLoaiSach(loaiSach);
//                            notifyDataSetChanged();
//                            builder.
//
//                        }
//
//                    }
//                })
//                .setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        dialog.dismiss();
//                    }
//                });
//
//        // Hiển thị dialog
//        AlertDialog dialog = builder.create();
//        dialog.show();
    }


}
