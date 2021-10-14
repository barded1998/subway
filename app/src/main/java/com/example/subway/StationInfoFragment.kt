package com.example.subway

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.bumptech.glide.Glide
import com.example.subway.R
import com.google.firebase.firestore.FirebaseFirestore
import com.example.subway.model.ContentDTO
import android.widget.ArrayAdapter
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import java.util.ArrayList


class StationInfoFragment : Fragment() {
    private lateinit var fragmentView: View
    private lateinit var firestore: FirebaseFirestore
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentView =
            LayoutInflater.from(activity).inflate(R.layout.fragment_station_info, container, false)
        fragmentView?.findViewById<Button>(R.id.info_prev_station)?.setOnClickListener {
            setFragment(R.id.info_prev_station)
        }
        fragmentView?.findViewById<Button>(R.id.info_next_station)?.setOnClickListener {
            setFragment(R.id.info_next_station)
        }
        var station: String? = null
        var prevStation: String? = null
        var nextStation: String? = null
        var line: ArrayList<String>? = null
        var facilities: ArrayList<String>? = null
        var landmarks: ArrayList<String>? = null
        station = arguments?.getString("station")
        Log.d("stion", station!!)
        firestore = FirebaseFirestore.getInstance()
        firestore.collection("stations")
            .whereEqualTo("station", station)
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    line = document.data.get("line") as ArrayList<String>?
                    facilities = document.data.get("facilities") as ArrayList<String>?
                    fragmentView?.findViewById<TextView>(R.id.info_station).setText(station)
                    if (document.data.get("prevStation") as String? == "0") {
                        fragmentView?.findViewById<Button>(R.id.info_prev_station).setText("없음")
                    } else {
                        fragmentView?.findViewById<Button>(R.id.info_prev_station)
                            .setText(document.data.get("prevStation") as String?)
                    }
                    if (document.data.get("nextStation") as String? == "0") {
                        fragmentView?.findViewById<Button>(R.id.info_next_station).setText("없음")
                    } else {
                        fragmentView?.findViewById<Button>(R.id.info_next_station)
                            .setText(document.data.get("nextStation") as String?)
                    }
                    fragmentView?.findViewById<Button>(R.id.info_main_station).setText(station)
                    var listItem = (document.data.get("landmarks") as ArrayList<String>?)?.toArray()
                    val adapter = ArrayAdapter(
                        requireActivity(), android.R.layout.simple_list_item_1,
                        listItem as Array<out Any>
                    )
                    val listview = fragmentView.findViewById(R.id.info_landmarks) as ListView
                    listview.setAdapter(adapter)
                }
            }
            .addOnFailureListener { exception ->
                Log.w("failure", "Error getting documents: ", exception)
            }
        return fragmentView
    }

    fun setFragment(id: Int) {
        var station = fragmentView?.findViewById<Button>(id)?.text.toString()
        arguments?.putString(
            "station",
            station
        )
        var prevStation: String? = null
        var nextStation: String? = null
        var line: ArrayList<String>? = null
        var facilities: ArrayList<String>? = null
        var landmarks: ArrayList<String>? = null
        firestore = FirebaseFirestore.getInstance()
        firestore.collection("stations")
            .whereEqualTo("station", station)
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    line = document.data.get("line") as ArrayList<String>?
                    facilities = document.data.get("facilities") as ArrayList<String>?
                    fragmentView?.findViewById<TextView>(R.id.info_station).setText(station)
                    if (document.data.get("prevStation") as String? == "0") {
                        fragmentView?.findViewById<Button>(R.id.info_prev_station).setText("없음")
                    } else {
                        fragmentView?.findViewById<Button>(R.id.info_prev_station)
                            .setText(document.data.get("prevStation") as String?)
                    }
                    if (document.data.get("nextStation") as String? == "0") {
                        fragmentView?.findViewById<Button>(R.id.info_next_station).setText("없음")
                    } else {
                        fragmentView?.findViewById<Button>(R.id.info_next_station)
                            .setText(document.data.get("nextStation") as String?)
                    }
                    fragmentView?.findViewById<Button>(R.id.info_main_station).setText(station)
                    var listItem = (document.data.get("landmarks") as ArrayList<String>?)?.toArray()
                    val adapter = ArrayAdapter(
                        requireActivity(), android.R.layout.simple_list_item_1,
                        listItem as Array<out Any>
                    )
                    val listview = fragmentView.findViewById(R.id.info_landmarks) as ListView
                    listview.setAdapter(adapter)
                }
            }
            .addOnFailureListener { exception ->
                Log.w("failure", "Error getting documents: ", exception)
            }
    }
}
