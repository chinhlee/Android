package com.example.lethichinh_qlvattu;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class Chinh_VTDatabase extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "QL_VatTu.db";
    //Khai báo tên bảng
    public static final String TABLE_NAME = "t_vattu";
    //Khai báo các trường trong bảng
    public static final String ID = "MaVT";
    public static final String NAME = "Ten";
    public static final String NSX = "Nsx";
    public static final String NAMSX = "Namsx";
    public static final String SOLUONG = "Soluong";
    public static final String DONGIA = "Dongia";

    private int version = 1;
    public Context context;

    public Chinh_VTDatabase(Context context) {
        super(context, TABLE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String sql = "CREATE TABLE " + TABLE_NAME + " ( " +
                ID + " Varchar(50) primary key, " +
                NAME + " Varchar(100), " +
                NSX + " Varchar(100), " +
                NAMSX + " Varchar(30), " +
                SOLUONG + " Varchar(200), " +
                DONGIA + " Varchar(200))";

        //Tạo bảng
        sqLiteDatabase.execSQL(sql);
        // Chèn 1 bản ghi vào bảng
//        String sv1 = "INSERT INTO " + TABLE_NAME + "(MaSV,HoTen,Lop,DiaChi,Sdt) " +
//                "VALUES(1,'Nguyen Van A','DH10C1','Ha Noi','0123456789')";
//        sqLiteDatabase.execSQL(sv1);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);

    }

    //Thêm mới 1 sv vào bảng CSDL
    public void ThemVT(Chinh_VatTu vt) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues(); //Khai báo và khởi tạo đối tương để lưu các thuộc tính của đối tượng sv vào
        values.put(ID, vt.getMavt());
        values.put(NAME, vt.getTen());
        values.put(NSX, vt.getNsx());
        values.put(NAMSX, vt.getNamsx());
        values.put(SOLUONG, vt.getSoluong());
        values.put(DONGIA, vt.getDongia());
        //đẩy values vào bảng CSDL
        db.insert(TABLE_NAME, null, values);
        db.close();

    }

    //Cập nhật sv theo mã
    public int CapNhatVT(Chinh_VatTu vt) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NAME, vt.getTen());
        values.put(NSX, vt.getNsx());
        values.put(NAMSX, vt.getNamsx());
        values.put(SOLUONG, vt.getSoluong());
        values.put(DONGIA, vt.getDongia());
        int a = db.update(TABLE_NAME, values, ID + "=?",
                new String[]{String.valueOf(vt.getMavt())});
        db.close();
        return a;
    }

    //Xóa 1 sv trong CSDL theo id
    public int XoaVT(Chinh_VatTu vt) {
        SQLiteDatabase db = this.getWritableDatabase();
        int b = db.delete(TABLE_NAME, ID + "=?", new String[]{String.valueOf(vt.getMavt())});
        db.close();
        return b;
    }

    //Lấy thông tin sv trong CSDL
    public List<Chinh_VatTu> ThongTinVT() {
        List<Chinh_VatTu> listVT = new ArrayList<Chinh_VatTu>();
        SQLiteDatabase db = this.getWritableDatabase();
        String sql2 = "SELECT * FROM " + TABLE_NAME;
        //con trỏ truy vấn cursor
        Cursor cursor = db.rawQuery(sql2, null);
        // đưa con trỏ cursor về hàng đầu tiên
        cursor.moveToFirst();
        //Kiểm tra điều kiện
        while (!cursor.isAfterLast())//cursor chưa phải là hàng cuối cùng
        {
            String mvt = cursor.getString(0);
            String ten = cursor.getString(1);
            String nsx = cursor.getString(2);
            String namsx = cursor.getString(3);
            String soluong = cursor.getString(4);
            String dongia = cursor.getString(5);
            Chinh_VatTu vt = new Chinh_VatTu(mvt,ten,nsx,namsx,soluong,dongia);
            listVT.add(vt);
            cursor.moveToNext();
        }
        return listVT;
    }

}
