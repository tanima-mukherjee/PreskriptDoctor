package com.ogma.preskriptdoctor.fragment;


import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ogma.preskriptdoctor.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 */
public class StudyFollowingFragment extends Fragment {

    private static final String TAG = StudyFollowingFragment.class.getSimpleName();
    private RecyclerAdapter recyclerAdapter;
    private CoordinatorLayout coordinatorLayout;
    private SwipeRefreshLayout swipeRefreshLayout;


    public StudyFollowingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_study_following, container, false);

        coordinatorLayout = (CoordinatorLayout) getActivity().findViewById(R.id.coordinator_layout);

        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_layout);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimaryDark, R.color.colorGreenDark, R.color.colorNavyBlueDark);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
//                if (prepareExecuteAsync()) {
//                    new FetchEventsTask().execute(new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(date));
//                }
                swipeRefreshLayout.setRefreshing(false);
            }
        });

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerAdapter = new RecyclerAdapter();
        recyclerView.setAdapter(recyclerAdapter);

        return view;
    }

    private class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_following_list, parent, false);

            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
//            imageLoader.displayImage("drawable://" + R.drawable.hyundai, holder.ivCategory, UniversalImageLoaderFactory.getDefaultOptions(25));
        }


        @Override
        public int getItemCount() {
            return 10;
//            return jArr.length();
        }

        private String parseDate(String dateString) {
            String formattedDate = "";
            try {
                Log.e(TAG, "parseDate: " + dateString);
                SimpleDateFormat inFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
                Date date = inFormat.parse(dateString);
                SimpleDateFormat outFormat = new SimpleDateFormat("MMM dd yyyy, hh:mm a", Locale.getDefault());
                formattedDate = outFormat.format(date);
                Log.e(TAG, "formattedDate: " + formattedDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return formattedDate;
        }

        class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

            public ViewHolder(View itemView) {
                super(itemView);
            }

            @Override
            public void onClick(View view) {
                Log.e(TAG, "onClick at position: " + getAdapterPosition());
            }
        }
    }


}
