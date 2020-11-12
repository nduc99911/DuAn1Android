package com.example.duan1android.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duan1android.Model.HoaDonChiTiet;

import java.util.ArrayList;
import java.util.List;

public
class DonViTinhDAO {
    public static final String TABLE_NAME = "DonViTinh";
    public static final String SQL_DONVI = "Create table if not exists DonViTinh (" +
            "   tenDonVi text primary key )";
    private Mydatabase mydatabase;
    private SQLiteDatabase sqLiteDatabase;

    public DonViTinhDAO(Context context) {
        mydatabase = new Mydatabase(context);
        sqLiteDatabase = mydatabase.getWritableDatabase();
    }
    public long addDonVi(String ten){
        ContentValues contentValues = new ContentValues();
        contentValues.put("tenDonVi",ten);
        return sqLiteDatabase.insert(TABLE_NAME,null,contentValues);
    }
    public long updateDonVi(String tenMoi,String tenCu){
        ContentValues contentValues = new ContentValues();
        contentValues.put("tenDonVi",tenMoi);
        return sqLiteDatabase.update(TABLE_NAME,contentValues,"tenDonVi = ?",new String[]{tenCu});
    }
    public long deleteHDCT(String ten){
        return sqLiteDatabase.delete(TABLE_NAME,"tenDonVi = ?",new String[]{ten});
    }
    public List<String> getAllDonViTinh(){
        List<String> list = new ArrayList<>();
        String query = "Select * from "+TABLE_NAME;
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        if(cursor.getCount()>0){
            cursor.moveToFirst();
            while (cursor.isAfterLast()==false){
                String ten = cursor.getString(0);
                list.add(ten);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return list;
    }
}
