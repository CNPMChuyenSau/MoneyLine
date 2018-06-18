package com.example.vanluom.quan_ly_tai_chinh_ca_nhan.Database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.vanluom.quan_ly_tai_chinh_ca_nhan.Control.DateTime;
import com.example.vanluom.quan_ly_tai_chinh_ca_nhan.Model.Chi;

import com.example.vanluom.quan_ly_tai_chinh_ca_nhan.Model.MucChiCha;
import com.example.vanluom.quan_ly_tai_chinh_ca_nhan.Model.MucChiCon;
import com.example.vanluom.quan_ly_tai_chinh_ca_nhan.Model.Personal;
import com.example.vanluom.quan_ly_tai_chinh_ca_nhan.Model.SoTietKiem;
import com.example.vanluom.quan_ly_tai_chinh_ca_nhan.Model.TK;
import com.example.vanluom.quan_ly_tai_chinh_ca_nhan.Model.TKDN;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;


public class SQLite extends SQLiteOpenHelper {
    private static final int Data_Version = 1;
    private static final String Data_Name = "QuanLyThuChi";
    private static final String TEXT = " TEXT,";
    private static final String LaKhoaChinh = " INTEGER PRIMARY KEY, ";

    //  Tạo bảng tài khoản
    private static final String Table_TK = "TK";
    private static final String TK_ID = "ID";
    private static final String TK_TenTK = "TenTK";
    private static final String TK_LoaiTK = "LoaiTK";
    private static final String TK_SoTien = "SoTien";
    private static final String TK_AvaTar = "Avatar";
    private static final String TK_GhiChu = "GhiChu";

    // Tạo bản tài khoản
    private static final String Table_TKDN = "TKDN";
    private static final String TKDN_ID = "ID";
    private static final String TKDN_Ten = "Ten";
    private static final String TKDN_Email = "Email";
    private static final String TKDN_MK = "MK";
    private static final String TKDN_SDT = "SDT";
    private static final String TKDN_CODE = "code";


    // Tạo bảng sổ tiết kiệm
    private static final String Table_SoTietKiem = "SoTietKiem";
    private static final String SoTietKiem_ID = "ID";
    private static final String SoTietKiem_TenSoTK = "TenSoTK";
    private static final String SoTietKiem_NganHang = "NganHang";
    private static final String SoTietKiem_SoTienBanDau = "SoTien";
    private static final String SoTietKiem_NgayGui = "NgayGui";
    private static final String SoTietKiem_NgayHetHan = "NgayhetHan";
    private static final String SoTietKiem_LaiXuat = "LaiXuat";
    private static final String SoTietKiem_ChuyenTuTaiKhoan = "ChuyenTuTaiKhoan";
    private static final String SoTietKiem_GhiChu = "GhiChu";
    private static final String SoTietKiem_NgayTatToan = "TatToan";

    // Tạo bảng Chi
    private static final String Table_ThuChi = "Chi";
    private static final String ThuChi_ID = "ID";
    private static final String ThuChi_TenMucChi = "TenMucChi";
    private static final String ThuChi_TuTaiKhoan = "TuTaiKhoan";
    private static final String ThuChi_LoaiMuc = "LoaiMuc";
    private static final String ThuChi_NgayChi = "NgayChi";
    private static final String ThuChi_Ai = "Nguoi";
    private static final String ThuChi_SoTien = "SoTien";
    private static final String ThuChi_GhiChu = "GhiChu";

    // Tạo bảng mục chi cha
    private static final String Table_MucChiCha = "MucChiCha";
    private static final String MucChiCha_ID = "ID";
    private static final String MucChiCha_TenMucChi = "TenMucChi";
    private static final String MucChiCha_GhiChu = "GhiChu";

    // Tạo bảng mục chi con
    private static final String Table_MucChiCon = "MucChiCon";
    private static final String MucChiCon_ID = "ID";
    private static final String MucChiCon_TenMucChi = "TenMucChi";
    private static final String MucChiCon_idMucChiCha = "idMucChiCha";
    private static final String MucChiCon_GhiChu = "GhiChu";

    // Tạo bảng chuyển khoản
    private static final String Table_ChuyenKhoan = "ChuyenKhoan";
    private static final String ChuyenKhoan_ID = "ID";
    private static final String ChuyenKhoan_TuTK = "TuTK";
    private static final String ChuyenKhoan_VaoTK = "VaoTK";
    private static final String ChuyenKhoan_NgayChuyen = "NgayChuyen";
    private static final String ChuyenKhoan_SoTien = "SoTien";
    private static final String ChuyenKhoan_Phi = "Phi";
    private static final String ChuyenKhoan_GhiChu = "GhiChu";

    //  Tạo bảng personal
    private static final String Table_Personal = "Personal";
    private static final String Personal_ID = "ID";
    private static final String Personal_Avatar = "Avatar";
    private static final String Personal_Ten = "Ten";
    private static final String Personal_QuanHe = "QuanHe";

