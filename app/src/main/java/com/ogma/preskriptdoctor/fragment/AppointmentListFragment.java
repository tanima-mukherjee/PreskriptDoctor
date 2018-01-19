package com.ogma.preskriptdoctor.fragment;


import android.app.DatePickerDialog;
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
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TextView;

import com.ogma.preskriptdoctor.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 */
public class AppointmentListFragment extends Fragment implements View.OnClickListener {

    private static final String TAG = AppointmentListFragment.class.getSimpleName();
    private RecyclerAdapter recyclerAdapter;
    private CoordinatorLayout coordinatorLayout;
    private SwipeRefreshLayout swipeRefreshLayout;
    private TextView tvTimeStamp;

    public AppointmentListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_appointment_list, container, false);

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

        tvTimeStamp = (TextView) view.findViewById(R.id.tv_time_stamp);
        tvTimeStamp.setOnClickListener(this);

        Calendar c = Calendar.getInstance();
        Date date = c.getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("MMMM dd yyyy", Locale.getDefault());
        tvTimeStamp.setText(formatter.format(date));

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerAdapter = new RecyclerAdapter();
        recyclerView.setAdapter(recyclerAdapter);

        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_indicator:
            case R.id.tv_time_stamp:
                showDatePicker();
                break;
            default:
                break;
        }
    }

    private void showDatePicker() {
        Calendar calendar = Calendar.getInstance();
        int cYear = calendar.get(Calendar.YEAR);
        int cMonth = calendar.get(Calendar.MONTH);
        int cDay = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Log.e(TAG, "year:" + year + " month:" + monthOfYear + " day:" + dayOfMonth);
                Calendar c = Calendar.getInstance();
                // set the calendar to start of today
                c.set(Calendar.YEAR, year);
                c.set(Calendar.MONTH, monthOfYear);
                c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                Date date = c.getTime();
                SimpleDateFormat formatter = new SimpleDateFormat("MMMM dd yyyy", Locale.getDefault());
                tvTimeStamp.setText(formatter.format(date));
            }
        }, cYear, cMonth, cDay);
        datePickerDialog.getDatePicker().setMinDate(new Date().getTime() - 1000);
        datePickerDialog.show();
    }

    private class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_appointment_list, parent, false);

            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
//            imageLoader.displayImage("drawable://" + R.drawable.hyundai, holder.ivCategory, UniversalImageLoaderFactory.getDefaultOptions(25));
//            try {
//                holder.tvTitle.setText(jArr.getJSONObject(position).getString("event_name"));
//                holder.tvGroupName.setText(jArr.getJSONObject(position).getString("group_name"));
//                holder.tvDesc.setText(jArr.getJSONObject(position).getString("desc"));
//                holder.tvLocation.setText(jArr.getJSONObject(position).getString("location"));
//                String dealAmount = "$" + jArr.getJSONObject(position).getString("deal_amount") + " Deal Amount";
//                holder.tvDealAmount.setText(dealAmount);
//                boolean isOwner = jArr.getJSONObject(position).getString("group_user_type").equals(Constant.Group.GroupUserType.OWNER);
//                holder.ibEdit.setVisibility(isOwner ? View.VISIBLE : View.GONE);
//                int isMultipleDate = jArr.getJSONObject(position).getInt("is_multiple_date");
//                String tag = isMultipleDate == 1 ? "MULTIPLE" : "SINGLE";
//                holder.tvTag.setText(tag);
//                String timeStamp = "";
//                switch (isMultipleDate) {
//                    case 0:
//                        timeStamp = parseDate(jArr.getJSONObject(position).getString("event_date_time"));
//                        holder.tvTimeStamp.setText(timeStamp);
//                        break;
//                    case 1:
//                        timeStamp = parseDate(jArr.getJSONObject(position).getString("event_start_date_time")) + " - " +
//                                parseDate(jArr.getJSONObject(position).getString("event_end_date_time"));
//                        holder.tvTimeStamp.setText(timeStamp);
//                        break;
//                    default:
//                        break;
//                }
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
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

            private TextView tvTitle, tvGroupName, tvTimeStamp, tvDesc, tvLocation, tvDealAmount, tvTag, tvViewOnMap;
            private ImageButton ibEdit;

            public ViewHolder(View itemView) {
                super(itemView);
//                tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
//                tvGroupName = (TextView) itemView.findViewById(R.id.tv_group_name);
//                tvTimeStamp = (TextView) itemView.findViewById(R.id.tv_time_stamp);
//                tvDesc = (TextView) itemView.findViewById(R.id.tv_desc);
//                tvLocation = (TextView) itemView.findViewById(R.id.tv_location);
//                tvDealAmount = (TextView) itemView.findViewById(R.id.tv_deal_amount);
//                tvTag = (TextView) itemView.findViewById(R.id.tv_tag);
//                tvViewOnMap = (TextView) itemView.findViewById(R.id.tv_view_on_map);
//                tvViewOnMap.setOnClickListener(this);
//
//                Drawable groupNameDrawable = AppCompatResources.getDrawable(getActivity(), R.drawable.ic_menu_people_white);
//                tvGroupName.setCompoundDrawablesWithIntrinsicBounds(groupNameDrawable, null, null, null);
//
//                Drawable timeStampDrawable = AppCompatResources.getDrawable(getActivity(), R.drawable.ic_time_white);
//                tvTimeStamp.setCompoundDrawablesWithIntrinsicBounds(timeStampDrawable, null, null, null);
//
//                Drawable locationDrawable = AppCompatResources.getDrawable(getActivity(), R.drawable.ic_map_white);
//                tvLocation.setCompoundDrawablesWithIntrinsicBounds(locationDrawable, null, null, null);
//
//                ibEdit = (ImageButton) itemView.findViewById(R.id.ib_edit);
//                ibEdit.setOnClickListener(this);
            }

            @Override
            public void onClick(View view) {
                Log.e(TAG, "onClick at position: " + getAdapterPosition());
//                if (view.getId() == ibEdit.getId()) {
//                    try {
//                        startActivityForResult(new Intent(getActivity(), CreateEventActivity.class)
//                                        .putExtra(CreateEventActivity.EXTRA_ID, jArr.getJSONObject(getAdapterPosition()).getString("group_id"))
//                                        .putExtra(CreateEventActivity.EXTRA_TYPE, jArr.getJSONObject(getAdapterPosition()).getString("group_type"))
//                                        .putExtra(CreateEventActivity.EXTRA_EVENT_UPDATE, true)
//                                        .putExtra(CreateEventActivity.EXTRA_EVENT_DATA, jArr.getJSONObject(getAdapterPosition()).toString()),
//                                REQUEST_CODE_EDIT_EVENT);
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//                } else if (view.getId() == tvViewOnMap.getId()) {
//                    try {
//                        String latitude = jArr.getJSONObject(getAdapterPosition()).getString("latitude");
//                        String longitude = jArr.getJSONObject(getAdapterPosition()).getString("longitude");
//                        String location = jArr.getJSONObject(getAdapterPosition()).getString("location");
//                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:<" + latitude + ">,<" + longitude + ">?q=<" + latitude + ">,<" + longitude + ">(" + location + ")"));
//                        if (intent.resolveActivity(getActivity().getPackageManager()) != null)
//                            startActivity(intent);
//                        else
//                            Snackbar.make(coordinatorLayout, "Google Maps not installed", Snackbar.LENGTH_SHORT).show();
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//                }
            }
        }
    }

}
