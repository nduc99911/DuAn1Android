package com.example.duan1android.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duan1android.Model.HoaDon;
import com.example.duan1android.Model.SanPham;

import java.util.ArrayList;
import java.util.List;

public
class HoaDonDAO {
    public static final String TABLE_NAME = "HoaDon";
    public static final String SQL_HOADON = "Create table if not exists HoaDon (" +
            "   maHoaDon text primary key," +
            "   ngayBan date ," +
            "   tenKhachHang text)";
    private Mydatabase mydatabase;
    private SQLiteDatabase sqLiteDatabase;

    public HoaDonDAO(Context context) {
        mydatabase = new Mydatabase(context);
        sqLiteDatabase = mydatabase.getWritableDatabase();
    }
    public long addHoaDon(HoaDon hoaDon){
        ContentValues contentValues = new ContentValues();
        contentValues.put("maHoaDon",hoaDon.getMaHD());
        contentValues.put("ngayBan",hoaDon.getNgayBan());
        contentValues.put("tenKhachHang",hoaDon.getTenKhachHang());
        return sqLiteDatabase.insert(TABLE_NAME,null,contentValues);
    }
    public long updateHoaDon(HoaDon hoaDon,String ma){
        ContentValues contentValues = new ContentValues();
        contentValues.put("maHoaDon",hoaDon.getMaHD());
        contentValues.put("ngayBan",hoaDon.getNgayBan());
        contentValues.put("tenKhachHang",hoaDon.getTenKhachHang());
        return sqLiteDatabase.update(TABLE_NAME,contentValues,"maHoaDon = ?",new String[]{ma});
    }
    public long deleteHoaDon(String ma){
        return sqLiteDatabase.delete(TABLE_NAME,"maHoaDon = ?",new String[]{ma});
    }
    public List<HoaDon> getAllHoaDon(){
        List<HoaDon> list = new ArrayList<>();
        String query = "Select * from "+TABLE_NAME;
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        if(cursor.getCount()>0){
            cursor.moveToFirst();
            while (cursor.isAfterLast()==false){
                String maHD = cursor.getString(0);
                String ngayBan = cursor.getString(1);
                String tenKhach = cursor.getString(2);
                HoaDon hoaDon = new HoaDon(maHD,ngayBan,tenKhach);
                list.add(hoaDon);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return list;
    }
    public List<HoaDon> getAllHoaDonTheoMa(String ma){
        List<HoaDon> list = new ArrayList<>();
        String query = "Select * from "+TABLE_NAME+ " where maHoaDon like '%"+ma+"%'";
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        if(cursor.getCount()>0){
            cursor.moveToFirst();
            while (cursor.isAfterLast()==false){
                String maHD = cursor.getString(0);
                String ngayBan = cursor.getString(1);
                String tenKhach = cursor.getString(2);
                HoaDon hoaDon = new HoaDon(maHD,ngayBan,tenKhach);
                list.add(hoaDon);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return list;
    }
}
