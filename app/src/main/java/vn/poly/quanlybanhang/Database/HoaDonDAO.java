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
            "   tenKhachHang text," +
            "   chietKhau text," +
            "   khachTra number," +
            "   traLai number ," +
            "   tongTien number)";
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
    private SQLiteDatabase sqLiteDatabase;

    public HoaDonDAO(Context context) {
        Mydatabase mydatabase = new Mydatabase(context);
        sqLiteDatabase = mydatabase.getWritableDatabase();
    }

    public long addHoaDon(HoaDon hoaDon) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("maHoaDon", hoaDon.getMaHD());
        contentValues.put("ngayBan", simpleDateFormat.format(hoaDon.getNgayBan()));
        contentValues.put("tenKhachHang", hoaDon.getTenKhachHang());
        contentValues.put("chietKhau", hoaDon.getChietKhau());
        contentValues.put("khachTra", hoaDon.getKhachTra());
        contentValues.put("traLai", hoaDon.getTraLai());
        contentValues.put("tongTien", hoaDon.getTongTien());

        return sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
    }

    public long updateHoaDon(HoaDon hoaDon, String ma) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("maHoaDon", hoaDon.getMaHD());
        contentValues.put("ngayBan", simpleDateFormat.format(hoaDon.getNgayBan()));
        contentValues.put("tenKhachHang", hoaDon.getTenKhachHang());
        contentValues.put("chietKhau", hoaDon.getChietKhau());
        contentValues.put("khachTra", hoaDon.getKhachTra());
        contentValues.put("traLai", hoaDon.getTraLai());
        contentValues.put("tongTien", hoaDon.getTongTien());
        return sqLiteDatabase.update(TABLE_NAME, contentValues, "maHoaDon = ?", new String[]{ma});
    }
    public long deleteHoaDon(String ma) {
        return sqLiteDatabase.delete(TABLE_NAME, "maHoaDon = ?", new String[]{ma});
    }

    public List<HoaDon> getAllHoaDon() throws ParseException {
        List<HoaDon> list = new ArrayList<>();
        String query = "Select * from " + TABLE_NAME;
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            while (cursor.isAfterLast() == false) {
                String maHD = cursor.getString(0);
                String ngayBan = cursor.getString(1);
                String tenKhach = cursor.getString(2);
                int chietKhau = cursor.getInt(3);
                int khachTra = cursor.getInt(4);
                int traLai = cursor.getInt(5);
                int tongTien = cursor.getInt(6);
                HoaDon hoaDon = new HoaDon(maHD, tenKhach, simpleDateFormat.parse(ngayBan), khachTra, traLai, tongTien, chietKhau);
                list.add(hoaDon);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return list;
    }

    public List<HoaDon> getAllHoaDonTheoMa(String ma) throws ParseException {
        List<HoaDon> list = new ArrayList<>();
        String query = "Select * from " + TABLE_NAME + " where maHoaDon like '%" + ma + "%'";
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            while (cursor.isAfterLast() == false) {
                String maHD = cursor.getString(0);
                String ngayBan = cursor.getString(1);
                String tenKhach = cursor.getString(2);
                int chietKhau = cursor.getInt(3);
                int khachTra = cursor.getInt(4);
                int traLai = cursor.getInt(5);
                int tongTien = cursor.getInt(6);
                HoaDon hoaDon = new HoaDon(maHD, tenKhach, simpleDateFormat.parse(ngayBan), khachTra, traLai, tongTien, chietKhau);
                list.add(hoaDon);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return list;
    }

    public HoaDon getHoaDonTheoMa(String ma) throws ParseException {
        String query = "Select * from " + TABLE_NAME + " where maHoaDon = '" + ma + "'";
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        HoaDon hoaDon = null;
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            String maHD = cursor.getString(0);
            String ngayBan = cursor.getString(1);
            String tenKhach = cursor.getString(2);
            int chietKhau = cursor.getInt(3);
            int khachTra = cursor.getInt(4);
            int traLai = cursor.getInt(5);
            int tongTien = cursor.getInt(6);
            hoaDon = new HoaDon(maHD, tenKhach, simpleDateFormat.parse(ngayBan), khachTra, traLai, tongTien, chietKhau);
            cursor.close();
        }
        return hoaDon;
    }
}
