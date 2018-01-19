package com.ogma.preskriptdoctor.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ogma.preskriptdoctor.R;
import com.ogma.preskriptdoctor.activity.Appointments;
import com.ogma.preskriptdoctor.activity.GetReference;
import com.ogma.preskriptdoctor.activity.GroupDiscussion;
import com.ogma.preskriptdoctor.activity.ReferToDiagnostic;
import com.ogma.preskriptdoctor.activity.ReferToDoctor;
import com.ogma.preskriptdoctor.activity.ReferToHospital;
import com.ogma.preskriptdoctor.activity.ReportCheck;
import com.ogma.preskriptdoctor.activity.SuccessStory;
import com.ogma.preskriptdoctor.activity.TreatmentEnquiry;

import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;

/**
 * A simple {@link Fragment} subclass.
 */
public class DashboardFragment extends Fragment {

    private final long DELAY_MS = 500;//delay in milliseconds before task is to be executed
    private final long PERIOD_MS = 3000; // time in milliseconds between successive task executions.
    private int currentPage = 0;
    private Timer timer;
    private RecyclerAdapter recyclerAdapter;

    public DashboardFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);

        final ViewPager viewPager = (ViewPager) view.findViewById(R.id.view_pager);
        viewPager.setAdapter(new FragmentPagerAdapter(getFragmentManager()) {

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
        viewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                currentPage = position;
            }
        });
        setAutoSlide(viewPager);

        CircleIndicator indicator = (CircleIndicator) view.findViewById(R.id.indicator);
        indicator.setViewPager(viewPager);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(gridLayoutManager);

        recyclerAdapter = new RecyclerAdapter();
        recyclerView.setAdapter(recyclerAdapter);

        return view;
    }

    @SuppressWarnings("Must be called after setting the viewpager adapter")
    private void setAutoSlide(final ViewPager viewPager) {
        /*After setting the adapter use the timer */
        final Handler handler = new Handler();
        final Runnable update = new Runnable() {
            public void run() {
                if (currentPage == viewPager.getAdapter().getCount()) {
                    currentPage = 0;
                }
                viewPager.setCurrentItem(currentPage++, true);
            }
        };

        timer = new Timer(); // This will create a new Thread
        timer.schedule(new TimerTask() { // task to be scheduled
            @Override
            public void run() {
                handler.post(update);
            }
        }, DELAY_MS, PERIOD_MS);
    }

    @Override
    public void onDestroyView() {
        timer.cancel();
        super.onDestroyView();
    }

    private class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

        private final String TAG = RecyclerAdapter.class.getSimpleName();

        private String[] titles = {
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
                R.drawable.ic_manage_schedule,
                R.drawable.ic_appointments,
                R.drawable.ic_report_check,
                R.drawable.ic_refer,
                R.drawable.ic_get_reference,
                R.drawable.ic_group_discussion,
                R.drawable.ic_treatment_enquiries,
                R.drawable.ic_study_with_preskript,
                R.drawable.ic_molecule,
                R.drawable.ic_consultations,
                R.drawable.ic_success_story,
                R.drawable.ic_refer_hospitals,
                R.drawable.ic_refer_diagnostics
        };

        private int[] drawable_bg_colors = {
                R.color.colorNavyBlueDark,
                R.color.colorRedLight,
                R.color.colorGreenDark,
                R.color.colorPurpleDark,
                R.color.colorSkyLight,
                R.color.colorPinkishGrey,
                R.color.colorPinkDark,
                R.color.colorGreenLight,
                R.color.colorBrownLight,
                R.color.colorBlueDark,
                R.color.colorBrownDark,
                R.color.colorPinkDarkVery,
                R.color.colorPurpleLight
        };

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dashboard, parent, false);

            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.ivIcon.setImageResource(drawables[position]);
            holder.tvTitle.setText(titles[position]);
            DrawableCompat.setTint(holder.ivIconBg.getDrawable(), ContextCompat.getColor(getActivity(), drawable_bg_colors[position]));
        }

        @Override
        public int getItemCount() {
            return titles.length == drawables.length ? titles.length : 0;
        }

        class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

            private LinearLayout itemHolder;
            private TextView tvTitle;
            private ImageView ivIcon;
            private ImageView ivIconBg;

            ViewHolder(View itemView) {
                super(itemView);
                itemHolder = (LinearLayout) itemView.findViewById(R.id.item_view);
                tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
                ivIcon = (ImageView) itemView.findViewById(R.id.iv_icon);
                ivIconBg = (ImageView) itemView.findViewById(R.id.iv_icon_bg);
//
                itemHolder.setOnClickListener(this);
            }

            @SuppressWarnings("Hardcoded position check")
            @Override
            public void onClick(View view) {
                if (view.getId() == itemHolder.getId()) {
                    Log.e(TAG, "onClick at position: " + getAdapterPosition());

                    if (getAdapterPosition() == 0) {
//                        startActivity(new Intent(getActivity(), Appointments.class));
                    } else if (getAdapterPosition() == 1) {
                        startActivity(new Intent(getActivity(), Appointments.class));
                    } else if (getAdapterPosition() == 2) {
                        startActivity(new Intent(getActivity(), ReportCheck.class));
                    } else if (getAdapterPosition() == 3) {
                        startActivity(new Intent(getActivity(), ReferToDoctor.class));
                    } else if (getAdapterPosition() == 4) {
                        startActivity(new Intent(getActivity(), GetReference.class));
                    } else if (getAdapterPosition() == 5) {
                        startActivity(new Intent(getActivity(), GroupDiscussion.class));
                    } else if (getAdapterPosition() == 6) {
                        startActivity(new Intent(getActivity(), TreatmentEnquiry.class));
                    }
// else if (getAdapterPosition() == 7) {
//                        startActivity(new Intent(getActivity(), DoctorSuggestion.class));
//                    } else if (getAdapterPosition() == 8) {
//                        startActivity(new Intent(getActivity(), AmbulanceSearch.class));
//                    } else if (getAdapterPosition() == 9) {
//                        startActivity(new Intent(getActivity(), MedicalInsurance.class));
//                    }
                    else if (getAdapterPosition() == 10) {
                        startActivity(new Intent(getActivity(), SuccessStory.class));
                    } else if (getAdapterPosition() == 11) {
                        startActivity(new Intent(getActivity(), ReferToHospital.class));
                    } else if (getAdapterPosition() == 12) {
                        startActivity(new Intent(getActivity(), ReferToDiagnostic.class));
                    }
// else if (getAdapterPosition() == 13) {
//                        startActivity(new Intent(getActivity(), ZeroBalanceHospitalization.class));
//                    } else if (getAdapterPosition() == 14) {
//                        startActivity(new Intent(getActivity(), TreatmentConsultancy.class));
//                    } else if (getAdapterPosition() == 15) {
//                        startActivity(new Intent(getActivity(), DropMedicine.class));
//                    }
//                    try {
//                        startActivity(new Intent(getActivity(), CategoryDetails.class)
//                                .putExtra(CategoryDetails.EXTRA_ID, jArr.getJSONObject(getAdapterPosition()).getString("id")));
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
                }
            }
        }
    }
}
