package com.example.subway

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

import me.relex.circleindicator.CircleIndicator

import androidx.viewpager.widget.ViewPager
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import kotlin.math.exp


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
        vpPager?.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {

            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }
            override fun onPageSelected(position: Int) {
                if(position == 1)
                    ((vpPager.adapter as MyPagerAdapter).getItem(1) as ResultByExpenseFragment).onCreateView(layoutInflater,vpPager,savedInstanceState)
                (vpPager.adapter as MyPagerAdapter).setActivity(position)
            }

        })
        Log.d("onCreate", "onCreate")
        val indicator = findViewById<View>(R.id.result_indicator) as CircleIndicator?
        indicator?.setViewPager(vpPager)

        //Ads
        MobileAds.initialize(this) {}
        mAdView = findViewById<AdView>(R.id.adView)
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)

        //Map Btn
        var mapBtn = findViewById<Button>(R.id.result_map_icon)
        mapBtn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }


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
        var initializeNumber = 0
        private val time = ResultByTimeFragment().newInstance(departureStation,transitStation,arrivalStation)
        private val expense = ResultByExpenseFragment().newInstance(departureStation,transitStation,arrivalStation)
        private val distance = ResultByDistanceFragment().newInstance(departureStation,transitStation,arrivalStation)
        override fun getCount(): Int {
            return NUM_ITEMS
        }

        fun setActivity(position: Int) {
            when (position) {
                0 -> time!!.setActivityBarImages()
                1 -> expense!!.setActivityBarImages()
                2 -> distance!!.setActivityBarImages()
                else -> time!!.setActivityBarImages()
            }
        }

        // Returns the fragment to display for that page
        override fun getItem(position: Int): Fragment {
            return when (position) {
                0 -> time!!
                1 -> expense!!
                2 -> distance!!
                else -> time!!
            }
//            return when (position) {
//                0 ->
//                    ResultByTimeFragment().newInstance(
//                        departureStation,
//                        transitStation,
//                        arrivalStation
//                    )!!
//                1 -> ResultByExpenseFragment().newInstance(
//                    departureStation,
//                    transitStation,
//                    arrivalStation
//                )!!
//                2 -> ResultByDistanceFragment().newInstance(
//                    departureStation,
//                    transitStation,
//                    arrivalStation
//                )!!
//                else -> ResultByTimeFragment().newInstance(
//                    departureStation,
//                    transitStation,
//                    arrivalStation
//                )!!
//            }
        }

        companion object {
            private const val NUM_ITEMS = 3
        }
    }


}