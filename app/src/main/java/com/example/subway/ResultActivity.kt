package com.example.subway

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

import me.relex.circleindicator.CircleIndicator

import androidx.viewpager.widget.ViewPager
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds


class ResultActivity : AppCompatActivity() {
    var adapterViewPager: FragmentPagerAdapter? = null
    var departureStation: String? = null
    var transitStation: String? = null
    var arrivalStation: String? = null
    lateinit var mAdView : AdView
    override fun onCreate(savedInstanceState: Bundle?) {
        // Get data from prev activities
        departureStation = intent.getStringExtra("departureStation");
        transitStation = intent.getStringExtra("transitStation");
        arrivalStation = intent.getStringExtra("arrivalStation");
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        //Set departureStation and arrivalStation on the bar
        findViewById<TextView>(R.id.result_departure_station).setText(departureStation)
        findViewById<TextView>(R.id.result_arrival_station).setText(arrivalStation)

        //Set viewPager and circle indicator with departure, transit and arrival stations
        val vpPager = findViewById<View>(R.id.result_viewpager) as ViewPager?
        adapterViewPager =
            MyPagerAdapter(supportFragmentManager, departureStation, transitStation, arrivalStation)
        vpPager?.adapter = adapterViewPager
        val indicator = findViewById<View>(R.id.result_indicator) as CircleIndicator?
        indicator?.setViewPager(vpPager)

        //Ads
        MobileAds.initialize(this) {}
        mAdView = findViewById<AdView>(R.id.adView)
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)

    }

    class MyPagerAdapter(
        fragmentManager: FragmentManager,
        departureStation: String?,
        transitStation: String?,
        arrivalStation: String?
    ) :
        FragmentPagerAdapter(fragmentManager) {
        //Initialize member variables
        var departureStation = departureStation
        var transitStation = transitStation
        var arrivalStation = arrivalStation
        override fun getCount(): Int {
            return NUM_ITEMS
        }

        // Returns the fragment to display for that page
        override fun getItem(position: Int): Fragment {
            when (position) {
                0 -> Log.d("result", "time")
                1 -> Log.d("result", "expense")
                2 -> Log.d("result", "distance")
                else -> Log.d("time", "time")
            }
            return when (position) {
                0 ->
                    ResultByTimeFragment().newInstance(
                        departureStation,
                        transitStation,
                        arrivalStation
                    )!!
                1 -> ResultByExpenseFragment().newInstance(
                    departureStation,
                    transitStation,
                    arrivalStation
                )!!
                2 -> ResultByDistanceFragment().newInstance(
                    departureStation,
                    transitStation,
                    arrivalStation
                )!!
                else -> ResultByTimeFragment().newInstance(
                    departureStation,
                    transitStation,
                    arrivalStation
                )!!
            }
        }

        companion object {
            private const val NUM_ITEMS = 3
        }
    }


}