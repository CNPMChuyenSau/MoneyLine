package com.vanluom.group11.quanlytaichinhcanhan.about;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.vanluom.group11.quanlytaichinhcanhan.R;
import com.vanluom.group11.quanlytaichinhcanhan.core.Core;
import com.vanluom.group11.quanlytaichinhcanhan.utils.NetworkUtils;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link WebChangelogFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WebChangelogFragment
    extends Fragment {

    public WebChangelogFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment WebChangelogFragment.
     */
    public static WebChangelogFragment newInstance() {
        WebChangelogFragment fragment = new WebChangelogFragment();
        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
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
        View view = inflater.inflate(R.layout.fragment_web_changelog, container, false);

        loadChangelog(view);

        return view;
    }

    private void loadChangelog(View view) {
        // check if there is network access
        NetworkUtils utils = new NetworkUtils(getActivity());
        if (!utils.isOnline()) {
            new Core(getActivity()).alert(R.string.no_network);
            return;
        }

        // Set up the URL.

//        String url = "https://github.com/moneymanagerex/android-money-manager-ex/issues?q=milestone%3A";
//        Core core = new Core(getActivity());
//        String version = core.getFullAppVersion(); // "2016.01.21.763";
//        url += version;

        // Show all the versions
        String url = "https://github.com/moneymanagerex/android-money-manager-ex/milestones?direction=desc&sort=due_date&state=closed";
        
        WebView webView = (WebView) view.findViewById(R.id.webView);
        if (webView != null) {
            webView.loadUrl(url);
        }
    }
}
