package com.vanluom.group11.quanlytaichinhcanhan.home;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.vanluom.group11.quanlytaichinhcanhan.R;

public class DrawerMenuItemAdapter
    extends ArrayAdapter<DrawerMenuItem> {

	public DrawerMenuItemAdapter(Context context) {
		super(context, 0);

		this.context = context;
	}

	private Context context;

    public Context getContext() {
        return this.context;
    }

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		DrawerMenuItem item = getItem(position);

		DrawerViewHolder holder = null;
		View view = convertView;
		
		if (view == null) {
			view = LayoutInflater.from(getContext()).inflate(R.layout.item_drawer, null);
            view.setTag(new DrawerViewHolder(view));
        }
		
		if (view != null && holder == null) {
			if (view.getTag() instanceof DrawerViewHolder) {
				holder = (DrawerViewHolder)view.getTag();
			}
		}

		if (item != null && holder != null) {
            holder.textViewItem.setText(item.getText());
            holder.viewDivider.setVisibility(item.hasDivider() ? View.VISIBLE : View.GONE);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                if (item.getIcon() != null) {
                    holder.imageViewIcon.setBackgroundResource(item.getIcon());
                }
                if (item.getIconDrawable() != null) {
                    holder.imageViewIcon.setBackground(item.getIconDrawable());
                }
            } else {
                holder.imageViewIcon.setBackgroundDrawable(item.getIconDrawable());
            }
		}
		
		return view;
	}
}
