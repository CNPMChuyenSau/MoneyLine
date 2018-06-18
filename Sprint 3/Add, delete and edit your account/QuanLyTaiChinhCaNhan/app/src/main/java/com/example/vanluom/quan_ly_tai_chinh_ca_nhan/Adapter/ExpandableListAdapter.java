package com.example.vanluom.quan_ly_tai_chinh_ca_nhan.Adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.vanluom.quan_ly_tai_chinh_ca_nhan.R;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

//For expandable list view use BaseExpandableListAdapter
public class ExpandableListAdapter extends BaseExpandableListAdapter {
    private Context _context;
    private List<String> header; // header titles
    private HashMap<String, List<String>> child;

    public ExpandableListAdapter(Context context, List<String> listDataHeader,
                                 HashMap<String, List<String>> listChildData) {
        this._context = context;
        this.header = listDataHeader;
        this.child = listChildData;
    }

    @Override
    public Object getChild(int groupPosition, int childPosititon) {
        return this.child.get(this.header.get(groupPosition)).get(
                childPosititon);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        View view = convertView;

        final String childText = (String) getChild(groupPosition, childPosition);
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            view = Objects.requireNonNull(inflater).inflate(R.layout.item_category_list_child, parent, false);
        }

        ImageView imgCheckedIncomeCategory = (ImageView) view.findViewById(R.id.imgCheckedIncomeCatagory);
        imgCheckedIncomeCategory.setVisibility(View.GONE);
        TextView child_text = (TextView) view.findViewById(R.id.txtChild);
        child_text.setText(childText);

        return view;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this.child.get(this.header.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this.header.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return this.header.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, final boolean isExpanded,
                             View convertView, ViewGroup parent) {

        View view = convertView;

        String headerTitle = (String) getGroup(groupPosition);
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = Objects.requireNonNull(inflater).inflate(R.layout.item_category_group, parent, false);
        }

        final LinearLayout linearLayoutChecked = (LinearLayout) view.findViewById(R.id.linear_layout_checked);
        final ImageView imgIndicator = (ImageView) view.findViewById(R.id.image_indicator);
        final TextView header_text = (TextView) view.findViewById(R.id.header);

        imgIndicator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (linearLayoutChecked.getVisibility() == View.GONE) {
                    linearLayoutChecked.setVisibility(View.VISIBLE);
                    imgIndicator.setImageResource(R.drawable.group_collapse);
                    header_text.setTypeface(null, Typeface.BOLD);

                } else {
                    linearLayoutChecked.setVisibility(View.GONE);
                    imgIndicator.setImageResource(R.drawable.group_expand);
                    header_text.setTypeface(null, Typeface.NORMAL);
                }
            }
        });

        header_text.setText(headerTitle);

        if (isExpanded) {
            linearLayoutChecked.setVisibility(View.VISIBLE);
            imgIndicator.setImageResource(R.drawable.group_collapse);
            header_text.setTypeface(null, Typeface.BOLD);

        } else {
            linearLayoutChecked.setVisibility(View.GONE);
            imgIndicator.setImageResource(R.drawable.group_expand);
            header_text.setTypeface(null, Typeface.NORMAL);
        }

        return view;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}