
package com.vanluom.group11.quanlytaichinhcanhan.tutorial;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vanluom.group11.quanlytaichinhcanhan.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TutorialTransactionsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TutorialTransactionsFragment extends Fragment {
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment TutorialTransactionsFragment.
     */
    public static TutorialTransactionsFragment newInstance() {
        TutorialTransactionsFragment fragment = new TutorialTransactionsFragment();
        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public TutorialTransactionsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tutorial_transactions, container, false);
    }


}
