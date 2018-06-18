package com.example.vanluom.quan_ly_tai_chinh_ca_nhan.Model;


public class MucChiCha {

    //  Các thuộc tính tạo bản chi cha
    private int ID;
    private String TenMucChi;
    private String GhiChu;

    public MucChiCha() {
    }

    public MucChiCha(int ID, String tenMucChi, String ghiChu) {
        this.GhiChu = ghiChu;
        this.ID = ID;
        this.TenMucChi = tenMucChi;
    }

    public MucChiCha(String tenMucChi, String ghiChu) {
        this.GhiChu = ghiChu;
        this.TenMucChi = tenMucChi;
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

    public String getTenMucChi() {
        return TenMucChi;
    }

    public void setTenMucChi(String tenMucChi) {
        TenMucChi = tenMucChi;
    }
}
