package com.example.zapis_test.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v4.app.Fragment
import android.util.Log
import com.example.zapis_test.R
import com.example.zapis_test.ui.home.HomeFragment
import com.example.zapis_test.ui.profile.ProfileFragment
import com.example.zapis_test.ui.search.SearchFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var listFragment: List<Fragment> = listOf(HomeFragment(), SearchFragment(), ProfileFragment())
    var selectedFragment = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().add(R.id.container, listFragment.get(0), HOME_FRAGMENT)
                .add(listFragment.get(1), SEARCH_FRAGMENT)
                .add(listFragment.get(2), PROFILE_FRAGMENT)
                .show(listFragment.get(0))
                .hide(listFragment.get(1))
                .hide(listFragment.get(2))
                .commit()
            selectedFragment = 0
        }
        bottomNavigationView.setOnNavigationItemSelectedListener {
            val transaction = supportFragmentManager.beginTransaction()
            when(it.itemId) {
                R.id.navigation_home -> {
                    if (selectedFragment != 0) {
                        transaction.hide(listFragment.get(selectedFragment))
                            .show(listFragment.get(0))
                        selectedFragment = 0
                    }
                }
                R.id.navigation_profile -> {
                    if (selectedFragment != 1) {
                        transaction.hide(listFragment.get(selectedFragment))
                            .show(listFragment.get(1))
                        selectedFragment = 1
                    }

                }
                else -> {
                    if (selectedFragment != 2) {
                        transaction.hide(listFragment.get(selectedFragment))
                            .show(listFragment.get(2))
                        selectedFragment = 2
                    }

                }
            }
            transaction.commit()
            true
        }
    }


    companion object TAGS {
        const val HOME_FRAGMENT = "HOME_FRAGMENT"
        const val SEARCH_FRAGMENT = "SEARCH_FRAGMENT"
        const val PROFILE_FRAGMENT = "PROFILE_FRAGMENT"
        const val SELECTED_POSITION = "SELECTED_POSITION"
    }

}
