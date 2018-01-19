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
import com.ogma.preskriptdoctor.fragment.FollowersFragment;
import com.ogma.preskriptdoctor.fragment.FollowingFragment;
import com.ogma.preskriptdoctor.fragment.StoryFeedFragment;
import com.ogma.preskriptdoctor.fragment.StoryNotificationFragment;
import com.ogma.preskriptdoctor.fragment.StoryProfileFragment;

public class SuccessStory extends AppCompatActivity {

    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success_story);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        ViewPagerAdapter pagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        ViewPager pager = (ViewPager) findViewById(R.id.view_pager);
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

        private final String titles[] = {"Home", "Following", "Followers", "Notifications", "Profile"};
        private final int icons[] = {
                R.drawable.selector_tab_home,
                R.drawable.selector_tab_follower,
                R.drawable.selector_tab_following,
                R.drawable.selector_tab_notification,
                R.drawable.selector_tab_profile
        };
        private final Fragment[] fragments = {
                new StoryFeedFragment(),
                new FollowersFragment(),
                new FollowingFragment(),
                new StoryNotificationFragment(),
                new StoryProfileFragment()
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
