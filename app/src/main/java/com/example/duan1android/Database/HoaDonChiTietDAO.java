package com.example.duan1android.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duan1android.Model.HoaDon;
import com.example.duan1android.Model.HoaDonChiTiet;

import java.util.ArrayList;
import java.util.List;

public
class HoaDonChiTietDAO {
    public static final String TABLE_NAME = "HoaDonChiTiet";
    public static final String SQL_HDCT = "Create table if not exists HoaDonChiTiet (" +
            "   maHDCT integer primary key autoincrement," +
            "   maHD text," +
            "   maSP text," +
            "   soLuong number," +
            "   tongTien number)";
    private Mydatabase mydatabase;
    private SQLiteDatabase sqLiteDatabase;

    public HoaDonChiTietDAO(Context context) {
        mydatabase = new Mydatabase(context);
        sqLiteDatabase = mydatabase.getWritableDatabase();
    }
    public long addHDCT(HoaDonChiTiet hoaDon){
        ContentValues contentValues = new ContentValues();
        contentValues.put("maHDCT",hoaDon.getMaHDCT());
        contentValues.put("maHD",hoaDon.getMaHD());
        contentValues.put("maSP",hoaDon.getMaSP());
        contentValues.put("soLuong",hoaDon.getSoLuong());
        contentValues.put("tongTien",hoaDon.getTongTien());
        return sqLiteDatabase.insert(TABLE_NAME,null,contentValues);
    }
    public long updateHDCT(HoaDonChiTiet hoaDon,String ma){
        ContentValues contentValues = new ContentValues();
        contentValues.put("maHDCT",hoaDon.getMaHDCT());
        contentValues.put("maHD",hoaDon.getMaHD());
        contentValues.put("maSP",hoaDon.getMaSP());
        contentValues.put("soLuong",hoaDon.getSoLuong());
        contentValues.put("tongTien",hoaDon.getTongTien());
        return sqLiteDatabase.update(TABLE_NAME,contentValues,"maHDCT = ?",new String[]{ma});
    }
    public long deleteHDCT(String ma){
        return sqLiteDatabase.delete(TABLE_NAME,"maHDCT = ?",new String[]{ma});
    }
    public List<HoaDonChiTiet> getAllHDCT(){
        List<HoaDonChiTiet> list = new ArrayList<>();
        String query = "Select * from "+TABLE_NAME;
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        if(cursor.getCount()>0){
            cursor.moveToFirst();
            while (cursor.isAfterLast()==false){
                String maHDCT = cursor.getString(0);
                String maHD = cursor.getString(1);
                String maSP = cursor.getString(2);
                int soLuong = cursor.getInt(3);
                double tongTien = cursor.getDouble(4);
                HoaDonChiTiet  hoaDon = new HoaDonChiTiet(maHDCT,maHD,maSP,soLuong,tongTien);
                list.add(hoaDon);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return list;
    }
}
