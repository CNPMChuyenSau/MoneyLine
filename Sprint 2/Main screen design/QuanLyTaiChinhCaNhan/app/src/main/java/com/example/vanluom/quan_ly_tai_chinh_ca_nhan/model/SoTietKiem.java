package com.example.vanluom.quan_ly_tai_chinh_ca_nhan.model;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class SoTietKiem {


    //  Các thuộc tính tạo sổ tiết kiệm
    private int ID;
    private String TenSoTK;
    private String NganHang;
    private String SoTienBanDau;
    private Date NgayGui;
    private Date NgayHetHan;
    private double LaiXuat;
    private String ChuyenTuTaiKhoan;
    private String GhiChu;
    private Date NgayTatToan;
    public static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);

    public SoTietKiem() {
    }

    public SoTietKiem(int ID, String TenSoTK, String NganHang, String SoTienBanDau, String NgayGui, String NgayHetHan, double LaiXuat, String ChuyenTuTaiKhoan, String GhiChu, String NgayTatToan) {
        this.ChuyenTuTaiKhoan = ChuyenTuTaiKhoan;
        this.GhiChu = GhiChu;
        this.ID = ID;
        this.LaiXuat = LaiXuat;
        this.NganHang = NganHang;
        try {
            this.NgayGui = dateFormat.parse(NgayGui);
            this.NgayHetHan = dateFormat.parse(NgayHetHan);
            this.NgayTatToan = dateFormat.parse(NgayTatToan);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        this.SoTienBanDau = SoTienBanDau;
        this.TenSoTK = TenSoTK;
    }

    public SoTietKiem(String TenSoTK, String NganHang, String SoTienBanDau, String NgayGui, String NgayHetHan, double LaiXuat, String ChuyenTuTaiKhoan, String GhiChu, String NgayTatToan) {
        this.ChuyenTuTaiKhoan = ChuyenTuTaiKhoan;
        this.GhiChu = GhiChu;
        this.LaiXuat = LaiXuat;
        this.NganHang = NganHang;
        try {
            this.NgayGui = dateFormat.parse(NgayGui);
            this.NgayHetHan = dateFormat.parse(NgayHetHan);
            this.NgayTatToan = dateFormat.parse(NgayTatToan);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        this.SoTienBanDau = SoTienBanDau;
        this.TenSoTK = TenSoTK;
    }


    public String getChuyenTuTaiKhoan() {
        return ChuyenTuTaiKhoan;
    }

    public void setChuyenTuTaiKhoan(String chuyenTuTaiKhoan) {
        ChuyenTuTaiKhoan = chuyenTuTaiKhoan;
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

    public double getLaiXuat() {
        return LaiXuat;
    }

    public void setLaiXuat(double laiXuat) {
        LaiXuat = laiXuat;
    }

    public String getNganHang() {
        return NganHang;
    }

    public void setNganHang(String nganHang) {
        NganHang = nganHang;
    }

    public Date getNgayGui() {
        return NgayGui;
    }

    public void setNgayGui(Date ngayGui) {
        NgayGui = ngayGui;
    }

    public String getStringNgayGui() {
        return dateFormat.format(NgayGui);
    }

    public void setStringNgayGui(String date) {
        try {
            NgayGui = dateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public Date getNgayHetHan() {
        return NgayHetHan;
    }

    public void setNgayHetHan(Date ngayHetHan) {
        NgayHetHan = ngayHetHan;
    }

    public String getStringNgayHetHan() {
        return dateFormat.format(NgayHetHan);
    }

    public void setStringNgayHetHan(String date) {
        try {
            NgayHetHan = dateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public Date getNgayTatToan() {
        return NgayTatToan;
    }

    public void setNgayTatToan(Date ngayTatToan) {
        NgayTatToan = ngayTatToan;
    }

    public String getStringNgayTatToan() {
        return dateFormat.format(NgayTatToan);
    }

    public void setStringNgayTatToan(String date) {
        try {
            NgayTatToan = dateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public String getSoTienBanDau() {
        return SoTienBanDau;
    }

    public void setSoTienBanDau(String soTienBanDau) {
        SoTienBanDau = soTienBanDau;
    }

    public String getTenSoTK() {
        return TenSoTK;
    }

    public void setTenSoTK(String tenSoTK) {
        TenSoTK = tenSoTK;
    }
}