    public SQLite(Context context) {
        super(context, Data_Name, null, Data_Version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String Create_Table_TKDN = "CREATE TABLE " + Table_TKDN + "("
                + TKDN_ID + LaKhoaChinh + TKDN_Ten + TEXT + TKDN_Email + TEXT
                + TKDN_MK + TEXT + TKDN_SDT + TEXT + TKDN_CODE + " TEXT)";

        // String câu truy vấn tạo bảng tài khoản
        String Create_Table_TK = "CREATE TABLE " + Table_TK + "("
                + TK_ID + LaKhoaChinh + TK_TenTK + TEXT + TK_LoaiTK + TEXT
                + TK_SoTien + TEXT + TK_GhiChu + TEXT + TK_AvaTar + " INTEGER)";

        // String câu truy vấn tạo bảng sổ tiết kiệm
        String Create_Table_SoTK = "CREATE TABLE " + Table_SoTietKiem + "("
                + SoTietKiem_ID + LaKhoaChinh + SoTietKiem_TenSoTK + TEXT
                + SoTietKiem_NganHang + TEXT + SoTietKiem_SoTienBanDau + TEXT + SoTietKiem_NgayGui + TEXT
                + SoTietKiem_NgayHetHan + TEXT + SoTietKiem_LaiXuat + TEXT
                + SoTietKiem_ChuyenTuTaiKhoan + TEXT + SoTietKiem_GhiChu + TEXT + SoTietKiem_NgayTatToan + " TEXT)";

        // String câu truy vấn tạo bảng  Mục chi
        String Create_Table_Chi = " CREATE TABLE " + Table_ThuChi + "("
                + ThuChi_ID + LaKhoaChinh + ThuChi_TenMucChi +
                TEXT + ThuChi_LoaiMuc + " INTEGER, " + ThuChi_TuTaiKhoan + TEXT + ThuChi_NgayChi + TEXT
                + ThuChi_Ai + TEXT + ThuChi_SoTien + TEXT + ThuChi_GhiChu + " TEXT )";

        // String câu truy vấn tạo bảng  mục chi cha
        String Create_Table_MucChiCha = " CREATE TABLE " + Table_MucChiCha + "("
                + MucChiCha_ID + LaKhoaChinh + MucChiCha_TenMucChi +
                TEXT + MucChiCha_GhiChu + " TEXT )";

        // String câu truy vấn tạo bảng  mục chi con
        String Create_Table_MucChiCon = " CREATE TABLE " + Table_MucChiCon + "("
                + MucChiCon_ID + LaKhoaChinh + MucChiCon_TenMucChi + TEXT +
                MucChiCon_idMucChiCha + " INTEGER ," + MucChiCon_GhiChu + " TEXT )";

        // String câu truy vấn tạo bảng chuyển khoản
        String Create_Table_ChuyenKhoan = "CREATE TABLE " + Table_ChuyenKhoan + "("
                + ChuyenKhoan_ID + LaKhoaChinh + ChuyenKhoan_TuTK + TEXT
                + ChuyenKhoan_VaoTK + TEXT + ChuyenKhoan_NgayChuyen + TEXT + ChuyenKhoan_SoTien + TEXT
                + ChuyenKhoan_Phi + TEXT + ChuyenKhoan_GhiChu + " TEXT)";

        // String câu truy vấn tạo bảng personal
        String Create_Table_Personal = "CREATE TABLE " + Table_Personal + "("
                + Personal_ID + LaKhoaChinh + Personal_Avatar + " INTEGER ,"
                + Personal_Ten + TEXT + Personal_QuanHe + " TEXT)";

        // thực hiện tạo database

        sqLiteDatabase.execSQL(Create_Table_TK);
        sqLiteDatabase.execSQL(Create_Table_SoTK);
        sqLiteDatabase.execSQL(Create_Table_MucChiCon);
        sqLiteDatabase.execSQL(Create_Table_MucChiCha);
        sqLiteDatabase.execSQL(Create_Table_Chi);
        sqLiteDatabase.execSQL(Create_Table_ChuyenKhoan);
        sqLiteDatabase.execSQL(Create_Table_Personal);

        sqLiteDatabase.execSQL(Create_Table_TKDN);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Table_TK);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Table_ThuChi);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Table_MucChiCha);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Table_MucChiCon);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Table_ChuyenKhoan);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Table_SoTietKiem);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Table_Personal);

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Table_TKDN);
    }

    // Function thêm tài khoản
    public void InsertTaiKhoan(TK taikhoan) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TK_TenTK, taikhoan.getTenTK());
        values.put(TK_LoaiTK, taikhoan.getLoaiTK());
        values.put(TK_SoTien, taikhoan.getSoTien());
        values.put(TK_AvaTar, taikhoan.getAvaTar());
        values.put(TK_GhiChu, taikhoan.getGhiChu());
        database.insert(Table_TK, null, values);
        database.close();
    }

    // Function lấy 1 tài khoản  có id
    public TK getTaiKhoan(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT  * FROM " + Table_TK + "  where " + TK_ID + " =" + String.valueOf(id);
        Cursor cursor = db.rawQuery(selectQuery, null);
        TK taiKhoan = null;
        if (cursor != null) {
            cursor.moveToFirst();
            taiKhoan = new
                    TK(cursor.getInt(0), cursor.getString(1), cursor.getString(2),
                    cursor.getString(3), cursor.getInt(4), cursor.getString(5));
        }
        return taiKhoan;
    }

    // function lấy danh sách tài khoản
    public ArrayList<TK> GetAllTK() {
        ArrayList<TK> listTK = new ArrayList<TK>();
        String selectQuery = "SELECT  * FROM " + Table_TK;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                TK taiKhoan = new TK();
                taiKhoan.setID(Integer.parseInt(cursor.getString(0)));
                taiKhoan.setTenTK(cursor.getString(1));
                taiKhoan.setLoaiTK(cursor.getString(2));
                taiKhoan.setSoTien(cursor.getString(3));
                taiKhoan.setAvaTar(cursor.getInt(4));
                taiKhoan.setGhiChu(cursor.getString(5));
                listTK.add(taiKhoan);
            } while (cursor.moveToNext());
        }
        return listTK;
    }

    // Function lấy danh sách tên tài khoản
    public ArrayList<String> GetAllTenTK() {
        ArrayList<String> Ten = new ArrayList<String>();
        String selectQuery = "SELECT  * FROM " + Table_TK;
        SQLiteDatabase db = this.getWritableDatabase();
        @SuppressLint("Recycle") Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Ten.add(cursor.getString(cursor.getColumnIndex(TK_TenTK)));
            } while (cursor.moveToNext());
        }
        return Ten;
    }

    // Function lấy danh sách id tài khoản
    public ArrayList<Integer> GetAllIDTK() {
        ArrayList<Integer> id = new ArrayList<Integer>();
        String selectQuery = "SELECT  * FROM " + Table_TK;
        SQLiteDatabase db = this.getWritableDatabase();
        @SuppressLint("Recycle") Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                id.add(cursor.getInt(cursor.getColumnIndex(TK_ID)));
            } while (cursor.moveToNext());
        }
        return id;
    }

    // Function đếm số tài khoản
    public int GetTKCount() {
        String countQuery = "SELECT  * FROM " + Table_TK;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int dem = cursor.getCount();
        cursor.close();
        return dem;
    }

    // Function cập nhật tài khoản
    public void UpdateTK(TK taikhoan) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TK_TenTK, taikhoan.getTenTK());
        values.put(TK_LoaiTK, taikhoan.getLoaiTK());
        values.put(TK_SoTien, taikhoan.getSoTien());
        values.put(TK_AvaTar, taikhoan.getAvaTar());
        values.put(TK_GhiChu, taikhoan.getGhiChu());
        db.update(Table_TK, values, TK_ID + " = ?",
                new String[]{String.valueOf(taikhoan.getID())});
    }

    // function xóa tài khoản
    public void DeleteTK(TK taikhoan) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Table_TK, TK_ID + " = ?",
                new String[]{String.valueOf(taikhoan.getID())});
        db.close();
    }


    //  Thêm sổ tiết kiệm
    public void InsertSoTietKiem(SoTietKiem soTietKiem) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(SoTietKiem_TenSoTK, soTietKiem.getTenSoTK());
        values.put(SoTietKiem_NganHang, soTietKiem.getNganHang());
        values.put(SoTietKiem_SoTienBanDau, soTietKiem.getSoTienBanDau());
        values.put(SoTietKiem_NgayGui, soTietKiem.getStringNgayGui());
        values.put(SoTietKiem_NgayHetHan, soTietKiem.getStringNgayHetHan());
        values.put(SoTietKiem_LaiXuat, String.valueOf(soTietKiem.getLaiXuat()));
        values.put(SoTietKiem_ChuyenTuTaiKhoan, soTietKiem.getChuyenTuTaiKhoan());
        values.put(SoTietKiem_GhiChu, soTietKiem.getGhiChu());
        values.put(SoTietKiem_NgayTatToan, soTietKiem.getStringNgayTatToan());
        database.insert(Table_SoTietKiem, null, values);
        database.close();
    }

    // function lấy 1 sổ tiết kiệm  có id
    public SoTietKiem GetSoTietKiem(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT  * FROM " + Table_SoTietKiem + "  where " + SoTietKiem_ID + " =" + String.valueOf(id);
        @SuppressLint("Recycle") Cursor cursor = db.rawQuery(selectQuery, null);
        SoTietKiem soTietKiem = null;
        if (cursor != null) {
            cursor.moveToFirst();
            soTietKiem = new
                    SoTietKiem(cursor.getInt(0), cursor.getString(1), cursor.getString(2),
                    cursor.getString(3), cursor.getString(4), cursor.getString(5),
                    Double.valueOf(cursor.getString(6)), cursor.getString(7),
                    cursor.getString(8), cursor.getString(9));
        }
        return soTietKiem;
    }

    // function lấy danh sách sổ tiết kiệm
    public ArrayList<SoTietKiem> GetAllSoTietKiem() {
        ArrayList<SoTietKiem> listSoTietKiem = new ArrayList<SoTietKiem>();
        String selectQuery = "SELECT  * FROM " + Table_SoTietKiem;
        SQLiteDatabase db = this.getWritableDatabase();
        @SuppressLint("Recycle") Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                SoTietKiem soTietKiem = new SoTietKiem();
                soTietKiem.setID(Integer.parseInt(cursor.getString(0)));
                soTietKiem.setTenSoTK(cursor.getString(1));
                soTietKiem.setNganHang(cursor.getString(2));
                soTietKiem.setSoTienBanDau(cursor.getString(3));
                soTietKiem.setStringNgayGui(cursor.getString(4));
                soTietKiem.setStringNgayHetHan(cursor.getString(5));
                soTietKiem.setLaiXuat(Double.valueOf(cursor.getString(6)));
                soTietKiem.setChuyenTuTaiKhoan(cursor.getString(7));
                soTietKiem.setGhiChu(cursor.getString(8));
                soTietKiem.setStringNgayTatToan(cursor.getString(9));
                listSoTietKiem.add(soTietKiem);
            } while (cursor.moveToNext());
        }
        return listSoTietKiem;
    }

    // function lấy danh sách tên sổ tiết kiệm
    public ArrayList<String> GetAllTenSoTietKiem() {
        ArrayList<String> Ten = new ArrayList<String>();
        String selectQuery = "SELECT  * FROM " + Table_SoTietKiem;
        SQLiteDatabase db = this.getWritableDatabase();
        @SuppressLint("Recycle") Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Ten.add(cursor.getString(cursor.getColumnIndex(SoTietKiem_TenSoTK)));
            } while (cursor.moveToNext());
        }
        return Ten;
    }

    // function lấy danh sách id sổ tiết kiệm
    public ArrayList<Integer> GetAllIDSoTietKiem() {
        ArrayList<Integer> id = new ArrayList<Integer>();
        String selectQuery = "SELECT  * FROM " + Table_SoTietKiem;
        SQLiteDatabase db = this.getWritableDatabase();
        @SuppressLint("Recycle") Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                id.add(cursor.getInt(cursor.getColumnIndex(SoTietKiem_ID)));
            } while (cursor.moveToNext());
        }
        return id;
    }

    // function  đếm số sổ tiết kiệm
    public int GetSoTietKiemCount() {
        String countQuery = "SELECT  * FROM " + Table_SoTietKiem;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int dem = cursor.getCount();
        cursor.close();
        return dem;
    }

    // function  cập nhật sổ tiết kiệm
    public void UpdateSoTietKiem(SoTietKiem soTietKiem) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(SoTietKiem_TenSoTK, soTietKiem.getTenSoTK());
        values.put(SoTietKiem_NganHang, soTietKiem.getNganHang());
        values.put(SoTietKiem_SoTienBanDau, soTietKiem.getSoTienBanDau());
        values.put(SoTietKiem_NgayGui, soTietKiem.getStringNgayGui());
        values.put(SoTietKiem_NgayHetHan, soTietKiem.getStringNgayHetHan());
        values.put(SoTietKiem_LaiXuat, String.valueOf(soTietKiem.getLaiXuat()));
        values.put(SoTietKiem_ChuyenTuTaiKhoan, soTietKiem.getChuyenTuTaiKhoan());
        values.put(SoTietKiem_GhiChu, soTietKiem.getGhiChu());
        values.put(SoTietKiem_NgayTatToan, soTietKiem.getStringNgayTatToan());
        db.update(Table_SoTietKiem, values, SoTietKiem_ID + " = ?",
                new String[]{String.valueOf(soTietKiem.getID())});
    }

    // function  xóa sổ tiết kiệm
    public void DeleteSoTietKiem(SoTietKiem soTietKiem) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Table_SoTietKiem, SoTietKiem_ID + " = ?",
                new String[]{String.valueOf(soTietKiem.getID())});
        db.close();
    }


    // function thêm 1 sổ bảng Chi
    public void InsertBangChi(Chi chi) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ThuChi_TenMucChi, chi.getTenMuc());
        values.put(ThuChi_TuTaiKhoan, chi.getTuTaiKhoan());
        values.put(ThuChi_NgayChi, chi.getStringNgayChi());
        values.put(ThuChi_Ai, chi.getChiChoAi());
        values.put(ThuChi_LoaiMuc, chi.getMaLoaiMuc());
        values.put(ThuChi_SoTien, chi.getSoTien());
        values.put(ThuChi_GhiChu, chi.getGhiChu());
        database.insert(Table_ThuChi, null, values);
        database.close();
    }

    // function lấy 1 bảng chi có id
    public Chi GetChi(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT  * FROM " + Table_ThuChi + "  where " + ThuChi_ID + " =" + String.valueOf(id);
        @SuppressLint("Recycle") Cursor cursor = db.rawQuery(selectQuery, null);
        Chi chi = null;
        if (cursor != null) {
            cursor.moveToFirst();
            chi = new
                    Chi(cursor.getInt(0), cursor.getString(1), cursor.getInt(2), cursor.getString(3),
                    cursor.getString(4), cursor.getString(5), cursor.getString(6),
                    cursor.getString(7));
        }
        return chi;
    }

    // function lấy danh sách bảng chi
    public ArrayList<Chi> GetAllChi(Chi.Type loai, Chi.Time tg) {
        ArrayList<Chi> listChi = new ArrayList<Chi>();
        String selectQuery = "SELECT  * FROM " + Table_ThuChi;
        SQLiteDatabase db = this.getWritableDatabase();
        @SuppressLint("Recycle") Cursor cursor = db.rawQuery(selectQuery, null);
        DateTime dateTime;
        DateTime dateTimenow = new DateTime();
        Calendar cal = Calendar.getInstance();
        @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat(DateTime.DATE_FORMAT);
        dateTimenow = new DateTime(sdf.format(cal.getTime()));
        Log.v("Tieu de", dateTimenow.getDayOfMonth() + "huy");
        if (cursor.moveToFirst()) {
            do {
                Chi chi = new Chi();
                chi.setID(Integer.parseInt(cursor.getString(0)));
                chi.setTenMuc(cursor.getString(1));
                chi.setMaLoaiMuc(cursor.getInt(2));
                chi.setTuTaiKhoan(cursor.getString(3));
                chi.setStringNgayChi(cursor.getString(4));
                chi.setChiChoAi(cursor.getString(5));
                chi.setSoTien(cursor.getString(6));
                chi.setGhiChu(cursor.getString(7));
                dateTime = new DateTime(cursor.getString(4));
                if (tg == Chi.Time.HomNay) {
                    if (dateTime.getDayOfMonth() == dateTimenow.getDayOfMonth()
                            && dateTime.getMonthOfYear() == dateTimenow.getMonthOfYear()
                            && dateTime.getYear() == dateTimenow.getYear()) {
                        if (loai == Chi.Type.TatCa)
                            listChi.add(chi);
                        else if (loai.getIntValue() == chi.getMaLoaiMuc()) {
                            listChi.add(chi);
                        }
                    }

                } else if (tg == Chi.Time.TuanNay) {
                    if (dateTimenow.getThu1() == 1) {

                        if (
                                dateTime.getDayOfMonth() <= dateTimenow.getDayOfMonth() && dateTime.getDayOfMonth()
                                        > dateTimenow.getDayOfMonth() - 7
                                        &&
                                        dateTime.getMonthOfYear() == dateTimenow.getMonthOfYear()
                                        && dateTime.getYear() == dateTimenow.getYear()) {
                            if (loai == Chi.Type.TatCa)
                                listChi.add(chi);
                            else if (loai.getIntValue() == chi.getMaLoaiMuc()) {
                                listChi.add(chi);
                            }
                        }
                    } else {

                        if (
                                dateTime.getDayOfMonth() >
                                        (dateTimenow.getDayOfMonth() -
                                                dateTimenow.getThu1()) + 1 &&
                                        dateTime.getDayOfMonth()
                                                < (dateTime.getDayOfMonth() + 7 - dateTimenow.getThu1())
                                        &&
                                        dateTime.getMonthOfYear() == dateTimenow.getMonthOfYear()
                                        && dateTime.getYear() == dateTimenow.getYear()) {
                            Log.e("notify", chi.getStringNgayChi());
                            if (loai == Chi.Type.TatCa)
                                listChi.add(chi);
                            else if (loai.getIntValue() == chi.getMaLoaiMuc()) {
                                listChi.add(chi);
                            }
                        }
                    }
                } else if (tg == Chi.Time.ThangNay) {
                    if (dateTime.getMonthOfYear() == dateTimenow.getMonthOfYear()
                            && dateTime.getYear() == dateTimenow.getYear()) {
                        if (loai == Chi.Type.TatCa)
                            listChi.add(chi);
                        else if (loai.getIntValue() == chi.getMaLoaiMuc()) {
                            listChi.add(chi);
                        }
                    }
                } else if (tg == Chi.Time.NamNay) {
                    if (dateTime.getYear() == dateTimenow.getYear()) {
                        if (loai == Chi.Type.TatCa)
                            listChi.add(chi);
                        else if (loai.getIntValue() == chi.getMaLoaiMuc()) {
                            listChi.add(chi);
                        }
                    }
                } else if (tg == Chi.Time.All) {
                    if (loai == Chi.Type.TatCa)
                        listChi.add(chi);
                    else if (loai.getIntValue() == chi.getMaLoaiMuc()) {
                        listChi.add(chi);
                    }
                }


            } while (cursor.moveToNext());
            Collections.sort(listChi, Chi.compare);
        }
        return listChi;
    }

    public Chi GetAllChiHienTai() {
        String selectQuery = "SELECT  * FROM " + Table_ThuChi;
        SQLiteDatabase db = this.getWritableDatabase();
        @SuppressLint("Recycle") Cursor cursor = db.rawQuery(selectQuery, null);
        Chi chi = new Chi();
        if (cursor.moveToLast()) {

            chi.setID(Integer.parseInt(cursor.getString(0)));
            chi.setTenMuc(cursor.getString(1));
            chi.setMaLoaiMuc(cursor.getInt(2));
            chi.setTuTaiKhoan(cursor.getString(3));
            chi.setStringNgayChi(cursor.getString(4));
            chi.setChiChoAi(cursor.getString(5));
            chi.setSoTien(cursor.getString(6));
            chi.setGhiChu(cursor.getString(7));
        }
        return chi;
    }


    // function  đếm số  bảng chi
    public int GetChiCount() {
        String countQuery = "SELECT  * FROM " + Table_ThuChi;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int dem = cursor.getCount();
        cursor.close();
        return dem;
    }

    // function  cập nhật 1 bảng chi
    public void UpdateChi(Chi chi) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ThuChi_TenMucChi, chi.getTenMuc());
        values.put(ThuChi_TuTaiKhoan, chi.getTuTaiKhoan());
        values.put(ThuChi_NgayChi, chi.getStringNgayChi());
        values.put(ThuChi_Ai, chi.getChiChoAi());
        values.put(ThuChi_LoaiMuc, chi.getMaLoaiMuc());
        values.put(ThuChi_SoTien, chi.getSoTien());
        values.put(ThuChi_GhiChu, chi.getGhiChu());
        db.update(Table_ThuChi, values, ThuChi_ID + " = ?",
                new String[]{String.valueOf(chi.getID())});
    }

    // xóa tất cả
    public void DeleteAllChi() {
        SQLiteDatabase db = this.getWritableDatabase();
        String selectQuery = "DELETE FROM " + Table_ThuChi;
        db.execSQL(selectQuery);
        db.close();
    }

    // function  xóa 1 bảng chi
    public void DeleteChi(Chi chi) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Table_ThuChi, ThuChi_ID + " = ?",
                new String[]{String.valueOf(chi.getID())});
        db.close();
    }

    // function thêm 1 sổ bảng mục chi cha
    public void InsertMucChiCha(MucChiCha mucChiCha) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(MucChiCha_TenMucChi, mucChiCha.getTenMucChi());
        values.put(MucChiCha_GhiChu, mucChiCha.getGhiChu());
        database.insert(Table_MucChiCha, null, values);
        database.close();
    }

    // function lấy 1 bảng mục chi cha có id
    public MucChiCha GetMucChiCha(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT  * FROM " + Table_MucChiCha + "  where " + MucChiCha_ID + " =" + String.valueOf(id);
        @SuppressLint("Recycle") Cursor cursor = db.rawQuery(selectQuery, null);
        MucChiCha mucChiCha = null;
        if (cursor != null) {
            cursor.moveToFirst();
            mucChiCha = new
                    MucChiCha(cursor.getInt(0), cursor.getString(1), cursor.getString(2));
        }
        return mucChiCha;
    }

    // function lấy danh sách bảng mục chi cha
    public ArrayList<MucChiCha> GetAllMucChiCha() {
        ArrayList<MucChiCha> listMucChiCha = new ArrayList<MucChiCha>();
        String selectQuery = "SELECT  * FROM " + Table_MucChiCha;
        SQLiteDatabase db = this.getWritableDatabase();
        @SuppressLint("Recycle") Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                MucChiCha mucChiCha = new MucChiCha();
                mucChiCha.setID(Integer.parseInt(cursor.getString(0)));
                mucChiCha.setTenMucChi(cursor.getString(1));
                mucChiCha.setGhiChu(cursor.getString(2));
                listMucChiCha.add(mucChiCha);
            } while (cursor.moveToNext());
        }
        return listMucChiCha;
    }

    // function  đếm số  bảng mục chi cha
    public int GetMucChiChaCount() {
        String countQuery = "SELECT  * FROM " + Table_MucChiCha;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int dem = cursor.getCount();
        cursor.close();
        return dem;
    }

    // function  cập nhật 1 bảng mục chi cha
    public int UpdateMucChiCha(MucChiCha mucChiCha) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(MucChiCha_TenMucChi, mucChiCha.getTenMucChi());
        values.put(MucChiCha_GhiChu, mucChiCha.getGhiChu());
        values.put(MucChiCha_GhiChu, mucChiCha.getGhiChu());
        return db.update(Table_MucChiCha, values, MucChiCha_ID + " = ?",
                new String[]{String.valueOf(mucChiCha.getID())});
    }

    // function  xóa 1 bảng MucChiCha
    public void DeleteMucChiCha(MucChiCha mucChiCha) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Table_MucChiCha, MucChiCha_ID + " = ?",
                new String[]{String.valueOf(mucChiCha.getID())});
        db.close();
    }

    // function  lấy tất cả tên bảng MucChiCha
    public ArrayList<String> GetAllTenMucChiCha() {
        ArrayList<String> Ten = new ArrayList<String>();
        String selectQuery = "SELECT  * FROM " + Table_MucChiCha;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Ten.add(cursor.getString(cursor.getColumnIndex(MucChiCha_TenMucChi)));
            } while (cursor.moveToNext());
        }
        return Ten;
    }

    // function lấy danh sách id MucChiCha
    public ArrayList<Integer> GetAllIDMucChiCha() {
        ArrayList<Integer> id = new ArrayList<Integer>();
        String selectQuery = "SELECT  * FROM " + Table_MucChiCha;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                id.add(cursor.getInt(cursor.getColumnIndex(MucChiCha_ID)));
            } while (cursor.moveToNext());
        }
        return id;
    }


    public void InsertMucChiCon(MucChiCon mucChiCon) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(MucChiCon_TenMucChi, mucChiCon.getTenMucChi());
        values.put(MucChiCon_idMucChiCha, mucChiCon.getMucChiCha());
        values.put(MucChiCon_GhiChu, mucChiCon.getGhiChu());
        database.insert(Table_MucChiCon, null, values);
        database.close();
    }

    // function lấy 1 bảng mục chi con có id
    public MucChiCon GetMucChiCon(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT  * FROM " + Table_MucChiCon + "  where " + MucChiCon_ID + " =" + String.valueOf(id);
        Cursor cursor = db.rawQuery(selectQuery, null);
        MucChiCon MucChiCon = null;
        if (cursor != null) {
            cursor.moveToFirst();
            MucChiCon = new
                    MucChiCon(cursor.getInt(0), cursor.getString(1), cursor.getInt(2)
                    , cursor.getString(3));
        }
        return MucChiCon;
    }

    // function lấy danh sách bảng mục chi con
    public ArrayList<MucChiCon> GetAllMucChiCon() {
        ArrayList<MucChiCon> listMucChiCon = new ArrayList<MucChiCon>();
        String selectQuery = "SELECT  * FROM " + Table_MucChiCon;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                MucChiCon mucChiCon = new MucChiCon();
                mucChiCon.setID(Integer.parseInt(cursor.getString(0)));
                mucChiCon.setTenMucChi(cursor.getString(1));
                mucChiCon.setMucChiCha(cursor.getInt(2));
                mucChiCon.setGhiChu(cursor.getString(3));
                listMucChiCon.add(mucChiCon);
            } while (cursor.moveToNext());
        }
        return listMucChiCon;
    }

    // function  đếm số  bảng mục chi con
    public int GetMucChiConCount() {
        String countQuery = "SELECT  * FROM " + Table_MucChiCon;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int dem = cursor.getCount();
        cursor.close();
        return dem;
    }

    // function  cập nhật 1 bảng mục chi con
    public int UpdateMucChiCon(MucChiCon MucChiCon) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(MucChiCon_TenMucChi, MucChiCon.getTenMucChi());
        values.put(MucChiCon_GhiChu, MucChiCon.getMucChiCha());
        values.put(MucChiCon_GhiChu, MucChiCon.getGhiChu());
        return db.update(Table_MucChiCon, values, MucChiCon_ID + " = ?",
                new String[]{String.valueOf(MucChiCon.getID())});
    }

    // function  xóa 1 bảng MucChiCon
    public void DeleteMucChiCon(MucChiCon MucChiCon) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Table_MucChiCon, MucChiCon_ID + " = ?",
                new String[]{String.valueOf(MucChiCon.getID())});
        db.close();
    }

    // function  lấy tất cả tên bảng MucChiCon
    public ArrayList<String> GetAllTenMucChiCon() {
        ArrayList<String> Ten = new ArrayList<String>();
        String selectQuery = "SELECT  * FROM " + Table_MucChiCon;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Ten.add(cursor.getString(cursor.getColumnIndex(MucChiCon_TenMucChi)));
            } while (cursor.moveToNext());
        }
        return Ten;
    }

    public ArrayList<MucChiCon> GetAllMucChiCon(int mucchicha) {
        ArrayList<MucChiCon> listMucChiCon = new ArrayList<MucChiCon>();
        String selectQuery = "SELECT  * FROM " + Table_MucChiCon + " where "
                + MucChiCon_idMucChiCha + " = " + mucchicha;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                MucChiCon mucChiCon = new MucChiCon();
                mucChiCon.setID(Integer.parseInt(cursor.getString(0)));
                mucChiCon.setTenMucChi(cursor.getString(1));
                mucChiCon.setMucChiCha(cursor.getInt(2));
                mucChiCon.setGhiChu(cursor.getString(3));
                listMucChiCon.add(mucChiCon);
            } while (cursor.moveToNext());
        }
        return listMucChiCon;
    }

    // function  lấy tất cả tên bảng MucChiCon theo mục cha
    public ArrayList<String> GetAllTenMucChiConInCha(int mucchicha) {
        ArrayList<String> Ten = new ArrayList<String>();
        String selectQuery = "SELECT  * FROM " + Table_MucChiCon + " where "
                + MucChiCon_idMucChiCha + " = " + String.valueOf(mucchicha);
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Ten.add(cursor.getString(cursor.getColumnIndex(MucChiCon_TenMucChi)));
            } while (cursor.moveToNext());
        }
        return Ten;
    }

    public ArrayList<String> GetAllTenMucChiConInCha(String mucchicha) {
        ArrayList<String> Ten = new ArrayList<String>();
        String selectQuery = "SELECT  * FROM " + Table_MucChiCon + " where "
                + MucChiCon_idMucChiCha + " = '" + mucchicha + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Ten.add(cursor.getString(cursor.getColumnIndex(MucChiCon_TenMucChi)));
            } while (cursor.moveToNext());
        }
        return Ten;
    }

    // function lấy danh sách id MucChiCon
    public ArrayList<Integer> GetAllIDMucChiCon() {
        ArrayList<Integer> id = new ArrayList<Integer>();
        String selectQuery = "SELECT  * FROM " + Table_MucChiCon;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                id.add(cursor.getInt(cursor.getColumnIndex(MucChiCon_ID)));
            } while (cursor.moveToNext());
        }
        return id;
    }


