package vn.poly.quanlybanhang.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.duan1android.R;

import java.util.List;

import vn.poly.quanlybanhang.Model.GioHang;
import vn.poly.quanlybanhang.Model.HoaDonChiTiet;
import vn.poly.quanlybanhang.Model.SanPham;

public
class HDCTAdapter extends BaseAdapter {
    Context context;
    List<HoaDonChiTiet> list;

    public  HDCTAdapter (Context context, List<HoaDonChiTiet> list) {
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
            view = LayoutInflater.from(context).inflate(R.layout.hoadonchitiet, viewGroup, false);
            viewHolder = new ViewHolder();
            viewHolder.tvSanPham = view.findViewById(R.id.tvSanPhamGH);
            viewHolder.tvGia = view.findViewById(R.id.tvGiaGH);
            viewHolder.tvSoluong = view.findViewById(R.id.tvSoLuongGH);
            view.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) view.getTag();
        }
        HoaDonChiTiet hoaDonChiTiet = list.get(i);
//        viewHolder.tvSanPham.setText(""+hoaDonChiTiet.getSanPham().getMaSanPham());
//        viewHolder.tvGia.setText(""+Math.round(hoaDonChiTiet.getTongTien()));
//        viewHolder.tvSoluong.setText(""+hoaDonChiTiet.getSoLuong());
        return view;
    }


    private class ViewHolder{
        TextView tvSanPham,tvSoluong,tvGia;
    }

}
