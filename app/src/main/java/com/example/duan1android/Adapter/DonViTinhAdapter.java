package com.example.duan1android.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.duan1android.Database.DonViTinhDAO;
import com.example.duan1android.Database.LoaiSanPhamDAO;
import com.example.duan1android.Model.DonViTinh;
import com.example.duan1android.Model.LoaiSanPham;
import com.example.duan1android.R;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public
class DonViTinhAdapter extends BaseAdapter {
    Context context;
    List<DonViTinh> list;
    LinearLayout ln;
    DonViTinhDAO donViTinhDAO;

    public DonViTinhAdapter(Context context, List<DonViTinh> list) {
        this.context = context;
        this.list = list;
    }

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
        final DonViTinh donViTinh = list.get(i);
        donViTinhDAO = new DonViTinhDAO(context);
        viewHolder.tvTen.setText(""+donViTinh.getDonViTinh());
        viewHolder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ln = view.findViewById(R.id.linearLSP);
                long chk = donViTinhDAO.deleteDonVi(donViTinh.getDonViTinh());
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
