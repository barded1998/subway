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
import androidx.core.widget.addTextChangedListener
import kotlin.reflect.typeOf


class SearchActivity : AppCompatActivity() {
    var stations = arrayListOf<String>(
        "101",
        "102",
        "103",
        "104",
        "105",
        "106",
        "107",
        "108",
        "109",
        "110",
        "111",
        "112",
        "113",
        "114",
        "115",
        "116",
        "117",
        "118",
        "119",
        "120",
        "121",
        "122",
        "123",
        "201",
        "202",
        "203",
        "204",
        "205",
        "206",
        "207",
        "208",
        "209",
        "210",
        "211",
        "212",
        "213",
        "214",
        "215",
        "216",
        "217",
        "301",
        "302",
        "303",
        "304",
        "305",
        "306",
        "307",
        "308",
        "401",
        "402",
        "403",
        "404",
        "405",
        "406",
        "407",
        "408",
        "409",
        "410",
        "411",
        "412",
        "413",
        "414",
        "415",
        "416",
        "417",
        "501",
        "502",
        "503",
        "504",
        "505",
        "506",
        "507",
        "601",
        "602",
        "603",
        "604",
        "605",
        "606",
        "607",
        "608",
        "609",
        "610",
        "611",
        "612",
        "613",
        "614",
        "615",
        "616",
        "617",
        "618",
        "619",
        "620",
        "621",
        "622",
        "701",
        "702",
        "703",
        "704",
        "705",
        "706",
        "707",
        "801",
        "802",
        "803",
        "804",
        "805",
        "806",
        "901",
        "902",
        "903",
        "904",
    )

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        //Set Auto Complete Text View with stations
        var departure = findViewById<AutoCompleteTextView>(R.id.search_departure_station)
        var transit = findViewById<AutoCompleteTextView>(R.id.search_transit_station)
        var arrival = findViewById<AutoCompleteTextView>(R.id.search_arrival_station)
        departure.setAdapter(
            ArrayAdapter<String>(
                this,
                android.R.layout.simple_dropdown_item_1line,
                stations
            )
        )
        transit.setAdapter(
            ArrayAdapter<String>(
                this,
                android.R.layout.simple_dropdown_item_1line,
                stations
            )
        )
        arrival.setAdapter(
            ArrayAdapter<String>(
                this,
                android.R.layout.simple_dropdown_item_1line,
                stations
            )
        )

        //Set bookmark list by getting data from shared preference (local storage)
        var bookmark = getStringArrayPref("bookmark")
        val boomarkList = mutableListOf<BookmarkListViewItem>()
        for (b in bookmark) {
            val bookmarkItem = BookmarkListViewItem(b)
            boomarkList.add(bookmarkItem)
        }
        val adapter1 = BookmarkListViewAdapter(boomarkList)
        val listview1 = findViewById(R.id.search_bookmark) as ListView
        listview1.setAdapter(adapter1)

        //Set history list by getting data from shared preference (local storage)
        var history = getStringArrayPref("history")
        var historyList = mutableListOf<HistoryListViewItem>()
        for (i in history.size - 1 downTo 0) {
            var h = history[i]
            var arr = h.split(" ")
            var historyItem: HistoryListViewItem
            if (arr.size < 2) {
                break
            }
            if (arr.size == 3) {
                historyItem = HistoryListViewItem(arr[0], arr[1], arr[2])
            } else {
                historyItem = HistoryListViewItem(arr[0], arr[1])
            }
            historyList.add(historyItem)
        }
        var adapter2 = HistoryListViewAdapter(historyList)
        var listview2 = findViewById(R.id.search_history) as ListView
        listview2.setAdapter(adapter2)
        listview2.setOnItemClickListener(HistoryItemClickListener())