//    public void InsertChuyenKhoan(ChuyenKhoan chuyenKhoan) {
//        SQLiteDatabase database = this.getWritableDatabase();
//        ContentValues values = new ContentValues();
//        values.put(ChuyenKhoan_TuTK, chuyenKhoan.getTuTK());
//        values.put(ChuyenKhoan_VaoTK, chuyenKhoan.getVaoTK());
//        values.put(ChuyenKhoan_NgayChuyen, String.valueOf(chuyenKhoan.getNgayChuyen()));
//        values.put(ChuyenKhoan_SoTien, chuyenKhoan.getSoTien());
//        values.put(ChuyenKhoan_Phi, chuyenKhoan.getPhi());
//        values.put(ChuyenKhoan_GhiChu, chuyenKhoan.getGhiChu());
//        database.insert(Table_ChuyenKhoan, null, values);
//        database.close();
//    }

    // function lấy 1 bảng mục ChuyenKhoan có id
//    public ChuyenKhoan GetChuyenKhoan(int id) {
//        SQLiteDatabase db = this.getReadableDatabase();
//        String selectQuery = "SELECT  * FROM " + Table_ChuyenKhoan + "  where " + ChuyenKhoan_ID + " =" + String.valueOf(id);
//        Cursor cursor = db.rawQuery(selectQuery, null);
//        ChuyenKhoan chuyenKhoan = null;
//        if (cursor != null) {
//            cursor.moveToFirst();
//            chuyenKhoan = new ChuyenKhoan();
//            chuyenKhoan.setID(Integer.parseInt(cursor.getString(0)));
//            chuyenKhoan.setTuTK(cursor.getString(1));
//            chuyenKhoan.setVaoTK(cursor.getString(2));
//            chuyenKhoan.setStringNgayChuyen(cursor.getString(3));
//            chuyenKhoan.setSoTien(cursor.getString(4));
//            chuyenKhoan.setPhi(cursor.getString(5));
//            chuyenKhoan.setGhiChu(cursor.getString(6));
//        }
//        return chuyenKhoan;
//    }

    // function lấy danh sách bảng ChuyenKhoan
