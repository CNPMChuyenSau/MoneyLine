package com.example.vanluom.quan_ly_tai_chinh_ca_nhan.Model;


public class TK {

    //  Các thuộc tính tạo bảng tài khoản
    private int ID;
    private String TenTK;
    private String LoaiTK;
    private String SoTien;
    private int AvaTar;
    private String GhiChu;

    public TK() {

    }

    public TK(String TenTK, String LoaiTK, String SoTien, String GhiChu) {
        this.GhiChu = GhiChu;
        this.LoaiTK = LoaiTK;
        this.SoTien = SoTien;
        this.TenTK = TenTK;
    }

    public TK(int ID, String TenTK, String LoaiTK, String SoTien, String GhiChu) {
        this.GhiChu = GhiChu;
        this.ID = ID;
        this.LoaiTK = LoaiTK;
        this.SoTien = SoTien;
        this.TenTK = TenTK;
    }

    public TK(int ID, String TenTK, String LoaiTK, String SoTien, int AvaTar, String GhiChu) {
        this.AvaTar = AvaTar;
        this.GhiChu = GhiChu;
        this.ID = ID;
        this.LoaiTK = LoaiTK;
        this.SoTien = SoTien;
        this.TenTK = TenTK;
    }

    public TK(String TenTK, String LoaiTK, String SoTien, int AvaTar, String GhiChu) {
        this.AvaTar = AvaTar;
        this.GhiChu = GhiChu;
        this.LoaiTK = LoaiTK;
        this.SoTien = SoTien;
        this.TenTK = TenTK;
    }

    public int getAvaTar() {
        return AvaTar;
    }

    public void setAvaTar(int avaTar) {
        AvaTar = avaTar;
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

    public String getLoaiTK() {
        return LoaiTK;
    }

    public void setLoaiTK(String loaiTK) {
        LoaiTK = loaiTK;
    }

    public String getSoTien() {
        return SoTien;
    }

    public void setSoTien(String soTien) {
        SoTien = soTien;
    }

    public String getTenTK() {
        return TenTK;
    }

    public void setTenTK(String tenTK) {
        TenTK = tenTK;
    }
}
