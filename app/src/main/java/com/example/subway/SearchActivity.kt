package com.example.subway

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.subway.model.BookmarkListViewItem
import com.example.subway.model.HistoryListViewItem
import org.json.JSONArray
import org.json.JSONException

import android.preference.PreferenceManager

import android.content.SharedPreferences
import kotlin.reflect.typeOf


class SearchActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)


        var bookmark = getStringArrayPref("bookmark")
        val boomarkList = mutableListOf<BookmarkListViewItem>()
            for(b in bookmark) {
                val bookmarkItem = BookmarkListViewItem(b)
                boomarkList.add(bookmarkItem)
            }
        val adapter1 = BookmarkListViewAdapter(boomarkList)
        val listview1 = findViewById(R.id.search_bookmark) as ListView
        listview1.setAdapter(adapter1)

        var history = getStringArrayPref("history")
        var historyList = mutableListOf<HistoryListViewItem>()
        for(i in history.size -1 downTo 0) {
            var h = history[i]
            var arr = h.split(" ")
            Log.d("arr", arr.toString())
            var historyItem : HistoryListViewItem
            if(arr.size < 2) {
                break
            }
            if(arr.size == 3) {
                 historyItem = HistoryListViewItem(arr[0],arr[1],arr[2])
            } else {
                 historyItem = HistoryListViewItem(arr[0],arr[1])
            }
            historyList.add(historyItem)
        }
        var adapter2 = HistoryListViewAdapter(historyList)
        var listview2 = findViewById(R.id.search_history) as ListView
        listview2.setAdapter(adapter2)
        listview2.setOnItemClickListener(HistoryItemClickListener())
        var searchBtn = findViewById<Button>(R.id.search_search_btn)
        searchBtn.setOnClickListener{
            val intent = Intent(this, ResultActivity::class.java)
            history = getStringArrayPref("history")
            Log.d("history 추가전", history.toString())
            var departureStation = findViewById<EditText>(R.id.search_departure_station).text.toString()
            var transitStation = findViewById<EditText>(R.id.search_transit_station).text.toString()
            var arrivalStation = findViewById<EditText>(R.id.search_arrival_station).text.toString()
            intent.putExtra("departureStation", departureStation)
            intent.putExtra("transitStation", transitStation)
            intent.putExtra("arrivalStation", arrivalStation)
            var route = departureStation + ' ' + arrivalStation + " " + transitStation
            if(departureStation != "" || arrivalStation != "") {
                startActivityForResult(intent, 101)
                if(history.size >= 5) {
                    history = ArrayList<String>(history.subList(1,5))
                }
                history.add(route)
                Log.d("history 추가후", history.toString())

                setStringArrayPref("history", history)
                historyList = mutableListOf<HistoryListViewItem>()
                for(i in history.size -1 downTo 0) {
                    var h = history[i]
                    var arr = h.split(" ")
                    Log.d("arr", arr.toString())
                    var historyItem : HistoryListViewItem
                    if(arr.size < 2) {
                        break
                    }
                    if(arr.size == 3) {
                        historyItem = HistoryListViewItem(arr[0],arr[1],arr[2])
                    } else {
                        historyItem = HistoryListViewItem(arr[0],arr[1])
                    }
                    historyList.add(historyItem)
                }
                adapter2 = HistoryListViewAdapter(historyList)
                listview2.setAdapter(adapter2)
                adapter2.notifyDataSetChanged()
            }
        }
    }

    inner class BookmarkListViewAdapter(val items: MutableList<BookmarkListViewItem>) : BaseAdapter()  {
        override fun getCount(): Int = items.size
        override fun getItem(position: Int): BookmarkListViewItem = items[position]
        override fun getItemId(position: Int): Long = position.toLong()
        override fun getView(position: Int, view: View?, parent: ViewGroup?): View {
            var convertView = view
            if (convertView == null) {
                convertView = layoutInflater.inflate(R.layout.bookmark_list_item, null)
            }
            val item: BookmarkListViewItem = items[position]
            var station = convertView?.findViewById<TextView>(R.id.bookmark_list_item_station)
            station?.text = item.station
            var departureBtn = convertView?.findViewById<Button>(R.id.bookmark_list_item_departure)
            departureBtn?.setOnClickListener {
                var departure = findViewById<EditText>(R.id.search_departure_station)
                departure.setText(item.station)
            }
            var transitBtn = convertView?.findViewById<Button>(R.id.bookmark_list_item_transit)
            transitBtn?.setOnClickListener {
                var transit = findViewById<EditText>(R.id.search_transit_station)
                transit.setText(item.station)
            }
            var arrivalBtn = convertView?.findViewById<Button>(R.id.bookmark_list_item_arrival)
            arrivalBtn?.setOnClickListener {
                var arrival = findViewById<EditText>(R.id.search_arrival_station)
                arrival.setText(item.station)
            }
            return convertView!!
        }
    }
    inner class HistoryListViewAdapter(val items: MutableList<HistoryListViewItem>) : BaseAdapter()  {
        override fun getCount(): Int = items.size
        override fun getItem(position: Int): HistoryListViewItem = items[position]
        override fun getItemId(position: Int): Long = position.toLong()
        override fun getView(position: Int, view: View?, parent: ViewGroup?): View {
            var convertView = view
            if (convertView == null) {
                convertView = layoutInflater.inflate(R.layout.history_list_item, null)
            }
            val item: HistoryListViewItem = items[position]


            var departureStation = convertView?.findViewById<TextView>(R.id.history_departure)
            departureStation?.text = item.departureStation
            var arrivalStation = convertView?.findViewById<TextView>(R.id.history_arrival)
            arrivalStation?.text = item.arrivalStation
            var transitLayout = convertView?.findViewById<LinearLayout>(R.id.history_transit_layout)
            transitLayout?.visibility = View.VISIBLE
            if(item.transitStation == "" || item.transitStation == null) {
                transitLayout?.visibility = View.GONE
            } else {
                var transitStation = convertView?.findViewById<TextView>(R.id.history_transit)
                transitStation?.text = item.transitStation
            }
            return convertView!!
        }

    }
    inner class HistoryItemClickListener : AdapterView.OnItemClickListener {
        override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            var departure = view?.findViewById<TextView>(R.id.history_departure)?.text.toString()
            var transit = view?.findViewById<TextView>(R.id.history_transit)?.text.toString()
            var arrival= view?.findViewById<TextView>(R.id.history_arrival)?.text.toString()
            var departureStation = findViewById<EditText>(R.id.search_departure_station)
            var transitStation = findViewById<EditText>(R.id.search_transit_station)
            var arrivalStation = findViewById<EditText>(R.id.search_arrival_station)
            departureStation.setText(departure)
            if(transit != "") {
                transitStation.setText(transit)
            } else {
                transitStation.setText("")
            }
            arrivalStation.setText(arrival)
        }

    }

    fun setStringArrayPref(key: String, items: ArrayList<String>) {
        val sharedPreference = getSharedPreferences("subway", 0)
        val editor = sharedPreference.edit()
        val json = JSONArray()
        for(item in items) {
            json.put(item)
        }
        if (items.isEmpty()) {
            editor.putString(key, null)
        } else {
            editor.putString(key, json.toString())
        }
        editor.apply()
    }

    fun getStringArrayPref(key: String): ArrayList<String> {
        val sharedPreference = getSharedPreferences("subway", 0)
        val json = sharedPreference.getString(key, null)
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