//    public ArrayList<ChuyenKhoan> GetAllChuyenKhoan() {
//        ArrayList<ChuyenKhoan> listChuyenKhoan = new ArrayList<ChuyenKhoan>();
//        String selectQuery = "SELECT  * FROM " + Table_ChuyenKhoan;
//        SQLiteDatabase db = this.getWritableDatabase();
//        Cursor cursor = db.rawQuery(selectQuery, null);
//        if (cursor.moveToFirst()) {
//            do {
//
//                ChuyenKhoan chuyenKhoan = new ChuyenKhoan();
//                chuyenKhoan = new ChuyenKhoan();
//                chuyenKhoan.setID(Integer.parseInt(cursor.getString(0)));
//                chuyenKhoan.setTuTK(cursor.getString(1));
//                chuyenKhoan.setVaoTK(cursor.getString(2));
//                chuyenKhoan.setStringNgayChuyen(cursor.getString(3));
//                chuyenKhoan.setSoTien(cursor.getString(4));
//                chuyenKhoan.setPhi(cursor.getString(5));
//                chuyenKhoan.setGhiChu(cursor.getString(6));
//                listChuyenKhoan.add(chuyenKhoan);
//            } while (cursor.moveToNext());
//        }
//        return listChuyenKhoan;
//    }

    // function  đếm số  bảng ChuyenKhoan
    public int GetChuyenKhoanCount() {
        String countQuery = "SELECT  * FROM " + Table_ChuyenKhoan;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int dem = cursor.getCount();
        cursor.close();
        return dem;
    }

    // function  cập nhật 1 bảng mục ChuyenKhoan
