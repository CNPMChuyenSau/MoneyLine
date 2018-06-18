package com.example.vanluom.quan_ly_tai_chinh_ca_nhan.model;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class ChuyenKhoan {

    //  Thuộc tính tạo chuyển khoản
    private int ID;
    private String TuTK;
    private String VaoTK;
    private Date NgayChuyen;
    private String SoTien;
    private String Phi;
    private String GhiChu;
    public static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);

    public ChuyenKhoan() {
    }

    public ChuyenKhoan(int ID, String TuTK, String VaoTK, String NgayChuyen, String SoTien, String Phi, String GhiChu) {
        this.GhiChu = GhiChu;
        this.ID = ID;
        try {
            this.NgayChuyen = dateFormat.parse(NgayChuyen);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        this.Phi = Phi;
        this.SoTien = SoTien;
        this.TuTK = TuTK;
        this.VaoTK = VaoTK;
    }

    public ChuyenKhoan(String TuTK, String VaoTK, String NgayChuyen, String SoTien, String Phi, String GhiChu) {
        this.GhiChu = GhiChu;
        try {
            this.NgayChuyen = dateFormat.parse(NgayChuyen);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        this.Phi = Phi;
        this.SoTien = SoTien;
        this.TuTK = TuTK;
        this.VaoTK = VaoTK;
    }

    public String getGhiChu() {
        return GhiChu;
    }

    public void setGhiChu(String ghiChu) {
        GhiChu = ghiChu;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public Date getNgayChuyen() {
        return NgayChuyen;
    }

    public void setNgayChuyen(Date ngayChuyen) {
        NgayChuyen = ngayChuyen;
    }

    public String getStringNgayChuyen() {
        return dateFormat.format(NgayChuyen);
    }

    public void setStringNgayChuyen(String date) {
        try {
            NgayChuyen = dateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public String getPhi() {
        return Phi;
    }

    public void setPhi(String phi) {
        Phi = phi;
    }

    public String getSoTien() {
        return SoTien;
    }

    public void setSoTien(String soTien) {
        SoTien = soTien;
    }

    public String getTuTK() {
        return TuTK;
    }

    public void setTuTK(String tuTK) {
        TuTK = tuTK;
    }

    public String getVaoTK() {
        return VaoTK;
    }

    public void setVaoTK(String vaoTK) {
        VaoTK = vaoTK;
    }
}
