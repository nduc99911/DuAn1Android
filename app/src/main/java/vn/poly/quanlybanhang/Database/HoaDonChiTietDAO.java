package vn.poly.quanlybanhang.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import vn.poly.quanlybanhang.Model.HoaDon;
import vn.poly.quanlybanhang.Model.HoaDonChiTiet;
import vn.poly.quanlybanhang.Model.SanPham;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public
class HoaDonChiTietDAO {
    public static final String TABLE_NAME = "HoaDonChiTiet";
    public static final String SQL_HDCT = "Create table if not exists HoaDonChiTiet (" +
            "   maHDCT integer primary key autoincrement," +
            "   maHoaDon text," +
            "   maSanPham text," +
            "   soLuong number," +
            "   tongTien number)";
    SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd-MM-yyyy");
    private SQLiteDatabase sqLiteDatabase;

    public HoaDonChiTietDAO(Context context) {
        Mydatabase mydatabase = new Mydatabase(context);
        sqLiteDatabase = mydatabase.getWritableDatabase();
    }
    public long addHDCT(HoaDonChiTiet hoaDon){
        ContentValues contentValues = new ContentValues();
        contentValues.put("maHoaDon",hoaDon.getHoaDon().getMaHD());
        contentValues.put("maSanPham",hoaDon.getSanPham().getMaSanPham());
        contentValues.put("soLuong",hoaDon.getSoLuong());
        contentValues.put("tongTien",hoaDon.getTongTien());
        return sqLiteDatabase.insert(TABLE_NAME,null,contentValues);
    }
    public long updateHDCT(HoaDonChiTiet hoaDon,String ma){
        ContentValues contentValues = new ContentValues();
        contentValues.put("maHDCT",hoaDon.getMaHDCT());
        contentValues.put("maHoaDon",hoaDon.getHoaDon().getMaHD());
        contentValues.put("maSanPham",hoaDon.getSanPham().getMaSanPham());
        contentValues.put("soLuong",hoaDon.getSoLuong());
        contentValues.put("tongTien",hoaDon.getTongTien());
        return sqLiteDatabase.update(TABLE_NAME,contentValues,"maHDCT = ?",new String[]{ma});
    }
    public long deleteHDCT(String ma){
        return sqLiteDatabase.delete(TABLE_NAME,"maHDCT = ?",new String[]{ma});
    }
    public List<HoaDonChiTiet> getAllHDCT(String maHD) throws ParseException {
        List<HoaDonChiTiet> list = new ArrayList<>();
        String query = "Select maHDCT, HoaDon.maHoaDon,SanPham.maSanPham FROM HoaDonChiTiet INNER JOIN HoaDon on HoaDonChiTiet.maHoaDon=HoaDon.maHoaDon INNER JOIN SanPham on SanPham.maSanPham = HoaDonChiTiet.maSanPham where HoaDonChiTiet.maHoaDon='" + maHD + "'";
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        if(cursor.getCount()>0){
            cursor.moveToFirst();
            while (cursor.isAfterLast()==false){
                HoaDonChiTiet ee = new HoaDonChiTiet();
                ee.setMaHDCT(cursor.getInt(0));
                ee.setHoaDon(new HoaDon(cursor.getString(1), cursor.getString(2),simpleDateFormat.parse(cursor.getString(3))));
                ee.setSanPham(new SanPham(cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getString(7), cursor.getInt(8), cursor.getDouble(9), cursor.getDouble(10),cursor.getBlob(11)));
                list.add(ee);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return list;
    }
}
