package com.ogma.preskriptdoctor.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.ogma.preskriptdoctor.R;
import com.ogma.preskriptdoctor.fragment.DashboardFragment;

public class Dashboard extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = Dashboard.class.getSimpleName();
    private static final int REQUEST_CODE_DRAW_OVER_OTHER_APP_PERMISSION = 191;
    private CoordinatorLayout coordinatorLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinator_layout);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
//                NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
//                TextView tvLocation = (TextView) navigationView.getHeaderView(0).findViewById(R.id.tv_location);
//                String location = app.getAppSettings().__uStateName + " | " + app.getAppSettings().__uCityName;
//                tvLocation.setText(location);
            }
        };
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        if (savedInstanceState == null) {
            navigationView.setCheckedItem(R.id.nav_dashboard);
            onNavigationItemSelected(navigationView.getMenu().findItem(R.id.nav_dashboard));
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        final int id = item.getItemId();

        if (item.getGroupId() == R.id.nav_group_primary) {
            if (id == R.id.nav_dashboard) {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new DashboardFragment()).commit();
            }
//            else if (id == R.id.nav_doctor_booking) {
//                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new DoctorBookingsFragment()).commit();
//            } else if (id == R.id.nav_diagnosis_booking) {
//                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new DiagnosisBookingsFragment()).commit();
//            } else if (id == R.id.nav_equipment_booking) {
//                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new MedicineEquipmentBookingsFragment()).commit();
//            } else if (id == R.id.nav_blood_booking) {
//                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new BloodBookingsFragment()).commit();
//            } else if (id == R.id.nav_second_opinion_booking) {
//                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new SecondOpinionBookingsFragment()).commit();
//            } else if (id == R.id.nav_doctor_suggestion_booking) {
//                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new DoctorSuggestionBookingsFragment()).commit();
//            } else if (id == R.id.nav_ambulance_booking) {
//                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new AmbulanceBookingsFragment()).commit();
//            } else if (id == R.id.nav_medical_insurance_booking) {
//                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new MedicalInsuranceBookingsFragment()).commit();
//            } else if (id == R.id.nav_old_age_support_booking) {
//                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new OldAgeSupportBookingsFragment()).commit();
//            } else if (id == R.id.nav_preskript_support_booking) {
//                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new PreskriptSupportBookingsFragment()).commit();
//            } else if (id == R.id.nav_emergency_service_booking) {
//                // TODO: show floating button
//                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new BlankFragment()).commit();
//            } else if (id == R.id.nav_zero_balance_hospitalization_booking) {
//                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ZeroBalanceHosBookingsFragment()).commit();
//            } else if (id == R.id.nav_treatment_consultancy_booking) {
//                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new TreatmentConsultancyBookingsFragment()).commit();
//            } else {
//                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new BlankFragment()).commit();
//            }
//            else if (id == R.id.nav_browse_groups) {
//                getSupportFragmentManager().beginTransaction().replace(R.id.content_main, new CalendarFragment()).commit();
//            }
//            else if (id == R.id.nav_make_a_friend) {
//                InviteByEmailFragment inviteByEmailFragment = new InviteByEmailFragment();
//                inviteByEmailFragment.setIsFriendInvitation(true);
//                getSupportFragmentManager().beginTransaction().replace(R.id.content_main, inviteByEmailFragment).commit();
//            }
            assert getSupportActionBar() != null;
            getSupportActionBar().setTitle(item.getTitle());
            TextView tvToolBarTitle = (TextView) findViewById(R.id.tv_toolbar_title);
            tvToolBarTitle.setText(item.getTitle());

            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);
            return true;
        }
//

//
//        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        drawer.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
//            @Override
//            public void onDrawerClosed(View drawerView) {
//                super.onDrawerClosed(drawerView);
//                if (id == R.id.nav_make_a_friend) {
//                    startActivity(new Intent(MainActivity.this, InviteByEmailActivity.class));
//                } else if (id == R.id.nav_community_calender) {
//                    startActivity(new Intent(MainActivity.this, CommunityCalendarActivity.class));
////                    getSupportFragmentManager().beginTransaction().replace(R.id.content_main, new CommunityCalenderFragment()).commit();
//                } else if (id == R.id.nav_business_deals) {
//                    startActivity(new Intent(MainActivity.this, BusinessDealActivity.class));
////                    getSupportFragmentManager().beginTransaction().replace(R.id.content_main, new BusinessDealFragment()).commit();
//                } else if (id == R.id.nav_profile) {
//                    startActivity(new Intent(MainActivity.this, UpdateProfileActivity.class));
//                } else if (id == R.id.nav_friends) {
//                    startActivity(new Intent(MainActivity.this, FriendsActivity.class));
//                } else if (id == R.id.nav_groups) {
//                    startActivity(new Intent(MainActivity.this, GroupsActivity.class));
//                } else if (id == R.id.nav_events) {
//                    startActivity(new Intent(MainActivity.this, EventsActivity.class));
//                } else if (id == R.id.nav_messages) {
//                    startActivity(new Intent(MainActivity.this, MessageActivity.class));
//                } else if (id == R.id.nav_testimonials) {
//                    startActivity(new Intent(MainActivity.this, TestimonialsActivity.class));
//                } else if (id == R.id.nav_team) {
////                    startActivity(new Intent(Home.this, AddCategory.class));
//                } else if (id == R.id.nav_contact) {
//                    startActivity(new Intent(MainActivity.this, ContactUsActivity.class));
//                } else if (id == R.id.nav_about) {
//                    startActivity(new Intent(MainActivity.this, GrouperIntroActivity.class));
//                } else if (id == R.id.nav_logout) {
//                    app.getAppSettings().revokeSession();
//                    startActivity(new Intent(MainActivity.this, LoginActivity.class)
//                            .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK));
//                }
//                drawer.removeDrawerListener(this);
//            }
//        });
//        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
