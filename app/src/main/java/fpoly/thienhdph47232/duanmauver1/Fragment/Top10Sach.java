package fpoly.thienhdph47232.duanmauver1.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import fpoly.thienhdph47232.duanmauver1.Adapter.Top10SachAdapter;
import fpoly.thienhdph47232.duanmauver1.DAO.PhieuMuonDAO;
import fpoly.thienhdph47232.duanmauver1.Model.Top10;
import fpoly.thienhdph47232.duanmauver1.R;


public class Top10Sach extends Fragment {
    ListView listView;
    ArrayList<Top10> list;
    Top10SachAdapter top10SachAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_top10_sach, container, false);
        listView = view.findViewById(R.id.lvTop10Sach);
        PhieuMuonDAO phieuMuonDAO = new PhieuMuonDAO(getActivity());
        list = (ArrayList<Top10>) phieuMuonDAO.getTop();
        top10SachAdapter = new Top10SachAdapter(getActivity(), this, list);
        listView.setAdapter(top10SachAdapter);
        return view;
    }
}