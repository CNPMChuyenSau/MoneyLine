package com.vanluom.group11.quanlytaichinhcanhan.tutorial;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vanluom.group11.quanlytaichinhcanhan.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TutorialAccountsFragment
    extends Fragment {

    public TutorialAccountsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tutorial_accounts, container, false);

        // customize the icon
//        FontIconDrawable drawable = FontIconDrawable.inflate(getActivity(), R.xml.ic_tutorial_accounts);
//        ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
//        imageView.setImageDrawable(drawable);
        // this inflates pixelated image

        return view;
    }


}
