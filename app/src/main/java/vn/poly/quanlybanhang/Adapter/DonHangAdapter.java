package vn.poly.quanlybanhang.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import vn.poly.quanlybanhang.Model.SanPham;
import com.example.duan1android.R;

import java.util.List;

public
class DonHangAdapter extends BaseAdapter {
    Context context;
    List<SanPham> list;

    public DonHangAdapter(Context context, List<SanPham> list) {
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
            viewHolder.tvSanPham = view.findViewById(R.id.tvTenSanPham_ItemSp);
            viewHolder.tvGia = view.findViewById(R.id.tvGiaBan_ItemSp);
            viewHolder.tvSoluong = view.findViewById(R.id.tvCon_ItemSp);
            view.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) view.getTag();
        }
        SanPham sanPham = list.get(i);
        viewHolder.tvSanPham.setText(""+sanPham.getTen());
        viewHolder.tvGia.setText(""+Math.round(sanPham.getGiaBan())+ " VNĐ");
        viewHolder.tvSoluong.setText(""+sanPham.getCon());
        return view;
    }


    private class ViewHolder{
        TextView tvSanPham,tvSoluong,tvGia;
    }

}
