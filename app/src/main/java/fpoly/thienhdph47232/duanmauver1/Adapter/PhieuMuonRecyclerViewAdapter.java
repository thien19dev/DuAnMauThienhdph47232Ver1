package fpoly.thienhdph47232.duanmauver1.Adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import fpoly.thienhdph47232.duanmauver1.DAO.PhieuMuonDAO;
import fpoly.thienhdph47232.duanmauver1.Model.PhieuMuon;
import fpoly.thienhdph47232.duanmauver1.R;

public class PhieuMuonRecyclerViewAdapter extends RecyclerView.Adapter<PhieuMuonRecyclerViewAdapter.PhieuMuonViewHolder> {

    private Context context;
    private List<PhieuMuon> list;
    private PhieuMuonDAO phieuMuonDAO;
    private AlertDialog.Builder alert;
    private LayoutInflater layoutInflater;

    public PhieuMuonRecyclerViewAdapter(Context context, List<PhieuMuon> list) {
        this.context = context;
        this.list = list;
        phieuMuonDAO = new PhieuMuonDAO(context);
        alert = new AlertDialog.Builder(context);
        layoutInflater = ((Activity) context).getLayoutInflater();
    }

    @NonNull
    @Override
    public PhieuMuonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_phieumuon_recyclerview, parent, false);
        return new PhieuMuonRecyclerViewAdapter.PhieuMuonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PhieuMuonViewHolder holder, int position) {
        PhieuMuon phieuMuon = list.get(position);
        holder.maPhieu.setText(phieuMuon.getMaPhieuMuon());
        holder.thanhVien.setText(phieuMuon.getMaThanhVien());

    }

    @Override
    public int getItemCount() {
        if(list != null) {
            return  list.size();
        } else {
            return 0;
        }
    }

    public static class PhieuMuonViewHolder extends RecyclerView.ViewHolder {
        TextView maPhieu, thanhVien, tenSach, tienThue, status, ngayThue;
        ImageView btnDelete;
        public PhieuMuonViewHolder(@NonNull View itemView) {
            super(itemView);
            maPhieu = itemView.findViewById(R.id.maPhieu);
            thanhVien = itemView.findViewById(R.id.thanhVien);
            tenSach = itemView.findViewById(R.id.tenSach);
            tienThue = itemView.findViewById(R.id.tienThue);
            status = itemView.findViewById(R.id.status);
            ngayThue = itemView.findViewById(R.id.ngayThue);
            btnDelete = itemView.findViewById(R.id.btn_delete_item);
        }
    }
}
