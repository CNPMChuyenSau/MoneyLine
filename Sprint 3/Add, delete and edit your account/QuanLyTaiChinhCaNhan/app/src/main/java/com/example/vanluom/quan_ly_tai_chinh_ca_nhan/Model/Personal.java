package com.example.vanluom.quan_ly_tai_chinh_ca_nhan.Model;


public class Personal {
    int ID;
    int Avatar;
    String Ten;
    String QuanHe;

    public Personal(String ten, String quanHe) {
        Ten = ten;
        QuanHe = quanHe;
    }

    public Personal() { }

    public Personal(int avatar, String ten, String quanHe) {
        this.Avatar = avatar;
        QuanHe = quanHe;
        Ten = ten;
    }

    public Personal(int ID, int avatar, String ten, String quanHe) {
        Avatar = avatar;
        this.ID = ID;
        QuanHe = quanHe;
        Ten = ten;
    }

    public int getAvatar() {
        return Avatar;
    }

    public void setAvatar(int avatar) {
        Avatar = avatar;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getQuanHe() {
        return QuanHe;
    }

    public void setQuanHe(String quanHe) {
        QuanHe = quanHe;
    }

    public String getTen() {
        return Ten;
    }

    public void setTen(String ten) {
        Ten = ten;
    }
}
