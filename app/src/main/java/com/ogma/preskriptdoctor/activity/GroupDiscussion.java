package com.ogma.preskriptdoctor.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ogma.preskriptdoctor.R;

public class GroupDiscussion extends AppCompatActivity {

    private String TAG = GroupDiscussion.class.getSimpleName();
    private RecyclerAdapter recyclerAdapter;
    private RecyclerAdapterHorizontal adapterHorizontal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_discussion);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        RecyclerView rvh = (RecyclerView) findViewById(R.id.recycler_view_horizontal);
        rvh.setHasFixedSize(true);
        adapterHorizontal = new RecyclerAdapterHorizontal();
        rvh.setAdapter(adapterHorizontal);

        SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(rvh);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerAdapter = new RecyclerAdapter();
        recyclerView.setAdapter(recyclerAdapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private class RecyclerAdapterHorizontal extends RecyclerView.Adapter<RecyclerAdapterHorizontal.ViewHolder> {

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_group_request, parent, false);

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

        class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

            private LinearLayout itemView;
            private TextView tvTitle, tvGroupName, tvTimeStamp, tvDesc, tvLocation, tvDealAmount, tvTag, tvViewOnMap;
            private ImageButton ibEdit;

            public ViewHolder(View itemView) {
                super(itemView);
                this.itemView = (LinearLayout) itemView.findViewById(R.id.item_view);
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

                this.itemView.setOnClickListener(this);
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

    private class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_group_discussion, parent, false);

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

        class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

            private LinearLayout itemView;
            private TextView tvTitle, tvGroupName, tvTimeStamp, tvDesc, tvLocation, tvDealAmount, tvTag, tvViewOnMap;
            private ImageButton ibEdit;

            public ViewHolder(View itemView) {
                super(itemView);
                this.itemView = (LinearLayout) itemView.findViewById(R.id.item_view);
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
                this.itemView.setOnClickListener(this);
            }

            @Override
            public void onClick(View view) {
                Log.e(TAG, "onClick at position: " + getAdapterPosition());
                startActivity(new Intent(GroupDiscussion.this, GroupDiscussionDetail.class));
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
