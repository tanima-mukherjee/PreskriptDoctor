package com.ogma.preskriptdoctor.activity;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.ogma.preskriptdoctor.R;
import com.ogma.preskriptdoctor.fragment.BannerFragment;
import com.ogma.preskriptdoctor.utility.viewpager.transformer.CustPagerTransformer;

import java.util.ArrayList;

public class ReportCheckDetails extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = ReportCheckDetails.class.getSimpleName();
    private CoordinatorLayout coordinatorLayout;
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerAdapter recyclerAdapter;
    private EditText etFeedback;
    private ArrayList<String> feedbacks = new ArrayList<>();
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_check_details);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinator_layout);

        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);
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

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(this);
        etFeedback = (EditText) findViewById(R.id.et_feedback);

        ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);
        viewPager.setClipToPadding(false);
        viewPager.setPadding(80, 0, 80, 0);
        viewPager.setPageMargin(40);
        viewPager.setPageTransformer(false, new CustPagerTransformer(this));
        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {

            private String[] titles = {
                    "",
                    "Manage Schedule",
                    "Appointments",
                    "Report check and Feedback",
                    "Refer to other doctor",
                    "Get Reference",
                    "Group Discussion",
                    "Treatment Enquiries",
                    "Study with Preskript",
                    "Find Molecule & Various Medicine",
                    "Consultations",
                    "Success Story",
                    "Refer Hospitals",
                    "Refer Diagnostics"
            };

            private int[] drawables = {
                    R.drawable.dashboard_banner,
                    R.drawable.banner_slide_schedule,
                    R.drawable.banner_slide_appointments,
                    R.drawable.banner_slide_report_check,
                    R.drawable.banner_slide_refer,
                    R.drawable.banner_slide_get_reference,
                    R.drawable.banner_slide_group_discussion,
                    R.drawable.banner_slide_treatment_inquiries,
                    R.drawable.banner_slide_study_with_preskript,
                    R.drawable.banner_slide_molicule_medicine,
                    R.drawable.banner_slide_consultations,
                    R.drawable.banner_slide_success_story,
                    R.drawable.banner_slide_refer_hospitals,
                    R.drawable.banner_slide_refer_diagnostics
            };

            @Override
            public Fragment getItem(int position) {
                Bundle bundle = new Bundle();
                bundle.putString(BannerFragment.EXTRA_TITLE_ARG, titles[position]);
                bundle.putInt(BannerFragment.EXTRA_DRAWABLE_ARG, drawables[position]);
                BannerFragment fragment = new BannerFragment();
                fragment.setArguments(bundle);
                return fragment;
            }

            @Override
            public int getCount() {
                return titles.length == drawables.length ? titles.length : 0;
            }
        });

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerAdapter = new RecyclerAdapter();
        recyclerView.setAdapter(recyclerAdapter);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.fab) {
            feedbacks.add(etFeedback.getText().toString());
            recyclerAdapter.notifyDataSetChanged();
            etFeedback.setText("");
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

        @Override
        public RecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_report_feedback, parent, false);

            return new RecyclerAdapter.ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(RecyclerAdapter.ViewHolder holder, int position) {
            holder.tvChat.setText(feedbacks.get(position));
//            try {
//                imageLoader.displayImage(jArr.getJSONObject(position).getString("user_image"),
//                        holder.ivImage,
//                        displayImageOptions);
//                String name = jArr.getJSONObject(position).getString("first_name") + " " + jArr.getJSONObject(position).getString("last_name");
//                holder.tvTitle.setText(name);
//                holder.tvEmail.setText(jArr.getJSONObject(position).getString("email"));
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
        }


        @Override
        public int getItemCount() {
            return feedbacks.size();
//            return jArr.length();
        }

        class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

            //            public LinearLayout itemHolder;
//            public ImageView ivImage;
            private TextView tvChat;
//            public Button btnAction;

            public ViewHolder(View itemView) {
                super(itemView);
//                itemHolder = (LinearLayout) itemView.findViewById(R.id.item_holder);
//                ivImage = (ImageView) itemView.findViewById(R.id.iv_image);
                tvChat = (TextView) itemView.findViewById(R.id.tv_chat);
//                tvEmail = (TextView) itemView.findViewById(R.id.tv_email);
//                btnAction = (Button) itemView.findViewById(R.id.btn_action);
//
//                itemHolder.setOnClickListener(this);
            }

            @Override
            public void onClick(View view) {
//                if (view.getId() == itemHolder.getId()) {
//                    Log.e(TAG, "onClick at position: " + getAdapterPosition());
//                    try {
//                        startActivity(new Intent(getActivity(), Menus.class)
//                                .putExtra(Menus.EXTRA_ID, jArr.getJSONObject(position).getString("id"))
//                                .putExtra(Menus.EXTRA_NAME, jArr.getJSONObject(position).getString("name")));
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//                }
            }
        }
    }
}
