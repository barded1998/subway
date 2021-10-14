package com.example.subway

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.widget.*
import androidx.fragment.app.Fragment
import com.example.subway.databinding.ActivityMainBinding
import com.google.firebase.firestore.FirebaseFirestore
import java.util.ArrayList
import kotlin.reflect.typeOf

class MainActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.station101.setOnClickListener(this)
        binding.station102.setOnClickListener(this)
        binding.mainSearchBtn.setOnClickListener {
            val intent = Intent(this, SearchActivity::class.java)
            startActivity(intent)
        }
    }


    override fun onClick(v: View?) {
        var station: String? = null
        when (v?.id) {
            binding.station101.id -> station = "101"
            binding.station102.id -> station = "102"
        }
        var fragment = StationInfoFragment()
        var bundle = Bundle()
        bundle.putString("station", station)
        fragment.arguments = bundle
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_station_info, fragment)
            .addToBackStack(null)
            .commit()
    }


}