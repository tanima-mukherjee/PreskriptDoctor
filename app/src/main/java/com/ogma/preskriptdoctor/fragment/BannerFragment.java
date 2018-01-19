package com.ogma.preskriptdoctor.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ogma.preskriptdoctor.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class BannerFragment extends Fragment {

    public static final String EXTRA_TITLE_ARG = "title_arg";
    public static final String EXTRA_DRAWABLE_ARG = "drawable_arg";

    public BannerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_banner, container, false);

        String title = getArguments() != null ? getArguments().getString(EXTRA_TITLE_ARG) : "";
        int drawable = getArguments() != null ? getArguments().getInt(EXTRA_DRAWABLE_ARG) : R.drawable.dashboard_banner;

        ImageView ivCover = (ImageView) view.findViewById(R.id.iv_cover);
        ivCover.setImageResource(drawable);

        TextView tvTitle = (TextView) view.findViewById(R.id.tv_title);
        tvTitle.setText(title);

        return view;
    }
}
