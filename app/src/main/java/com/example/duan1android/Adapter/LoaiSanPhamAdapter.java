package com.example.duan1android.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.duan1android.Database.LoaiSanPhamDAO;
import com.example.duan1android.Model.LoaiSanPham;
import com.example.duan1android.R;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public
class LoaiSanPhamAdapter extends BaseAdapter {
    Context context;
    List<LoaiSanPham> list;
    LinearLayout ln;

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if(view==null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_loai_san_pham, viewGroup, false);
            viewHolder = new ViewHolder();
            viewHolder.imgDelete = view.findViewById(R.id.imgDeleteLSP);
            viewHolder.imgUpdate = view.findViewById(R.id.imgUpdateLSP);
            viewHolder.tvTen = view.findViewById(R.id.tvTenLSP);
            view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) view.getTag();
        }
        final LoaiSanPhamDAO loaiSanPhamDAO = new LoaiSanPhamDAO(context);
        final LoaiSanPham loaiSanPham = list.get(i);
        viewHolder.tvTen.setText(""+loaiSanPham.getTenLoai());
        viewHolder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ln = view.findViewById(R.id.linearLSP);
                long chk = loaiSanPhamDAO.deleteLoaiSanPham(loaiSanPham.getMaLoai());
                if(chk>0){
                    Snackbar.make(ln,"Xóa thành công", BaseTransientBottomBar.LENGTH_SHORT).show();
                    list.remove(i);
                    notifyDataSetChanged();
                }else {
                    Snackbar.make(ln,"Xóa không thành công", BaseTransientBottomBar.LENGTH_SHORT).show();
                }
            }
        });
        viewHolder.imgUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        return view;
    }
    private class ViewHolder{
        TextView tvTen;
        ImageView imgDelete,imgUpdate;
    }
}
