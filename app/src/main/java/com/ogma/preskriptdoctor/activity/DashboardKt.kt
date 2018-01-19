package com.ogma.preskriptdoctor.activity

import android.os.Bundle
import android.support.design.widget.CoordinatorLayout
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import com.ogma.preskriptdoctor.R
import com.ogma.preskriptdoctor.fragment.DashboardFragment

/**
 * Created by User on 21-09-2017.
 */
class DashboardKt : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private var coordinatorLayout: CoordinatorLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        assert(supportActionBar != null)
        supportActionBar!!.setDisplayShowTitleEnabled(false)

        coordinatorLayout = findViewById(R.id.coordinator_layout)

        val drawer: DrawerLayout = findViewById(R.id.drawer_layout)
        val toggle = object : ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {
            override fun onDrawerOpened(drawerView: View?) {
                super.onDrawerOpened(drawerView)
                //                NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
                //                TextView tvLocation = (TextView) navigationView.getHeaderView(0).findViewById(R.id.tv_location);
                //                String location = app.getAppSettings().__uStateName + " | " + app.getAppSettings().__uCityName;
                //                tvLocation.setText(location);
            }
        }
        drawer?.addDrawerListener(toggle)
        toggle.syncState()

        val navigationView: NavigationView = findViewById(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this)

        if (savedInstanceState == null) {
            navigationView.setCheckedItem(R.id.nav_dashboard)
            onNavigationItemSelected(navigationView.menu.findItem(R.id.nav_dashboard))
        }
    }

    override fun onBackPressed() {
        val drawer: DrawerLayout = findViewById(R.id.drawer_layout)
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        val id = item.itemId

        if (item.groupId == R.id.nav_group_primary) {
            if (id == R.id.nav_dashboard) {
                supportFragmentManager.beginTransaction().replace(R.id.fragment_container, DashboardFragment()).commit()
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
            assert(supportActionBar != null)
            supportActionBar!!.title = item.title
            val tvToolBarTitle: TextView = findViewById(R.id.tv_toolbar_title)
            tvToolBarTitle.text = item.title

            val drawer: DrawerLayout = findViewById(R.id.drawer_layout)
            drawer.closeDrawer(GravityCompat.START)
            return true
        }
        return true
    }
}