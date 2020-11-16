package com.example.duan1android.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duan1android.Model.HoaDon;
import com.example.duan1android.Model.NguoiDung;

import java.util.ArrayList;
import java.util.List;

public
class NguoiDungDAO {
    public static final String TABLE_NAME = "NguoiDung";
    public static final String SQL_NGUOIDUNG = "Create table if not exists NguoiDung (" +
            "   taiKhoan text primary key," +
            "   matKhau text," +
            "   soDienThoai text," +
            "   email text," +
            "   hoTen text," +
            "   hinhAnh BLOB)";

    private Mydatabase mydatabase;
    private SQLiteDatabase sqLiteDatabase;

    public NguoiDungDAO(Context context) {
        mydatabase = new Mydatabase(context);
        sqLiteDatabase = mydatabase.getWritableDatabase();
    }
    public long addNguoiDung(NguoiDung nguoiDung){
        ContentValues contentValues = new ContentValues();
        contentValues.put("taiKhoan",nguoiDung.getTaiKhoan());
        contentValues.put("matKhau",nguoiDung.getMatKhau());
        contentValues.put("soDienThoai",nguoiDung.getSoDienThoai());
        contentValues.put("email",nguoiDung.getEmail());
        contentValues.put("hoTen",nguoiDung.getHoTen());
        contentValues.put("hinhAnh",nguoiDung.getHinhAnh());
        return sqLiteDatabase.insert(TABLE_NAME,null,contentValues);
    }
    public long updateNguoiDung(NguoiDung  nguoiDung,String userName){
        ContentValues contentValues = new ContentValues();
        contentValues.put("taiKhoan",nguoiDung.getTaiKhoan());
        contentValues.put("matKhau",nguoiDung.getMatKhau());
        contentValues.put("soDienThoai",nguoiDung.getSoDienThoai());
        contentValues.put("email",nguoiDung.getEmail());
        contentValues.put("hoTen",nguoiDung.getHoTen());
        contentValues.put("hinhAnh",nguoiDung.getHinhAnh());
        return sqLiteDatabase.update(TABLE_NAME,contentValues,"taiKhoan = ?",new String[]{userName});
    }
    public long deleteNguoiDung(String userName){
        return sqLiteDatabase.delete(TABLE_NAME,"taiKhoan = ?",new String[]{userName});
    }
    public List<NguoiDung> getAllNguoiDung(){
        List<NguoiDung> list = new ArrayList<>();
        String query = "Select * from "+TABLE_NAME;
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        if(cursor.getCount()>0){
            cursor.moveToFirst();
            while (cursor.isAfterLast()==false){
                String taiKhoan = cursor.getString(0);
                String matKhau = cursor.getString(1);
                String sdt = cursor.getString(2);
                String email = cursor.getString(3);
                String hoTen = cursor.getString(4);
                byte[] img = cursor.getBlob(5);

                NguoiDung nguoiDung = new NguoiDung(taiKhoan,matKhau,sdt,email,hoTen,img);
                list.add(nguoiDung);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return list;
    }
}
