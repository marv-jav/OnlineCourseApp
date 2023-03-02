package com.tmmarv.onlinecourseapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.tmmarv.onlinecourseapp.fragments.CourseFragment
import com.tmmarv.onlinecourseapp.fragments.ProfileFragment
import com.tmmarv.onlinecourseapp.fragments.SettingsFragment

class HomeActivity : AppCompatActivity() {

    private lateinit var bottomNav: BottomNavigationView
    private lateinit var frameLayout: FrameLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        bottomNav = findViewById(R.id.bottom_nav)
        frameLayout = findViewById(R.id.fragment_container)

        bottomNav.setOnItemSelectedListener {
            var selectedFragment: Fragment? = null

            when(it.itemId) {
                R.id.profile -> selectedFragment = ProfileFragment()
                R.id.course -> selectedFragment = CourseFragment()
                R.id.settings -> selectedFragment = SettingsFragment()
            }
            if (selectedFragment != null) {
                supportFragmentManager.beginTransaction().replace(R.id.fragment_container, selectedFragment).commit()
            }
            return@setOnItemSelectedListener true
        }
        bottomNav.selectedItemId = R.id.profile
    }
}