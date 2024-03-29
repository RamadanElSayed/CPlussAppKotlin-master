package com.example.macpro.myapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.navigation.NavigationView
import androidx.core.view.GravityCompat
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.InterstitialAd
import com.google.android.gms.ads.MobileAds
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.app_bar_home.*

class HomeActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    lateinit var mAdView : AdView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        MobileAds.initialize(this, "ca-app-pub-2505688435160257~1862231497")
        mAdView = findViewById<View>(R.id.adView) as AdView
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)



        setSupportActionBar(toolbar)
        if(savedInstanceState == null){
        supportFragmentManager.beginTransaction()
            .replace(R.id.home_frame,HomeFragment())
            .commitNow()
        }
        window.decorView.layoutDirection = View.LAYOUT_DIRECTION_RTL

        val toggle = ActionBarDrawerToggle(
            this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }



    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.home -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.home_frame,HomeFragment())
                    .commitNow()
            }
            R.id.about -> {
                     supportFragmentManager.beginTransaction()
                    .replace(R.id.home_frame,AboutFragment())
                    .commitNow()
            }
            R.id.team -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.home_frame,TeamFragment())
                    .commitNow()
            }
            R.id.order -> {
            startActivity(Intent(Intent.ACTION_VIEW,
                Uri.parse("mailto:" + "info@ali-gamal.com" + "?subject=" + " رسالة من التطبيق")))
            }
            R.id.nav_share -> {
                val sent = Intent()
                sent.action = Intent.ACTION_SEND
                sent.putExtra(Intent.EXTRA_TEXT , "https://play.google.com/store/apps/details?id=com.example.macpro.myapp حمل تطبيق تعلم السي بلس بلس ")
                sent.type = "text/plain"
                startActivity(Intent.createChooser(sent,"إختار التطبيق الذي تريد المشاركه معه:"))
            }

        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}
