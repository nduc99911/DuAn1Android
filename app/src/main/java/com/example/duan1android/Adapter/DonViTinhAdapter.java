package com.example.duan1android.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

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
    DonViTinhDAO donViTinhDAO;
    Dialog dialog;
    EditText edSua;

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
                long chk = donViTinhDAO.deleteDonVi(donViTinh.getDonViTinh());
                if(chk>0){
                    Toast.makeText(context,"Xóa thành công",Toast.LENGTH_SHORT).show();
                    list.remove(i);
                    notifyDataSetChanged();
                }else {
                    Toast.makeText(context,"Xóa không thành công",Toast.LENGTH_SHORT).show();
                }
            }
        });
        viewHolder.imgUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog = new Dialog(context, android.R.style.Theme_NoTitleBar_Fullscreen);
                dialog.setContentView(R.layout.activity_sua_don_vi_tinh);
                dialog.show();
                edSua = (EditText) dialog.findViewById(R.id.edSuaDonViTinh);
                ImageView imgLuu = dialog.findViewById(R.id.imgLuuThayDoiDV);
                imgLuu.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        try{
                            donViTinhDAO.updateDonVi(edSua.getText().toString(),list.get(i).getDonViTinh());
                            Toast.makeText(context,"Lưu thành công",Toast.LENGTH_SHORT).show();
                            list.set(i,new DonViTinh(edSua.getText().toString()));
                            notifyDataSetChanged();
                            dialog.dismiss();
                        }catch (Exception e){
                            Toast.makeText(context,"Lưu thất bại, Đơn Vị đã tồn tại",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
        return view;
    }
    private class ViewHolder{
        TextView tvTen;
        ImageView imgDelete,imgUpdate;
    }
}
