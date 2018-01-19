package com.ogma.preskriptdoctor.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.ogma.preskriptdoctor.R;
import com.ogma.preskriptdoctor.fragment.AppointmentListFragment;

public class Appointments extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointments);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        ViewPagerAdapter pagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        ViewPager pager = (ViewPager) findViewById(R.id.view_pager);
        pager.setAdapter(pagerAdapter);

        //Notice how The Tab Layout and View Pager object are linked
        tabLayout.setupWithViewPager(pager);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private class ViewPagerAdapter extends FragmentStatePagerAdapter {

        private final String titles[] = {"DUMDUM", "NEW TOWN", "GARIAHAT", "BEHALA", "METROPOLITAN", "HILAND PARK"};

        ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment;
            Bundle bundle = new Bundle();
            switch (position) {
                case 0:
                    fragment = new AppointmentListFragment();
//                    bundle.putString(EventListFragment.EXTRA_GROUP_TYPE, FREE);
                    fragment.setArguments(bundle);
                    return fragment;
                case 1:
                    fragment = new AppointmentListFragment();
//                    bundle.putString(EventListFragment.EXTRA_GROUP_TYPE, BUSINESS);
                    fragment.setArguments(bundle);
                    return fragment;
                default:
                    return new AppointmentListFragment();
            }
        }

        @Override
        public int getCount() {
            return titles.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }
    }
}
