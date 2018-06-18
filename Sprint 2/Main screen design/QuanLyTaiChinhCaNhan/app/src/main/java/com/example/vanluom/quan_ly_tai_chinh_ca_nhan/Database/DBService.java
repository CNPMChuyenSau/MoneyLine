package com.example.vanluom.quan_ly_tai_chinh_ca_nhan.Database;

import android.content.Context;
import android.widget.Toast;

import com.example.vanluom.quan_ly_tai_chinh_ca_nhan.R;
import com.example.vanluom.quan_ly_tai_chinh_ca_nhan.model.MucChiCha;
import com.example.vanluom.quan_ly_tai_chinh_ca_nhan.model.MucChiCon;
import com.example.vanluom.quan_ly_tai_chinh_ca_nhan.model.Personal;
import com.example.vanluom.quan_ly_tai_chinh_ca_nhan.model.SoTietKiem;
import com.example.vanluom.quan_ly_tai_chinh_ca_nhan.model.TK;

// Thêm dữ liệu khi mới tạo app
public class DBService {
    public DBService() {
    }

    public static void ThemDuLieu(Context c) {
        try {

            SQLite database = new SQLite(c);
            if (database.GetTKCount() == 0) {
                database.InsertTaiKhoan(new TK("Agribank", "Ngân hàng", "2000000", ""));
                database.InsertTaiKhoan(new TK("Sacom bank", "Ngân hàng", "4000000", ""));
                database.InsertTaiKhoan(new TK("BIDV", "Ngân hàng", "8000000", ""));
                database.InsertTaiKhoan(new TK("Viettin bank", "Ngân hàng", "7000000", ""));
                database.InsertTaiKhoan(new TK("VP bank", "Ngân hàng", "400000", ""));
                database.InsertTaiKhoan(new TK("ACB bank", "Ngân hàng", "400000", ""));
                database.InsertMucChiCha(new MucChiCha("Ăn uống", "Tất cả mọi thứ về ăn uống"));
                database.InsertMucChiCha(new MucChiCha("Sinh hoạt", "Chưa có"));
                database.InsertMucChiCha(new MucChiCha("Đi lại", "Chưa có"));
                database.InsertMucChiCha(new MucChiCha("Vui chơi", "Chưa có"));
                database.InsertMucChiCon(new MucChiCon("Kẹo", 1, ""));
                database.InsertMucChiCon(new MucChiCon("Bánh", 1, ""));
                database.InsertMucChiCon(new MucChiCon("Mức", 1, ""));
                database.InsertMucChiCon(new MucChiCon("Sữa", 1, ""));
                database.InsertMucChiCon(new MucChiCon("Rau", 1, ""));
                database.InsertMucChiCon(new MucChiCon("Má", 1, ""));
                database.InsertMucChiCon(new MucChiCon("Tiền điện", 2, ""));
                database.InsertMucChiCon(new MucChiCon("Tiền phòng", 2, ""));
                database.InsertMucChiCon(new MucChiCon("Tiền nước", 2, ""));
                database.InsertMucChiCon(new MucChiCon("Xe bus", 3, ""));
                database.InsertMucChiCon(new MucChiCon("Xăng", 3, ""));
                database.InsertMucChiCon(new MucChiCon("Đi công viên", 4, ""));
                database.InsertMucChiCon(new MucChiCon("Xem phim", 4, ""));
                database.InsertPersonal(new Personal(R.drawable.account_other, "Chưa có", "Chưa có"));
                database.InsertPersonal(new Personal(R.drawable.account_other, "Chưa có", "Chưa có"));
                database.InsertPersonal(new Personal(R.drawable.account_other, "Chưa có", "Chưa có"));
                database.InsertPersonal(new Personal(R.drawable.account_other, "Chưa có", "Chưa có"));
                SoTietKiem s = new SoTietKiem("Sổ tiết kiệm", "Agribank", "5000000", "2016-12-12 12:12:12", "2018-12-12 12:12:12", 0.2, "vi1", "Chưa có", "2018-12-12 12:12:12");
                database.InsertSoTietKiem(s);
                database.InsertSoTietKiem(s);
                database.InsertSoTietKiem(s);
                database.InsertSoTietKiem(s);
            }

        } catch (Exception e) {
            Toast.makeText(c, "Lỗi dữ liệu" + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
