package com.example.vanluom.quan_ly_tai_chinh_ca_nhan.Model;


public class MucChiCon {

    //  Thuộc tính tạo bản chi con
    private int ID;
    private String TenMucChi;
    private int MucChiCha;
    private String GhiChu;

    public MucChiCon() {
    }

    public MucChiCon(int ID, String TenMucChi, int idMucChiCha, String GhiChu) {
        this.GhiChu = GhiChu;
        this.ID = ID;
        this.MucChiCha = idMucChiCha;
        this.TenMucChi = TenMucChi;
    }

    public MucChiCon(String TenMucChi, int idMucChiCha, String GhiChu) {
        this.GhiChu = GhiChu;
        this.TenMucChi = TenMucChi;
        this.MucChiCha = idMucChiCha;
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

    public int getMucChiCha() {
        return MucChiCha;
    }

    public void setMucChiCha(int mucChiCha) {
        MucChiCha = mucChiCha;
    }
}