        //set searchBtn click listener
        var searchBtn = findViewById<Button>(R.id.search_search_btn)
        searchBtn.setOnClickListener {
            val intent = Intent(this, ResultActivity::class.java)
            history = getStringArrayPref("history")
            var departureStation =
                findViewById<EditText>(R.id.search_departure_station).text.toString()
            var transitStation = findViewById<EditText>(R.id.search_transit_station).text.toString()
            var arrivalStation = findViewById<EditText>(R.id.search_arrival_station).text.toString()
            //To prevent errors
            if (!(stations.contains(departureStation)) && departureStation != "") {
                departure.text = null
                Toast.makeText(applicationContext, "잘못된 출발역을 입력했습니다.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (!(stations.contains(transitStation)) && transitStation != "") {
                transit.text = null
                Toast.makeText(applicationContext, "잘못된 경유역을 입력했습니다.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener

            }
            if ((!stations.contains(arrivalStation)) && arrivalStation != "") {
                arrival.text = null
                Toast.makeText(applicationContext, "잘못된 도착역을 입력했습니다.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (departureStation == "" || arrivalStation == "") {
                Toast.makeText(applicationContext, "출발역 혹은 도착역을 입력해 주십시오.", Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            }
            if (departureStation == transitStation) {
                Toast.makeText(applicationContext, "출발역과 경유역이 같습니다.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (transitStation == arrivalStation) {
                Toast.makeText(applicationContext, "경유역과 도착역이 같습니다.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (departureStation == arrivalStation &&  transitStation == "") {
                Toast.makeText(applicationContext, "출발역과 도착역이 같습니다.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener

            }
            if (departureStation.length != 3 || (transitStation != "" && transitStation.length != 3) || arrivalStation.length != 3) {
                if (departureStation.length != 3) {
                    departure.setText(null)
                    Toast.makeText(
                        applicationContext,
                        "출발역을 세자리 수로 입력해주십시오.",
                        Toast.LENGTH_SHORT
                    ).show()
                } else if (transitStation != "" && transitStation.length != 3) {
                    transit.setText(null)
                    Toast.makeText(
                        applicationContext,
                        "경유역을 세자리 수로 입력해주십시오.",
                        Toast.LENGTH_SHORT
                    ).show()
                } else if (arrivalStation.length != 3) {
                    arrival.setText(null)
                    Toast.makeText(
                        applicationContext,
                        "도착역을 세자리 수로 입력해주십시오.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                return@setOnClickListener
            }
            intent.putExtra("departureStation", departureStation)
            intent.putExtra("transitStation", transitStation)
            intent.putExtra("arrivalStation", arrivalStation)
            var route = departureStation + ' ' + arrivalStation + " " + transitStation
            if (departureStation != "" || arrivalStation != "") {
                startActivityForResult(intent, 101)
                if (history.size >= 10) {
                    history = ArrayList<String>(history.subList(1, 10))
                }
                history.add(route)

                setStringArrayPref("history", history)
                historyList = mutableListOf<HistoryListViewItem>()
                for (i in history.size - 1 downTo 0) {
                    var h = history[i]
                    var arr = h.split(" ")
                    var historyItem: HistoryListViewItem
                    if (arr.size < 2) {
                        break
                    }
                    if (arr.size == 3) {
                        historyItem = HistoryListViewItem(arr[0], arr[1], arr[2])
                    } else {
                        historyItem = HistoryListViewItem(arr[0], arr[1])
                    }
                    historyList.add(historyItem)
                }
                adapter2 = HistoryListViewAdapter(historyList)
                listview2.setAdapter(adapter2)
                adapter2.notifyDataSetChanged()
            }

        }
    }

    inner class BookmarkListViewAdapter(val items: MutableList<BookmarkListViewItem>) :
        BaseAdapter() {
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

    inner class HistoryListViewAdapter(val items: MutableList<HistoryListViewItem>) :
        BaseAdapter() {
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
            if (item.transitStation == "" || item.transitStation == null) {
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
            var arrival = view?.findViewById<TextView>(R.id.history_arrival)?.text.toString()
            var departureStation = findViewById<EditText>(R.id.search_departure_station)
            var transitStation = findViewById<EditText>(R.id.search_transit_station)
            var arrivalStation = findViewById<EditText>(R.id.search_arrival_station)
            departureStation.setText(departure)
            if (transit != "") {
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
        for (item in items) {
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