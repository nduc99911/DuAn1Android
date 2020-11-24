package com.example.duan1android.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;

import com.example.duan1android.Model.SanPham;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
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
            "   hinhAnh BLOB, "+
            "   con number)";

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
        contentValues.put("con",sanPham.getCon());
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
        contentValues.put("con",sanPham.getCon());
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
                int con=cursor.getInt(8);
                SanPham sanPham = new SanPham(maSP,maLoai,ten,donVi,soLuong,giaNhap,giaBan,img,con);
                list.add(sanPham);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return list;
    }
    public SanPham getSanPhamTheoMa(String ma){
        String query = "select * from "+TABLE_NAME+ " where maSanPham = '"+ma+"'";
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        SanPham sanPham = null;
        if(cursor.getCount()>0){
            cursor.moveToFirst();
            String maSP = cursor.getString(0);
            String maLoai = cursor.getString(1);
            String ten = cursor.getString(2);
            String donVi = cursor.getString(3);
            int soLuong = cursor.getInt(4);
            double giaNhap = cursor.getDouble(5);
            double giaBan = cursor.getDouble(6);
            byte[] img = cursor.getBlob(7);
            int con=cursor.getInt(8);
            sanPham = new SanPham(maSP,maLoai,ten,donVi,soLuong,giaNhap,giaBan,img,con);
        }
        cursor.close();
        return sanPham;
    }
    public List<SanPham> getAllSanPhamTheoMa(String ma){
        List<SanPham> list = new ArrayList<>();
        String query = "select DISTINCT * from "+TABLE_NAME+ " where maSanPham like '%"+ma+"%' or tenSanPham like '%"+ma+"%'";
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        SanPham sanPham = null;
        if(cursor.getCount()>0){
            cursor.moveToFirst();
            String maSP = cursor.getString(0);
            String maLoai = cursor.getString(1);
            String tensp = cursor.getString(2);
            String donVi = cursor.getString(3);
            int soLuong = cursor.getInt(4);
            double giaNhap = cursor.getDouble(5);
            double giaBan = cursor.getDouble(6);
            byte[] img = cursor.getBlob(7);
            int con=cursor.getInt(8);
            sanPham = new SanPham(maSP,maLoai,tensp,donVi,soLuong,giaNhap,giaBan,img,con);
            list.add(sanPham);
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }

}
