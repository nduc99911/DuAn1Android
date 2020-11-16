package com.example.duan1android.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import com.example.duan1android.Model.NguoiDung;
import com.example.duan1android.R;

import java.util.List;

public
class NguoiDungAdapter extends BaseAdapter {
    Context context;
    List<NguoiDung> list;

    public NguoiDungAdapter(Context context, List<NguoiDung> list) {
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
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if(view == null){
            viewHolder = new ViewHolder();
            view = LayoutInflater.from(context).inflate(R.layout.item_thiet_lap_nguoi_dung, viewGroup, false);
            viewHolder.imgHinhAnh = view.findViewById(R.id.imgNguoiDung);
            viewHolder.tvTen = view.findViewById(R.id.tvTenKhachHang);
            viewHolder.btnShowMore = view.findViewById(R.id.btnNext);
            view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) view.getTag();
        }
        NguoiDung nguoiDung = list.get(i);
        viewHolder.tvTen.setText(""+nguoiDung.getTaiKhoan());
        viewHolder.btnShowMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(context,android.R.style.Theme_Black_NoTitleBar_Fullscreen);
                dialog.setContentView(R.layout.activity_sua_nguoi_dung);
                dialog.show();
            }
        });
        byte[] img = nguoiDung.getHinhAnh();
        try {
            Bitmap bitmap = BitmapFactory.decodeByteArray(img, 0, img.length);
            viewHolder.imgHinhAnh.setImageBitmap(bitmap);
        }catch (Exception e){
            viewHolder.imgHinhAnh.setImageResource(R.drawable.iocnnguoidung);
        }
        return view;
    }
    private class ViewHolder{
        ImageView imgHinhAnh;
        TextView tvTen;
        Button btnShowMore;
    }
}
