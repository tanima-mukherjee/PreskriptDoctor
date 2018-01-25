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
import com.ogma.preskriptdoctor.fragment.StudyEbookFragment;
import com.ogma.preskriptdoctor.fragment.StudyFeedFragment;
import com.ogma.preskriptdoctor.fragment.StudyFollowingFragment;
import com.ogma.preskriptdoctor.fragment.StudyNotificationFragment;
import com.ogma.preskriptdoctor.fragment.StudyProfileFragment;

public class StudyWithPreskript extends AppCompatActivity {

    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study_with_preskript);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tabLayout = findViewById(R.id.tab_layout);
        StudyWithPreskript.ViewPagerAdapter pagerAdapter = new StudyWithPreskript.ViewPagerAdapter(getSupportFragmentManager());
        ViewPager pager = findViewById(R.id.view_pager);
        pager.setAdapter(pagerAdapter);

        //Notice how The Tab Layout and View Pager object are linked
        tabLayout.setupWithViewPager(pager);
        pagerAdapter.setIcons();
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

        private final String titles[] = {"Home", "Profile", "Following", "Notifications", "Ebook"};
        private final int icons[] = {
                R.drawable.selector_tab_home,
                R.drawable.selector_tab_profile,
                R.drawable.selector_tab_following,
                R.drawable.selector_tab_notification,
                R.drawable.selector_tab_ebook
        };
        private final Fragment[] fragments = {
                new StudyFeedFragment(),
                new StudyProfileFragment(),
                new StudyFollowingFragment(),
                new StudyNotificationFragment(),
                new StudyEbookFragment()


        };

        ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment;
            Bundle bundle = new Bundle();
            return fragments[position];
        }

        @Override
        public int getCount() {
            return titles.length == icons.length ? icons.length : 0;
        }

        void setIcons() {
            for (int i = 0; i < getCount(); i++) {
                TabLayout.Tab tab = tabLayout.getTabAt(i);
                if (tab != null)
                    tab.setIcon(icons[i]);
            }
        }

    }
}
