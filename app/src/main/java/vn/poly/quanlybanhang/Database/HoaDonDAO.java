package vn.poly.quanlybanhang.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import vn.poly.quanlybanhang.Model.HoaDon;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class HoaDonDAO {
    public static final String TABLE_NAME = "HoaDon";
    public static final String SQL_HOADON = "Create table if not exists HoaDon (" +
            "   maHoaDon text primary key," +
            "   ngayBan date ," +
            "   tenKhachHang text)";
    SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd-MM-yyyy");
    private SQLiteDatabase sqLiteDatabase;

    public HoaDonDAO(Context context) {
        Mydatabase mydatabase = new Mydatabase(context);
        sqLiteDatabase = mydatabase.getWritableDatabase();
    }
    public long addHoaDon(HoaDon hoaDon){
        ContentValues contentValues = new ContentValues();
        contentValues.put("maHoaDon",hoaDon.getMaHD());
        contentValues.put("ngayBan",simpleDateFormat.format(hoaDon.getNgayBan()));
        contentValues.put("tenKhachHang",hoaDon.getTenKhachHang());
        return sqLiteDatabase.insert(TABLE_NAME,null,contentValues);
    }
    public long updateHoaDon(HoaDon hoaDon,String ma){
        ContentValues contentValues = new ContentValues();
        contentValues.put("maHoaDon",hoaDon.getMaHD());
        contentValues.put("ngayBan",simpleDateFormat.format(hoaDon.getNgayBan()));
        contentValues.put("tenKhachHang",hoaDon.getTenKhachHang());
        return sqLiteDatabase.update(TABLE_NAME,contentValues,"maHoaDon = ?",new String[]{ma});
    }
    public long deleteHoaDon(String ma){
        return sqLiteDatabase.delete(TABLE_NAME,"maHoaDon = ?",new String[]{ma});
    }
    public List<HoaDon> getAllHoaDon() throws ParseException {
        List<HoaDon> list = new ArrayList<>();
        String query = "Select * from "+TABLE_NAME;
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        if(cursor.getCount()>0){
            cursor.moveToFirst();
            while (cursor.isAfterLast()==false){
                String maHD = cursor.getString(0);

                String ngayBan = cursor.getString(1);
                String tenKhach = cursor.getString(2);
                HoaDon hoaDon = new HoaDon(maHD,tenKhach,simpleDateFormat.parse(ngayBan));
                list.add(hoaDon);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return list;
    }
    public List<HoaDon> getAllHoaDonTheoMa(String ma) throws ParseException {
        List<HoaDon> list = new ArrayList<>();
        String query = "Select * from "+TABLE_NAME+ " where maHoaDon like '%"+ma+"%'";
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        if(cursor.getCount()>0){
            cursor.moveToFirst();
            while (cursor.isAfterLast()==false){
                String maHD = cursor.getString(0);
                String tenKhach = cursor.getString(1);
                String ngayBan = cursor.getString(2);
                HoaDon hoaDon = new HoaDon(maHD,tenKhach,simpleDateFormat.parse(ngayBan));
                list.add(hoaDon);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return list;
    }
}
