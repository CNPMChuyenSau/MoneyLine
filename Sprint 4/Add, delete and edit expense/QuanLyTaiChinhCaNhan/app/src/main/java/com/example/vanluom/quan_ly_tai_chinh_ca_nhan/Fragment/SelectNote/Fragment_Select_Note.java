package com.example.vanluom.quan_ly_tai_chinh_ca_nhan.Fragment.SelectNote;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.vanluom.quan_ly_tai_chinh_ca_nhan.Adapter.ListNoteAdapter;
import com.example.vanluom.quan_ly_tai_chinh_ca_nhan.Database.SQLite;
import com.example.vanluom.quan_ly_tai_chinh_ca_nhan.R;
import com.example.vanluom.quan_ly_tai_chinh_ca_nhan.Model.Chi;

import java.util.ArrayList;

import static com.example.vanluom.quan_ly_tai_chinh_ca_nhan.R.id.radioButtonNamNay;


public class Fragment_Select_Note extends Fragment implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {
    FragmentManager fragmentManager;
    FrameLayout fragmentControlThuChi, fragmentControlThuChiRight;
    Animation animation, animationRightOut, animationLeftIn;
    View view;
    SQLite database;
    ListView listViewNote;
    ListNoteAdapter adapter;
    ArrayList<Chi> listNote;
    Chi.Time TG = Chi.Time.TuanNay;
    Chi.Type type = Chi.Type.TatCa;
    RadioButton rbThu, rbChi, rbThuChi, rbTatCa, rbHomNay, rbTuanNay, rbThangNay, rbNamNay;
    LinearLayout linearLayout;

    public Fragment_Select_Note() { }

    public Fragment_Select_Note(FragmentManager fragmentManager, FrameLayout fl, FrameLayout flRight) {
        fragmentControlThuChi = fl;
        this.fragmentManager = fragmentManager;
        fragmentControlThuChiRight = flRight;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_select_note, container, false);

        Init();
        Events();

        try {
            database = new SQLite(getContext());
            if (rbChi.isChecked()) {
                type = Chi.Type.CHi;
                setList(type, TG);
            } else {
                type = Chi.Type.Thu;
                setList(type, TG);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return view;
    }

    private void Events() {
        rbThu.setOnCheckedChangeListener(this);
        rbChi.setOnCheckedChangeListener(this);
        rbThuChi.setOnCheckedChangeListener(this);
        rbTuanNay.setOnCheckedChangeListener(this);
        rbNamNay.setOnCheckedChangeListener(this);
        rbThangNay.setOnCheckedChangeListener(this);
        rbHomNay.setOnCheckedChangeListener(this);
        rbTatCa.setOnCheckedChangeListener(this);
    }

    private void Init() {
        listViewNote = (ListView) view.findViewById(R.id.listviewNote);
        animationLeftIn = AnimationUtils.loadAnimation(getContext(), R.anim.left_in);
        animation = AnimationUtils.loadAnimation(getContext(), R.anim.right_in);
        animationRightOut = AnimationUtils.loadAnimation(getContext(), R.anim.right_out);
        rbChi = (RadioButton) this.view.findViewById(R.id.radioButtonChi);
        rbThu = (RadioButton) this.view.findViewById(R.id.radioButtonThu);
        rbThuChi = (RadioButton) this.view.findViewById(R.id.radioButtonThuChi);
        rbTuanNay = (RadioButton) this.view.findViewById(R.id.radioButtonTuanNay);
        rbNamNay = (RadioButton) this.view.findViewById(radioButtonNamNay);
        rbThangNay = (RadioButton) this.view.findViewById(R.id.radioButtonThangNay);
        rbHomNay = (RadioButton) this.view.findViewById(R.id.radioButtonHomnay);
        rbTatCa = (RadioButton) this.view.findViewById(R.id.radioButtonTatca);
        linearLayout = (LinearLayout) this.view.findViewById(R.id.linear_layout_item_note_child);
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

        if (rbChi.isChecked()) {
            type = Chi.Type.CHi;
            LoadList(type, TG);
        } else if (rbThu.isChecked()) {
            type = Chi.Type.Thu;
            LoadList(type, TG);
        } else if (rbThuChi.isChecked()) {
            type = Chi.Type.TatCa;
            LoadList(type, TG);
        }

        if (rbHomNay.isChecked()) {
            TG = Chi.Time.HomNay;
            LoadList(type, TG);
        } else if (rbTuanNay.isChecked()) {
            TG = Chi.Time.TuanNay;
            LoadList(type, TG);
        } else if (rbThangNay.isChecked()) {
            TG = Chi.Time.ThangNay;
            LoadList(type, TG);
        } else if (rbNamNay.isChecked()) {
            TG = Chi.Time.NamNay;
            LoadList(type, TG);
        } else if (rbTatCa.isChecked()) {
            TG = Chi.Time.All;
            LoadList(type, TG);
        }

    }

    @TargetApi(Build.VERSION_CODES.N)
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.linear_layout_item_note_child:
                break;
        }
    }

    private void setList(Chi.Type type, Chi.Time time) {
        listNote = new ArrayList<>();
        listNote = database.GetAllChi(type, time);
        adapter = new ListNoteAdapter(getContext(), R.layout.item_note_child, listNote);
        listViewNote.setAdapter(adapter);
    }

    @TargetApi(Build.VERSION_CODES.N)
    private void LoadList(Chi.Type type, Chi.Time time) {
        listNote = new ArrayList<>();
        listNote = database.GetAllChi(type, time);
        adapter = new ListNoteAdapter(getContext(), R.layout.item_note_child, listNote);
        listViewNote.setAdapter(adapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        Toast.makeText(getContext(), "Đã tải lại", Toast.LENGTH_SHORT).show();
        LoadList(type, TG);
    }
}
