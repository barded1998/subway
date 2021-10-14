package com.example.subway

import android.net.wifi.p2p.nsd.WifiP2pServiceRequest.newInstance
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.subway.R
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

import me.relex.circleindicator.CircleIndicator

import androidx.viewpager.widget.ViewPager




class ResultActivity : AppCompatActivity() {
    var adapterViewPager : FragmentPagerAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        val vpPager = findViewById<View>(R.id.result_viewpager) as ViewPager?
        adapterViewPager = MyPagerAdapter(supportFragmentManager)
        vpPager?.adapter = adapterViewPager
        val indicator = findViewById<View>(R.id.result_indicator) as CircleIndicator?
        indicator?.setViewPager(vpPager)
    }

    class MyPagerAdapter(fragmentManager: FragmentManager) :
        FragmentPagerAdapter(fragmentManager) {
        // Returns total number of pages
        override fun getCount(): Int {
            return NUM_ITEMS
        }

        // Returns the fragment to display for that page
        override fun getItem(position: Int): Fragment {
            return when (position) {
                0 -> ResultByTimeFragment().newInstance(0, "Page # 1")!!
                1 -> ResultByDistanceFragment().newInstance(1, "Page # 2")!!
                2 -> ResultByExpenseFragment().newInstance(2, "Page # 3")!!
                else -> ResultByTimeFragment().newInstance(0, "Page # 1")!!
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