package nocompany.quanlytaichinhcanhan.model;

public class Item_Gioitinh {
    public Integer icons;
    public String gioitinh;

    public Item_Gioitinh(Integer icons, String gioitinh) {
        this.icons = icons;
        this.gioitinh = gioitinh;
    }

    public Integer getIcons() {
        return icons;
    }

    public void setIcons(Integer icons) {
        this.icons = icons;
    }

    public String getGioitinh() {
        return gioitinh;
    }

    public void setGioitinh(String gioitinh) {
        this.gioitinh = gioitinh;
    }
}
