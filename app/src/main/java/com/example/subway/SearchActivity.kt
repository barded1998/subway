package com.example.subway

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button

class SearchActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        var searchBtn = findViewById<Button>(R.id.search_search_btn)
        searchBtn.setOnClickListener{
            val intent = Intent(this, ResultActivity::class.java)
            startActivity(intent)
        }
    }

}