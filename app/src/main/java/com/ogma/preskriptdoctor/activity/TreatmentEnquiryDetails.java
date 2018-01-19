package com.ogma.preskriptdoctor.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.ogma.preskriptdoctor.R;
import com.ogma.preskriptdoctor.fragment.BannerFragment;
import com.ogma.preskriptdoctor.utility.viewpager.transformer.CustPagerTransformer;

public class TreatmentEnquiryDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_treatment_enquiry_details);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);
        viewPager.setClipToPadding(false);
        viewPager.setPadding(80, 0, 80, 0);
        viewPager.setPageMargin(80);
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
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
