package com.example.vanluom.quan_ly_tai_chinh_ca_nhan.model;

import android.util.Log;

import com.example.vanluom.quan_ly_tai_chinh_ca_nhan.Control.DateTime;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;


public class Chi {

    //  Các thuộc tính tạo bản chi
    private int ID;
    private String TenMuc;
    private String TuTaiKhoan;
    private Date NgayChi;
    private int MaLoaiMuc;
    private String ChiChoAi;
    private String SoTien;
    private String GhiChu;
    DateTime dateTime = new DateTime();
    public static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);

    public Chi() {
    }

    public Chi(int ID, String TenMucChi, int LoaiMuc, String TuTaiKhoan, String NgayChi, String ChiChoAi, String SoTien, String GhiChu) {
        this.GhiChu = GhiChu;
        this.ChiChoAi = ChiChoAi;
        this.MaLoaiMuc = LoaiMuc;
        this.ID = ID;
        try {
            this.NgayChi = dateFormat.parse(NgayChi);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        this.TenMuc = TenMucChi;
        this.SoTien = SoTien;
        this.TuTaiKhoan = TuTaiKhoan;
    }

    public Chi(String TenMucChi, int LoaiMuc, String TuTaiKhoan, String NgayChi, String ChiChoAi, String SoTien, String GhiChu) {
        this.GhiChu = GhiChu;
        this.ChiChoAi = ChiChoAi;
        this.MaLoaiMuc = LoaiMuc;
        try {
            this.NgayChi = dateFormat.parse(NgayChi);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        this.TenMuc = TenMucChi;
        this.SoTien = SoTien;

        this.TuTaiKhoan = TuTaiKhoan;
    }

    public int getMaLoaiMuc() {
        return MaLoaiMuc;
    }

    public void setMaLoaiMuc(int maLoaiMuc) {
        MaLoaiMuc = maLoaiMuc;
    }

    public String getGhiChu() {
        return GhiChu;
    }

    public void setGhiChu(String chi_GhiChu) {
        GhiChu = chi_GhiChu;
    }

    public String getChiChoAi() {
        return ChiChoAi;
    }

    public void setChiChoAi(String chiChoAi) {
        ChiChoAi = chiChoAi;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public Date getNgayChi() {
        return NgayChi;
    }

    public void setNgayChi(Date ngayChi) {
        NgayChi = ngayChi;
    }

    public String getStringNgayChi() {
        return dateFormat.format(NgayChi);
    }

    public void setStringNgayChi(String date) {
        try {
            NgayChi = dateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public String getTenMuc() {
        return TenMuc;
    }

    public void setTenMuc(String tenMuc) {
        TenMuc = tenMuc;
    }

    public String getTuTaiKhoan() {
        return TuTaiKhoan;
    }

    public void setTuTaiKhoan(String tuTaiKhoan) {
        TuTaiKhoan = tuTaiKhoan;
    }

    public String getSoTien() {
        return SoTien;
    }

    public void setSoTien(String soTien) {
        SoTien = soTien;
    }

    public static Comparator<Chi> compare = new Comparator<Chi>() {

        @Override
        public int compare(Chi o1, Chi o2) {
            DateTime d1 = new DateTime(o1.getNgayChi());
            DateTime d2 = new DateTime(o2.getNgayChi());
            Log.e("dfdf", "dung rooi");
            if (Integer.valueOf(d1.getYear()).compareTo((Integer.valueOf(d2.getYear()))) == 0) {
                if (Integer.valueOf(d1.getMonthOfYear()).compareTo(Integer.valueOf(d2.getMonthOfYear())) == 0) {
                    if (Integer.valueOf(d1.getDayOfMonth()).compareTo(Integer.valueOf(d2.getDayOfMonth())) == 0) {
                        return (Integer.valueOf(d1.getHourOfDay()).compareTo(Integer.valueOf(d2.getHourOfDay())))*-1;
                    }else
                        return Integer.valueOf(d1.getDayOfMonth()).compareTo(Integer.valueOf(d2.getDayOfMonth()))*-1;
                }
                else
                    return
                            Integer.valueOf(d1.getMonthOfYear()).compareTo(Integer.valueOf(d2.getMonthOfYear()))*-1;

            } else
               return Integer.valueOf(d1.getYear()).compareTo((Integer.valueOf(d2.getYear())))*-1;
        }


    };

    public static enum Time {
        HomNay("HomNay", 1),
        TuanNay("TuanNay", 7),
        ThangNay("ThangNay",31),
        NamNay("NamNay", 365),
        All("All", 0) {
            @Override
            public int getIntValue() {
                return super.getIntValue();
            }
        };
        private String stringValue;
        private int intValue;
        private Time(String toString, int value) {
            stringValue = toString;
            intValue = value;
        }
        private Time() {
        }
        public int getIntValue() {
            return intValue;
        }
        @Override
        public String toString() {
            return stringValue;
        }
    }
    public static enum Type {
        Thu("Thu", 2),
        CHi("Chi", 1),
        Vay("Vay", 3),
        ChiVay("ChiVay", 4),
        TatCa("TatCa",5){
            @Override
            public int getIntValue() {
                return super.getIntValue();
            }
        };
        private String stringValue;
        private int intValue;
        private Type(String toString, int value) {
            stringValue = toString;
            intValue = value;
        }
        private Type() {
        }
        public int getIntValue() {
            return intValue;
        }
        @Override
        public String toString() {
            return stringValue;
        }
    }
}