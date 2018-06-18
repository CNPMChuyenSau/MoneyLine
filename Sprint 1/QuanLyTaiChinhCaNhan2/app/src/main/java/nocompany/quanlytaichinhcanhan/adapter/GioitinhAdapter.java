package nocompany.quanlytaichinhcanhan.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import nocompany.quanlytaichinhcanhan.R;
import nocompany.quanlytaichinhcanhan.model.Item_Gioitinh;

public class GioitinhAdapter extends BaseAdapter {
    Context context;
    int myLayout;
    List<Item_Gioitinh> arrayList;


    public GioitinhAdapter(Context context, int myLayout, List<Item_Gioitinh> arrayList) {
        this.context = context;
        this.myLayout = myLayout;
        this.arrayList = arrayList;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(myLayout, null);

        TextView txt_gioitinh = (TextView)convertView.findViewById(R.id.ac_txt_gioitinh);
        ImageView ing_icon = (ImageView)convertView.findViewById(R.id.ac_img_anh);

        txt_gioitinh.setText(arrayList.get(position).gioitinh);
        ing_icon.setImageResource(arrayList.get(position).icons);

        return convertView;
    }
}
