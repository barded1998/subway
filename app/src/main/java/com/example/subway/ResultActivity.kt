package com.example.subway

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

import me.relex.circleindicator.CircleIndicator

import androidx.viewpager.widget.ViewPager




class ResultActivity : AppCompatActivity() {
    var adapterViewPager : FragmentPagerAdapter? = null
    var departureStation : String? = null
    var transitStation : String? = null
    var arrivalStation : String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        departureStation = intent.getStringExtra("departureStation");
        transitStation = intent.getStringExtra("transitStation");
        arrivalStation = intent.getStringExtra("arrivalStation");
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        findViewById<TextView>(R.id.result_departure_station).setText(departureStation)
//        findViewById<TextView>(R.id.result_transit_station).setText(transitStation)
        findViewById<TextView>(R.id.result_arrival_station).setText(arrivalStation)

        val vpPager = findViewById<View>(R.id.result_viewpager) as ViewPager?
        adapterViewPager = MyPagerAdapter(supportFragmentManager, departureStation, transitStation, arrivalStation)
        vpPager?.adapter = adapterViewPager
        val indicator = findViewById<View>(R.id.result_indicator) as CircleIndicator?
        indicator?.setViewPager(vpPager)
    }

    class MyPagerAdapter(fragmentManager: FragmentManager, departureStation: String?, transitStation: String? ,arrivalStation: String?) :
        FragmentPagerAdapter(fragmentManager) {
        // Returns total number of pages
        var departureStation = departureStation
        var transitStation = transitStation
        var arrivalStation = arrivalStation


        override fun getCount(): Int {
            return NUM_ITEMS
        }

        // Returns the fragment to display for that page
        override fun getItem(position: Int): Fragment {
            return when (position) {
                0 -> ResultByTimeFragment().newInstance(departureStation, transitStation,arrivalStation)!!
                1 -> ResultByExpenseFragment().newInstance(departureStation, transitStation,arrivalStation)!!
                2 -> ResultByDistanceFragment().newInstance(departureStation, transitStation,arrivalStation)!!
                else -> ResultByTimeFragment().newInstance(departureStation, transitStation,arrivalStation)!!
            }
        }

        // Returns the page title for the top indicator
        override fun getPageTitle(position: Int): CharSequence? {
            return "Page $position"
        }

        companion object {
            private const val NUM_ITEMS = 3
        }
    }


}