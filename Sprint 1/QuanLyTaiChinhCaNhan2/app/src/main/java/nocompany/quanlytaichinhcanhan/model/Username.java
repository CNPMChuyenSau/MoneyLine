package nocompany.quanlytaichinhcanhan.model;

public class Username {
    private String mEmail;
    private String mPhonenumber;
    private String mPasswork;
    private String mGioitinh;
    private String mLoaivitien;
    private String mSotien;

    public Username(String mEmail, String mPhonenumber, String mPasswork, String mGioitinh, String mLoaivitien, String mSotien) {
        this.mEmail = mEmail;
        this.mPhonenumber = mPhonenumber;
        this.mPasswork = mPasswork;
        this.mGioitinh = mGioitinh;
        this.mLoaivitien = mLoaivitien;
        this.mSotien = mSotien;
    }

    public Username() {
    }



    public String getmEmail() {
        return mEmail;
    }

    public void setmEmail(String mEmail) {
        this.mEmail = mEmail;
    }

    public String getmPhonenumber() {
        return mPhonenumber;
    }

    public void setmPhonenumber(String mPhonenumber) {
        this.mPhonenumber = mPhonenumber;
    }

    public String getmPasswork() {
        return mPasswork;
    }

    public void setmPasswork(String mPasswork) {
        this.mPasswork = mPasswork;
    }

    public String getmGioitinh() {
        return mGioitinh;
    }

    public void setmGioitinh(String mGioitinh) {
        this.mGioitinh = mGioitinh;
    }

    public String getmLoaivitien() {
        return mLoaivitien;
    }

    public void setmLoaivitien(String mLoaivitien) {
        this.mLoaivitien = mLoaivitien;
    }

    public String getmSotien() {
        return mSotien;
    }

    public void setmSotien(String mSotien) {
        this.mSotien = mSotien;
    }
}
