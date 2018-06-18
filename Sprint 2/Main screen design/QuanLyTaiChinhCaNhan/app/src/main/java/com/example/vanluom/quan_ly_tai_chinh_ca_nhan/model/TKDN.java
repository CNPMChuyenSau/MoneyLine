package com.example.vanluom.quan_ly_tai_chinh_ca_nhan.model;


public class TKDN {
    int ID;
    String Ten;
    String Email;
    String MK;
    String SDT;
    String Code;

    public TKDN() {
    }

    public TKDN(int ID, String ten, String email, String MK, String SDT, String code) {
        Email = email;
        this.ID = ID;
        this.MK = MK;
        this.SDT = SDT;
        Ten = ten;
        this.Code = code;
    }

    public TKDN(String ten, String email, String MK, String SDT, String code) {
        Email = email;
        this.ID = ID;
        this.MK = MK;
        this.SDT = SDT;
        Ten = ten;
        this.Code = code;
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getMK() {
        return MK;
    }

    public void setMK(String MK) {
        this.MK = MK;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public String getTen() {
        return Ten;
    }

    public void setTen(String ten) {
        Ten = ten;
    }
}
