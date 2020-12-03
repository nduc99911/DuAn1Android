package vn.poly.quanlybanhang.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import vn.poly.quanlybanhang.Model.HoaDon;
import com.example.duan1android.R;

import java.text.SimpleDateFormat;
import java.util.List;

public
class HoaDonAdapter extends BaseAdapter {
    Context context;
    List<HoaDon> list;
    SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd-MM-yyyy");
    public HoaDonAdapter(Context context, List<HoaDon> list) {
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
            view = LayoutInflater.from(context).inflate(R.layout.item_hoa_don, viewGroup, false);
            viewHolder = new ViewHolder();
            viewHolder.tvNgayMua = view.findViewById(R.id.tvNgayMua);
            viewHolder.tvShowHDCT = view.findViewById(R.id.tvShowHDCT);
            viewHolder.tvTongTien = view.findViewById(R.id.tvTongTien);
            view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) view.getTag();
        }
        HoaDon hoaDon = list.get(i);
        viewHolder.tvNgayMua.setText("Ngày bán : "+simpleDateFormat.format(hoaDon.getNgayBan()));
        viewHolder.tvTongTien.setText("Mã Hóa Đơn : "+hoaDon.getMaHD());
        return view;
    }
    private class ViewHolder{
        TextView tvNgayMua, tvTongTien,tvShowHDCT;

    }
}
