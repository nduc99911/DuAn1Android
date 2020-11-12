package com.example.duan1android.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import com.example.duan1android.Model.SanPham;
import com.example.duan1android.R;

import java.util.List;

public
class SanPhamAdapter extends BaseAdapter {
    Context context;
    List<SanPham> list;

    public SanPhamAdapter(Context context, List<SanPham> list) {
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
            view = LayoutInflater.from(context).inflate(R.layout.item_san_pham, viewGroup, false);
            viewHolder = new ViewHolder();
            viewHolder.tvMa = view.findViewById(R.id.tvMaSanPham_ItemSp);
            viewHolder.tvTen = view.findViewById(R.id.tvTenSanPham_ItemSp);
            viewHolder.tvGia = view.findViewById(R.id.tvGiaBan_ItemSp);
            viewHolder.tvConDu = view.findViewById(R.id.tvCon_ItemSp);
            viewHolder.imgSanPham = view.findViewById(R.id.imageView_ItemSp);
            view.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) view.getTag();
        }
        SanPham sanPham = list.get(i);
        viewHolder.tvMa.setText("Mã sản phẩm : "+sanPham.getMaSanPham());
        viewHolder.tvTen.setText("Tên sản phẩm : "+sanPham.getTen());
        viewHolder.tvGia.setText("Giá bán : "+sanPham.getGiaBan());
        viewHolder.tvConDu.setText("Còn : "+sanPham.getSoLuong());
        byte[] image = sanPham.getImage();
        Bitmap bitmap = BitmapFactory.decodeByteArray(image,0,image.length);
        viewHolder.imgSanPham.setImageBitmap(bitmap);

        return view;
    }


    private class ViewHolder{
        TextView tvMa,tvTen,tvGia,tvConDu;
        ImageView imgSanPham;
    }
}