//    public int UpdateChuyenKhoan(ChuyenKhoan chuyenKhoan) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues values = new ContentValues();
//        values.put(ChuyenKhoan_TuTK, chuyenKhoan.getTuTK());
//        values.put(ChuyenKhoan_VaoTK, chuyenKhoan.getVaoTK());
//        values.put(ChuyenKhoan_NgayChuyen, String.valueOf(chuyenKhoan.getNgayChuyen()));
//        values.put(ChuyenKhoan_SoTien, chuyenKhoan.getSoTien());
//        values.put(ChuyenKhoan_Phi, chuyenKhoan.getPhi());
//        values.put(ChuyenKhoan_GhiChu, chuyenKhoan.getGhiChu());
//        return db.update(Table_ChuyenKhoan, values, ChuyenKhoan_ID + " = ?",
//                new String[]{String.valueOf(chuyenKhoan.getID())});
//    }

    // function  xóa 1 bảng ChuyenKhoan
//    public void DeleteChuyenKhoan(ChuyenKhoan ChuyenKhoan) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        db.delete(Table_ChuyenKhoan, ChuyenKhoan_ID + " = ?",
//                new String[]{String.valueOf(ChuyenKhoan.getID())});
//        db.close();
//    }
//12


    public void InsertPersonal(Personal personal) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Personal_Avatar, personal.getAvatar());
        values.put(Personal_Ten, personal.getTen());
        values.put(Personal_QuanHe, personal.getQuanHe());
        database.insert(Table_Personal, null, values);
        database.close();
    }

    // function lấy 1 bảng mục chi con có id
    public Personal GetPersonal(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT  * FROM " + Table_Personal + "  where " + Personal_ID + " =" + String.valueOf(id);
        Cursor cursor = db.rawQuery(selectQuery, null);
        Personal personal = null;
        if (cursor != null) {
            cursor.moveToFirst();
            personal = new
                    Personal(cursor.getInt(0), cursor.getInt(1), cursor.getString(2)
                    , cursor.getString(3));
        }
        return personal;
    }

    // function lấy danh sách bảng mục chi con
    public ArrayList<Personal> GetAllPersonal() {
        ArrayList<Personal> listPersonal = new ArrayList<Personal>();
        String selectQuery = "SELECT  * FROM " + Table_Personal;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Personal personal = new Personal();
                personal.setID(cursor.getInt(0));
                personal.setAvatar(cursor.getInt(1));
                personal.setTen(cursor.getString(2));
                personal.setQuanHe(cursor.getString(3));
                listPersonal.add(personal);
            } while (cursor.moveToNext());
        }
        return listPersonal;
    }

    // function  đếm số  bảng mục chi con
    public int GetPersonalCount() {
        String countQuery = "SELECT  * FROM " + Table_Personal;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int dem = cursor.getCount();
        cursor.close();
        return dem;
    }

    // function  cập nhật 1 bảng mục chi con
    public int UpdatePersonal(Personal Personal) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Personal_Avatar, Personal.getAvatar());
        values.put(Personal_Ten, Personal.getTen());
        values.put(Personal_QuanHe, Personal.getQuanHe());
        return db.update(Table_Personal, values, Personal_ID + " = ?",
                new String[]{String.valueOf(Personal.getID())});
    }

    // function  xóa 1 bảng Personal
    public void DeletePersonal(Personal personal) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Table_Personal, Personal_ID + " = ?",
                new String[]{String.valueOf(personal.getID())});
        db.close();
    }

    // function  lấy tất cả tên bảng Personal
    public ArrayList<String> GetAllTenPersonal() {
        ArrayList<String> Ten = new ArrayList<String>();
        String selectQuery = "SELECT  * FROM " + Table_Personal;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Ten.add(cursor.getString(cursor.getColumnIndex(Personal_Ten)));
            } while (cursor.moveToNext());
        }
        return Ten;
    }

    // function lấy danh sách id Personal
    public ArrayList<Integer> GetAllIDPersonal() {
        ArrayList<Integer> id = new ArrayList<Integer>();
        String selectQuery = "SELECT  * FROM " + Table_Personal;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                id.add(cursor.getInt(cursor.getColumnIndex(Personal_ID)));
            } while (cursor.moveToNext());
        }
        return id;
    }


    public void InsertTKDN(TKDN tk) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TKDN_Ten, tk.getTen());
        values.put(TKDN_Email, tk.getEmail());
        values.put(TKDN_MK, tk.getMK());
        values.put(TKDN_SDT, tk.getSDT());
        values.put(TKDN_CODE, tk.getCode());
        database.insert(Table_TKDN, null, values);
        database.close();
    }


    public TKDN getTKDN(String Email) {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT  * FROM " + Table_TKDN + "  where " + TKDN_Email + " ='" + String.valueOf(Email) + "'";
        Cursor cursor = db.rawQuery(selectQuery, null);
        TKDN taikhoan = null;
        if (cursor != null) {
            cursor.moveToFirst();
            taikhoan = new
                    TKDN(cursor.getInt(0), cursor.getString(1), cursor.getString(2),
                    cursor.getString(3), cursor.getString(4), cursor.getString(5));
        }
        return taikhoan;
    }

    // function lấy 1 tài khoản  có id
    public TKDN getTKDN(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT  * FROM " + Table_TKDN + "  where " + TKDN_ID + " = " + String.valueOf(id);
        Cursor cursor = db.rawQuery(selectQuery, null);
        TKDN taikhoan = null;
        if (cursor != null) {
            cursor.moveToFirst();
            taikhoan = new
                    TKDN(cursor.getInt(0), cursor.getString(1), cursor.getString(2),
                    cursor.getString(3), cursor.getString(4), cursor.getString(5));
        }
        return taikhoan;
    }

    // function lấy danh sách tài khoản
    public ArrayList<TKDN> GetAllTKDN() {
        ArrayList<TKDN> listTKDN = new ArrayList<TKDN>();
        String selectQuery = "SELECT  * FROM " + Table_TKDN;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                TKDN taikhoan = new TKDN();
                taikhoan.setID(Integer.parseInt(cursor.getString(0)));
                taikhoan.setTen(cursor.getString(1));
                taikhoan.setEmail(cursor.getString(2));
                taikhoan.setMK(cursor.getString(3));
                taikhoan.setSDT(cursor.getString(4));
                taikhoan.setCode(cursor.getString(5));
                listTKDN.add(taikhoan);
            } while (cursor.moveToNext());
        }
        return listTKDN;
    }

    // function lấy danh sách tên tài khoản
    public ArrayList<String> GetAllTenTKDN() {
        ArrayList<String> Ten = new ArrayList<String>();
        String selectQuery = "SELECT  * FROM " + Table_TKDN;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Ten.add(cursor.getString(cursor.getColumnIndex(TKDN_Ten)));
            } while (cursor.moveToNext());
        }
        return Ten;
    }

    // function lấy danh sách id tài khoản
    public ArrayList<Integer> GetAllIDTKDN() {
        ArrayList<Integer> id = new ArrayList<Integer>();
        String selectQuery = "SELECT  * FROM " + Table_TKDN;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                id.add(cursor.getInt(cursor.getColumnIndex(TKDN_ID)));
            } while (cursor.moveToNext());
        }
        return id;
    }

    // function  đếm số tài khoản
    public int GetTKDNCount() {
        String countQuery = "SELECT  * FROM " + Table_TKDN;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int dem = cursor.getCount();
        cursor.close();
        return dem;
    }

    // function  cập nhật tài khoản
    public int UpdateTKDN(TKDN taikhoan) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TKDN_Ten, taikhoan.getTen());
        values.put(TKDN_Email, taikhoan.getEmail());
        values.put(TKDN_MK, taikhoan.getMK());
        values.put(TKDN_SDT, taikhoan.getSDT());
        values.put(TKDN_CODE, taikhoan.getCode());
        return db.update(Table_TKDN, values, TKDN_ID + " = ?",
                new String[]{String.valueOf(taikhoan.getID())});
    }

    public void UpdateCode(String code) {
        String selectQuery = "UPDATE " + Table_TKDN + " " + TKDN_CODE + " ='" + code + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

    }

    // function  xóa tài khoản
    public void DeleteTKDN(TKDN taikhoan) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Table_TKDN, TKDN_ID + " = ?",
                new String[]{String.valueOf(taikhoan.getID())});
        db.close();
    }

}
