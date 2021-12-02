package com.example.subway

import android.content.Intent
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
import android.widget.ArrayAdapter
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.subway.R.color
import com.google.firebase.firestore.DocumentSnapshot
import org.json.JSONArray
import org.json.JSONException
import java.util.*
import kotlin.collections.ArrayList


class StationInfoFragment : Fragment() {
    private lateinit var fragmentView: View
    private lateinit var firestore: FirebaseFirestore
    private lateinit var station: String
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //initialize
        station = arguments?.getString("station")!!
        firestore = FirebaseFirestore.getInstance()
        fragmentView =
            LayoutInflater.from(activity).inflate(R.layout.fragment_station_info, container, false)
        setFragment()
        
        //set info_prev_button
        var bookmark = getStringArrayPref("bookmark")
        if(bookmark?.contains(station)!!) {
            fragmentView?.findViewById<ImageButton>(R.id.info_bookmark_btn).setImageResource(R.drawable.star)
        } else {
            fragmentView?.findViewById<ImageButton>(R.id.info_bookmark_btn).setImageResource(R.drawable.gray_star)
        }
        fragmentView?.findViewById<ImageButton>(R.id.info_bookmark_btn)?.setOnClickListener {
            bookmark = getStringArrayPref("bookmark")
            if(bookmark?.contains(station)!!) {
                fragmentView?.findViewById<ImageButton>(R.id.info_bookmark_btn).setImageResource(R.drawable.gray_star)
                Toast.makeText(context, "즐겨찾기에서 해제되었습니다.", Toast.LENGTH_SHORT).show()
                bookmark?.remove(station)
                var ft: FragmentTransaction = fragmentManager?.beginTransaction()!!
                ft.detach(this).attach(this).commit()
            } else {
                fragmentView?.findViewById<ImageButton>(R.id.info_bookmark_btn).setImageResource(R.drawable.star)
                Toast.makeText(context, "즐겨찾기에 추가되었습니다.", Toast.LENGTH_SHORT).show()
                bookmark?.add(station)
                var ft: FragmentTransaction = fragmentManager?.beginTransaction()!!
                ft.detach(this).attach(this).commit()
            }
            setStringArrayPref("bookmark",bookmark)

        }
        fragmentView?.findViewById<Button>(R.id.info_prev_station)?.setOnClickListener {
            arguments?.putString(
                "prevStation",
                station
            )
            station = fragmentView?.findViewById<Button>(R.id.info_prev_station)?.text.toString()
            arguments?.putString(
                "station",
                station
            )
            station = arguments?.getString("station")!!
            setFragment()
        }
        //set info_next_station
        fragmentView?.findViewById<Button>(R.id.info_next_station)?.setOnClickListener {
            station = fragmentView?.findViewById<Button>(R.id.info_next_station)?.text.toString()
            arguments?.putString(
                "station",
                station
            )
            station = arguments?.getString("station")!!
            setFragment()
        }
        //set info_departure_btn
        fragmentView?.findViewById<LinearLayout>(R.id.info_departure_btn)?.setOnClickListener {
            (activity as MainActivity).departureStation = station
            if((activity as MainActivity).departureStation == (activity as MainActivity).arrivalStation ){
                (activity as MainActivity).arrivalStation = null
            }
            if((activity as MainActivity).departureStation == (activity as MainActivity).transitStation ){
                (activity as MainActivity).transitStation = null
            }
            if ((activity as MainActivity).departureStation != null && (activity as MainActivity).arrivalStation != null) {
                var intent = Intent(context, ResultActivity::class.java)
                intent.putExtra("departureStation", (activity as MainActivity).departureStation)
                intent.putExtra("arrivalStation", (activity as MainActivity).arrivalStation)
                var route = (activity as MainActivity).departureStation + " " + (activity as MainActivity).arrivalStation
                if((activity as MainActivity).transitStation != null) {
                    intent.putExtra("transitStation", (activity as MainActivity).transitStation)
                    route = (activity as MainActivity).departureStation + " " + (activity as MainActivity).arrivalStation + " " + (activity as MainActivity).transitStation
                }
                var history = getStringArrayPref("history")
                history.add(route)
                setStringArrayPref("history",history )
                (activity as MainActivity).departureStation = null
                (activity as MainActivity).transitStation = null
                (activity as MainActivity).arrivalStation = null
                startActivity(intent)
            }
            var ft: FragmentTransaction? = fragmentManager?.beginTransaction()
            ft?.detach(this)?.commit()
        }
        fragmentView?.findViewById<ImageButton>(R.id.info_departure_btn2)?.setOnClickListener {
            (activity as MainActivity).departureStation = station
            if((activity as MainActivity).departureStation == (activity as MainActivity).arrivalStation ){
                (activity as MainActivity).arrivalStation = null
            }
            if((activity as MainActivity).departureStation == (activity as MainActivity).transitStation ){
                (activity as MainActivity).transitStation = null
            }
            if ((activity as MainActivity).departureStation != null && (activity as MainActivity).arrivalStation != null) {
                var intent = Intent(context, ResultActivity::class.java)
                intent.putExtra("departureStation", (activity as MainActivity).departureStation)
                intent.putExtra("arrivalStation", (activity as MainActivity).arrivalStation)
                var route = (activity as MainActivity).departureStation + " " + (activity as MainActivity).arrivalStation
                if((activity as MainActivity).transitStation != null) {
                    intent.putExtra("transitStation", (activity as MainActivity).transitStation)
                    route = (activity as MainActivity).departureStation + " " + (activity as MainActivity).arrivalStation + " " + (activity as MainActivity).transitStation
                }
                var history = getStringArrayPref("history")
                history.add(route)
                setStringArrayPref("history",history )
                (activity as MainActivity).departureStation = null
                (activity as MainActivity).transitStation = null
                (activity as MainActivity).arrivalStation = null
                startActivity(intent)
            }
            var ft: FragmentTransaction? = fragmentManager?.beginTransaction()
            ft?.detach(this)?.commit()
        }
        fragmentView?.findViewById<LinearLayout>(R.id.info_transit_btn)?.setOnClickListener {
            (activity as MainActivity).transitStation = station
            if((activity as MainActivity).transitStation == (activity as MainActivity).arrivalStation ){
                (activity as MainActivity).arrivalStation = null
            }
            if((activity as MainActivity).transitStation == (activity as MainActivity).departureStation ){
                (activity as MainActivity).departureStation = null
            }
            var ft: FragmentTransaction? = fragmentManager?.beginTransaction()
            ft?.detach(this)?.commit()
        }
        fragmentView?.findViewById<ImageButton>(R.id.info_transit_btn2)?.setOnClickListener {
            (activity as MainActivity).transitStation = station
            if((activity as MainActivity).transitStation == (activity as MainActivity).arrivalStation ){
                (activity as MainActivity).arrivalStation = null
            }
            if((activity as MainActivity).transitStation == (activity as MainActivity).departureStation ){
                (activity as MainActivity).departureStation = null
            }
            var ft: FragmentTransaction? = fragmentManager?.beginTransaction()
            ft?.detach(this)?.commit()
        }
        //set info_arrival_btn
        fragmentView?.findViewById<LinearLayout>(R.id.info_arrival_btn)?.setOnClickListener {
            (activity as MainActivity).arrivalStation = station
            if((activity as MainActivity).arrivalStation == (activity as MainActivity).departureStation ){
                (activity as MainActivity).departureStation = null
            }
            if((activity as MainActivity).arrivalStation == (activity as MainActivity).transitStation ){
                (activity as MainActivity).transitStation = null
            }
            if ((activity as MainActivity).departureStation != null && (activity as MainActivity).arrivalStation != null) {
                var intent = Intent(context, ResultActivity::class.java)
                intent.putExtra("departureStation", (activity as MainActivity).departureStation)
                intent.putExtra("arrivalStation", (activity as MainActivity).arrivalStation)
                var route = (activity as MainActivity).departureStation + " " + (activity as MainActivity).arrivalStation
                if((activity as MainActivity).transitStation != null) {
                    intent.putExtra("transitStation", (activity as MainActivity).transitStation)
                    route = (activity as MainActivity).departureStation + " " + (activity as MainActivity).arrivalStation + " " + (activity as MainActivity).transitStation
                }
                var history = getStringArrayPref("history")
                history.add(route)
                setStringArrayPref("history",history )
                (activity as MainActivity).departureStation = null
                (activity as MainActivity).transitStation = null
                (activity as MainActivity).arrivalStation = null
                startActivity(intent)
            }
            var ft: FragmentTransaction? = fragmentManager?.beginTransaction()
            ft?.detach(this)?.commit()
        }
        fragmentView?.findViewById<ImageButton>(R.id.info_arrival_btn2)?.setOnClickListener {
            (activity as MainActivity).arrivalStation = station
            if((activity as MainActivity).arrivalStation == (activity as MainActivity).departureStation ){
                (activity as MainActivity).departureStation = null
            }
            if((activity as MainActivity).arrivalStation == (activity as MainActivity).transitStation ){
                (activity as MainActivity).transitStation = null
            }
            if ((activity as MainActivity).departureStation != null && (activity as MainActivity).arrivalStation != null) {
                var intent = Intent(context, ResultActivity::class.java)
                intent.putExtra("departureStation", (activity as MainActivity).departureStation)
                intent.putExtra("arrivalStation", (activity as MainActivity).arrivalStation)
                var route = (activity as MainActivity).departureStation + " " + (activity as MainActivity).arrivalStation
                if((activity as MainActivity).transitStation != null) {
                    intent.putExtra("transitStation", (activity as MainActivity).transitStation)
                    route = (activity as MainActivity).departureStation + " " + (activity as MainActivity).arrivalStation + " " + (activity as MainActivity).transitStation
                }
                var history = getStringArrayPref("history")
                history.add(route)
                setStringArrayPref("history",history )
                (activity as MainActivity).departureStation = null
                (activity as MainActivity).transitStation = null
                (activity as MainActivity).arrivalStation = null
                startActivity(intent)
            }
            var ft: FragmentTransaction? = fragmentManager?.beginTransaction()
            ft?.detach(this)?.commit()
        }
        return fragmentView
    }

    //set Fragment details
    fun setFragment() {
        if(station == "없음") {
            Toast.makeText(context, "역이 없습니다.", Toast.LENGTH_SHORT).show()
            return
        }
        firestore.document("stations/${station}").get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    var document: DocumentSnapshot? = task.result
                    fragmentView?.findViewById<TextView>(R.id.info_station)
                        .setText(document?.getData()?.get("station") as String?)
                    var bookmark = getStringArrayPref("bookmark")
                    if(bookmark?.contains(station)!!) {
                        fragmentView?.findViewById<ImageButton>(R.id.info_bookmark_btn).setImageResource(R.drawable.star)
                    } else {
                        fragmentView?.findViewById<ImageButton>(R.id.info_bookmark_btn).setImageResource(R.drawable.gray_star)
                    }
                    var lines = (document?.data?.get("line") as ArrayList<String>)
                    var line: Int =  lines[0].toInt()
                    var interchangeStation = document?.getData()?.get("interchangeStation")
                    if(interchangeStation == true) {
                        var prevLine = arguments?.getInt("prevLine")
                        if(prevLine != 0) {
                            line = prevLine!!
                        }
                        arguments?.putInt("prevLine", line)
                    } else {
                        arguments?.putInt("prevLine", line)
                    }
                    fragmentView?.findViewById<ImageView>(R.id.info_line2).visibility = View.VISIBLE
                    when(line) {
                        1 -> {
                            fragmentView?.findViewById<TextView>(R.id.info_station).setTextColor(
                                ContextCompat.getColor(
                                    requireContext(),
                                    R.color.line1
                                )
                            )
                            fragmentView?.findViewById<TextView>(R.id.info_station).setBackgroundResource(R.drawable.circle1)
                        }
                        2 -> {
                            fragmentView?.findViewById<TextView>(R.id.info_station).setTextColor(
                                ContextCompat.getColor(
                                    requireContext(),
                                    R.color.line2
                                )
                            )
                            fragmentView?.findViewById<TextView>(R.id.info_station).setBackgroundResource(R.drawable.circle2)
                        }
                        3 -> {
                            fragmentView?.findViewById<TextView>(R.id.info_station).setTextColor(
                                ContextCompat.getColor(
                                    requireContext(),
                                    R.color.line3
                                )
                            )
                            fragmentView?.findViewById<TextView>(R.id.info_station).setBackgroundResource(R.drawable.circle3)
                        }
                        4 -> {
                            fragmentView?.findViewById<TextView>(R.id.info_station).setTextColor(
                                ContextCompat.getColor(
                                    requireContext(),
                                    R.color.line4
                                )
                            )
                            fragmentView?.findViewById<TextView>(R.id.info_station).setBackgroundResource(R.drawable.circle4)
                        }
                        5 -> {
                            fragmentView?.findViewById<TextView>(R.id.info_station).setTextColor(
                                ContextCompat.getColor(
                                    requireContext(),
                                    R.color.line5
                                )
                            )
                            fragmentView?.findViewById<TextView>(R.id.info_station).setBackgroundResource(R.drawable.circle5)
                        }
                        6 -> {
                            fragmentView?.findViewById<TextView>(R.id.info_station).setTextColor(
                                ContextCompat.getColor(
                                    requireContext(),
                                    R.color.line6
                                )
                            )
                            fragmentView?.findViewById<TextView>(R.id.info_station).setBackgroundResource(R.drawable.circle6)
                        }
                        7 -> {
                            fragmentView?.findViewById<TextView>(R.id.info_station).setTextColor(
                                ContextCompat.getColor(
                                    requireContext(),
                                    R.color.line7
                                )
                            )
                            fragmentView?.findViewById<TextView>(R.id.info_station).setBackgroundResource(R.drawable.circle7)
                        }
                        8 -> {
                            fragmentView?.findViewById<TextView>(R.id.info_station).setTextColor(
                                ContextCompat.getColor(
                                    requireContext(),
                                    R.color.line8
                                )
                            )
                            fragmentView?.findViewById<TextView>(R.id.info_station).setBackgroundResource(R.drawable.circle8)
                        }
                        9 -> {
                            fragmentView?.findViewById<TextView>(R.id.info_station).setTextColor(
                                ContextCompat.getColor(
                                    requireContext(),
                                    R.color.line9
                                )
                            )
                            fragmentView?.findViewById<TextView>(R.id.info_station).setBackgroundResource(R.drawable.circle9)
                        }
                    }
                    fragmentView?.findViewById<ImageButton>(R.id.info_line1).setOnClickListener {
                        arguments?.putInt("prevLine", lines[0].toInt())
                        setFragment()
                    }
                    when(lines[0].toInt()) {
                        1 ->  fragmentView?.findViewById<ImageButton>(R.id.info_line1).setImageResource(R.drawable.line1)
                        2 ->  fragmentView?.findViewById<ImageButton>(R.id.info_line1).setImageResource(R.drawable.line2)
                        3 ->  fragmentView?.findViewById<ImageButton>(R.id.info_line1).setImageResource(R.drawable.line3)
                        4 ->  fragmentView?.findViewById<ImageButton>(R.id.info_line1).setImageResource(R.drawable.line4)
                        5 ->  fragmentView?.findViewById<ImageButton>(R.id.info_line1).setImageResource(R.drawable.line5)
                        6 ->  fragmentView?.findViewById<ImageButton>(R.id.info_line1).setImageResource(R.drawable.line6)
                        7 ->  fragmentView?.findViewById<ImageButton>(R.id.info_line1).setImageResource(R.drawable.line7)
                        8 ->  fragmentView?.findViewById<ImageButton>(R.id.info_line1).setImageResource(R.drawable.line8)
                        9 ->  fragmentView?.findViewById<ImageButton>(R.id.info_line1).setImageResource(R.drawable.line9)
                    }
                    when (line) {
                        1 -> setLinearLayout(fragmentView, R.color.line1)
                        2 -> setLinearLayout(fragmentView, R.color.line2)
                        3 -> setLinearLayout(fragmentView, R.color.line3)
                        4 -> setLinearLayout(fragmentView, R.color.line4)
                        5 -> setLinearLayout(fragmentView, R.color.line5)
                        6 -> setLinearLayout(fragmentView, R.color.line6)
                       7 -> setLinearLayout(fragmentView, R.color.line7)
                        8 ->setLinearLayout(fragmentView, R.color.line8)
                        9 ->  setLinearLayout(fragmentView, R.color.line9)
                    }
                    if (lines.size == 2) {
                        fragmentView?.findViewById<ImageButton>(R.id.info_line2).setOnClickListener {
                            arguments?.putInt("prevLine", lines[1].toInt())
                            setFragment()
                        }
                        when (lines[1].toInt()) {
                            1 -> fragmentView?.findViewById<ImageButton>(R.id.info_line2)
                                .setImageResource(R.drawable.line1)
                            2 -> fragmentView?.findViewById<ImageButton>(R.id.info_line2)
                                .setImageResource(R.drawable.line2)
                            3 -> fragmentView?.findViewById<ImageButton>(R.id.info_line2)
                                .setImageResource(R.drawable.line3)
                            4 -> fragmentView?.findViewById<ImageButton>(R.id.info_line2)
                                .setImageResource(R.drawable.line4)
                            5 -> fragmentView?.findViewById<ImageButton>(R.id.info_line2)
                                .setImageResource(R.drawable.line5)
                            6 -> fragmentView?.findViewById<ImageButton>(R.id.info_line2)
                                .setImageResource(R.drawable.line6)
                            7 -> fragmentView?.findViewById<ImageButton>(R.id.info_line2)
                                .setImageResource(R.drawable.line7)
                            8 -> fragmentView?.findViewById<ImageButton>(R.id.info_line2)
                                .setImageResource(R.drawable.line8)
                            9 -> fragmentView?.findViewById<ImageButton>(R.id.info_line2)
                                .setImageResource(R.drawable.line9)
                        }
                    } else {
                        fragmentView?.findViewById<ImageView>(R.id.info_line2).visibility =
                            View.GONE
                    }
                    if(interchangeStation == true) {
                        Log.d("prevLine", line.toString())
                        var prevStation = (document?.data?.get("prevStation") as ArrayList<String>)
                        var nextStation = (document?.data?.get("nextStation") as ArrayList<String>)
                        if(line == lines[0].toInt()) {
                            if (prevStation[0] == "0") {
                                fragmentView?.findViewById<Button>(R.id.info_prev_station).setText("없음")
                            } else {
                                fragmentView?.findViewById<Button>(R.id.info_prev_station)
                                    .setText(prevStation[0])
                            }
                            if (nextStation[0] == "0") {
                                fragmentView?.findViewById<Button>(R.id.info_next_station).setText("없음")
                            } else {
                                fragmentView?.findViewById<Button>(R.id.info_next_station)
                                    .setText(nextStation[0])
                            }
                        } else {
                            if (prevStation[1] == "0") {
                                fragmentView?.findViewById<Button>(R.id.info_prev_station).setText("없음")
                            } else {
                                fragmentView?.findViewById<Button>(R.id.info_prev_station)
                                    .setText(prevStation[1])
                            }
                            if (nextStation[1] == "0") {
                                fragmentView?.findViewById<Button>(R.id.info_next_station).setText("없음")
                            } else {
                                fragmentView?.findViewById<Button>(R.id.info_next_station)
                                    .setText(nextStation[1])
                            }
                        }
                    } else {
                        if (document?.data?.get("prevStation") as String? == "0") {
                            fragmentView?.findViewById<Button>(R.id.info_prev_station).setText("없음")
                        } else {
                            fragmentView?.findViewById<Button>(R.id.info_prev_station)
                                .setText(document?.data?.get("prevStation") as String?)
                        }
                        if (document?.data?.get("nextStation") as String? == "0") {
                            fragmentView?.findViewById<Button>(R.id.info_next_station).setText("없음")
                        } else {
                            fragmentView?.findViewById<Button>(R.id.info_next_station)
                                .setText(document?.data?.get("nextStation") as String?)
                        }
                    }
                    fragmentView?.findViewById<Button>(R.id.info_main_station).setText(station)
                    var listItem =
                        (document?.data?.get("landmarks") as ArrayList<String>?)?.toArray()
                    val adapter = ArrayAdapter(
                        requireActivity(), android.R.layout.simple_list_item_1,
                        listItem as Array<out Any>
                    )
                    val listview = fragmentView.findViewById(R.id.info_landmarks) as ListView
                    listview.setAdapter(adapter)
                    var facilities = (document?.data?.get("facilities") as ArrayList<Boolean>)
                    if(facilities[0]) {
                        fragmentView?.findViewById<TextView>(R.id.info_toilet).setText("화장실 내부")
                    } else {
                        fragmentView?.findViewById<TextView>(R.id.info_toilet).setText("화장실 외부")
                    }
                    if(facilities[1]) {
                        fragmentView?.findViewById<TextView>(R.id.info_door).setText("내리는문 왼쪽")
                        fragmentView?.findViewById<ImageView>(R.id.info_door_icon).setImageResource(R.drawable.previous)
                    } else {
                        fragmentView?.findViewById<TextView>(R.id.info_door).setText("내리는문 오른쪽")
                        fragmentView?.findViewById<ImageView>(R.id.info_door_icon).setImageResource(R.drawable.next)
                    }
                    if(facilities[2]) {
                        fragmentView?.findViewById<TextView>(R.id.info_across).setText("횡단 가능")
                    } else {
                        fragmentView?.findViewById<TextView>(R.id.info_across).setText("횡단 불가능")
                    }
                } else {
                    Log.d("tastk", task.toString())
                }
            }
    }

    //set LinearLayout Color
    fun setLinearLayout( fragmentView: View, color: Int) {
        fragmentView?.findViewById<LinearLayout>(R.id.info_linear_layout)
            .setBackgroundResource(color)
        fragmentView?.findViewById<Button>(R.id.info_main_station).setTextColor(ContextCompat.getColor(requireContext(), color))
    }

    //set SharedPreference data
    fun setStringArrayPref(key: String, items: ArrayList<String>) {
        val sharedPreference = this.activity?.getSharedPreferences("subway", 0)
        val editor = sharedPreference?.edit()
        val json = JSONArray()
        for(item in items) {
            json.put(item)
        }
        if (items.isEmpty()) {
            editor?.putString(key, null)
        } else {
            editor?.putString(key, json.toString())
        }
        editor?.apply()
    }
    //get SharedPreference data
    fun getStringArrayPref(key: String): ArrayList<String> {
        val sharedPreference = this.activity?.getSharedPreferences("subway", 0)
        val json = sharedPreference?.getString(key, null)
        var array = arrayListOf<String>()
        if (json != null) {
            try {
                val a = JSONArray(json)
                for (i in 0 until a.length()) {
                    val s = a.optString(i)
                    array.add(s)
                }
            } catch (e: JSONException) {
                e.printStackTrace()
            }
        }
        return array
    }
}
