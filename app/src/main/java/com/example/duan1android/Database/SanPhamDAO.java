package com.example.duan1android.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.BitmapDrawable;

import com.example.duan1android.Model.SanPham;

import java.util.ArrayList;
import java.util.List;

public
class SanPhamDAO {
    public static final String TABLE_NAME = "SanPham";
    public static final String SQL_SANPHAM = "Create table if not exists SanPham(" +
            "   maSanPham text primary key," +
            "   maLoai text," +
            "   tenSanPham text," +
            "   donViTinh text," +
            "   soLuong number," +
            "   giaNhap number," +
            "   giaBan number," +
            "   hinhAnh BLOB)";
    private Mydatabase mydatabase;
    private SQLiteDatabase sqLiteDatabase;

    public SanPhamDAO(Context context) {
        mydatabase = new Mydatabase(context);
        sqLiteDatabase = mydatabase.getWritableDatabase();
    }
    public long addSanPham(SanPham sanPham){
        ContentValues contentValues = new ContentValues();
        contentValues.put("maSanPham",sanPham.getMaSanPham());
        contentValues.put("maLoai",sanPham.getMaLoai());
        contentValues.put("tenSanPham",sanPham.getTen());
        contentValues.put("donViTinh",sanPham.getDonViTinh());
        contentValues.put("soLuong",sanPham.getSoLuong());
        contentValues.put("giaNhap",sanPham.getGiaNhap());
        contentValues.put("giaBan",sanPham.getGiaBan());
        contentValues.put("hinhAnh",sanPham.getImage());
        return sqLiteDatabase.insert(TABLE_NAME,null,contentValues);
    }
    public long updateSanPham(SanPham sanPham,String ma){
        ContentValues contentValues = new ContentValues();
        contentValues.put("maSanPham",sanPham.getMaSanPham());
        contentValues.put("maLoai",sanPham.getMaLoai());
        contentValues.put("tenSanPham",sanPham.getTen());
        contentValues.put("donViTinh",sanPham.getDonViTinh());
        contentValues.put("soLuong",sanPham.getSoLuong());
        contentValues.put("giaNhap",sanPham.getGiaNhap());
        contentValues.put("giaBan",sanPham.getGiaBan());
        contentValues.put("hinhAnh",sanPham.getImage());
        return sqLiteDatabase.update(TABLE_NAME,contentValues,"maSanPham = ?",new String[]{ma});
    }
    public long deleteSanPham(String ma){
        return sqLiteDatabase.delete(TABLE_NAME,"maSanPham = ?",new String[]{ma});
    }
    public List<SanPham> getAllSanPham(){
        List<SanPham> list = new ArrayList<>();
        String query = "Select * from "+TABLE_NAME;
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        if(cursor.getCount()>0){
            cursor.moveToFirst();
            while (cursor.isAfterLast()==false){
                String maSP = cursor.getString(0);
                String maLoai = cursor.getString(1);
                String ten = cursor.getString(2);
                String donVi = cursor.getString(3);
                int soLuong = cursor.getInt(4);
                double giaNhap = cursor.getDouble(5);
                double giaBan = cursor.getDouble(6);
                byte[] img = cursor.getBlob(7);
                SanPham sanPham = new SanPham(maSP,maLoai,ten,donVi,soLuong,giaNhap,giaBan,img);
                list.add(sanPham);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return list;
    }
}
