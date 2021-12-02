package com.example.subway

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.subway.model.BookmarkListViewItem
import com.example.subway.model.HistoryListViewItem
import org.json.JSONArray
import org.json.JSONException

class HistoryFragment : Fragment() {
    companion object {
        var adapter: HistoryListViewAdapter? = null
        var listView: ListView? = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_history, container, false)
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
        adapter = HistoryListViewAdapter(historyList)
        listView = view?.findViewById(R.id.history) as ListView
        setHistoryListViewAdapter(historyList)
//        listView?.setOnItemClickListener(object : AdapterView.OnItemClickListener {
//            override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
//            }
//        })
        return view
    }

    fun setHistoryListViewAdapter(historyList: MutableList<HistoryListViewItem>) {
        adapter = HistoryListViewAdapter(historyList)
        listView?.setAdapter(adapter)
    }

    fun setStringArrayPref(key: String, items: ArrayList<String>) {
        val sharedPreference = activity?.getSharedPreferences("subway", 0)
        val editor = sharedPreference?.edit()
        val json = JSONArray()
        for (item in items) {
            json.put(item)
        }
        if (items.isEmpty()) {
            editor?.putString(key, null)
        } else {
            editor?.putString(key, json.toString())
        }
        editor?.apply()
    }

    fun getStringArrayPref(key: String): ArrayList<String> {
        val sharedPreference = activity?.getSharedPreferences("subway", 0)
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

    fun adapterChanged(station: String?) {
        var history = getStringArrayPref("history")
        history.remove(station)
        setStringArrayPref("history", history)
        val historyList = mutableListOf<HistoryListViewItem>()
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
        adapter = HistoryListViewAdapter(historyList)
        listView?.setAdapter(adapter)
        adapter?.notifyDataSetChanged()
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
            var transitStation: TextView? = null
            if (item.transitStation == "" || item.transitStation == null) {
                transitLayout?.visibility = View.GONE
            } else {
                transitStation = convertView?.findViewById<TextView>(R.id.history_transit)
                transitStation?.text = item.transitStation
            }
//            var btnRemove = convertView?.findViewById<TextView>(R.id.history_remove)
//            btnRemove?.setOnClickListener {
//                if (transitStation?.text.toString() == "null" || transitStation?.text.toString() == " ") {
//                    Log.d(departureStation?.text.toString() + ' '+ arrivalStation?.text.toString() + " ", "click")
//                    adapterChanged(departureStation?.text.toString() + ' '+ arrivalStation?.text.toString() + " ")
//                } else {
//                    adapterChanged(departureStation?.text.toString() + ' ' + arrivalStation?.text.toString() + " " + transitStation?.text.toString())
//                }
//            }
            var historyListItem = convertView?.findViewById<LinearLayout>(R.id.history_list_item)
            historyListItem?.setOnClickListener {
                var departure =
                    convertView?.findViewById<TextView>(R.id.history_departure)?.text.toString()
                var transit =
                    convertView?.findViewById<TextView>(R.id.history_transit)?.text.toString()
                var arrival =
                    convertView?.findViewById<TextView>(R.id.history_arrival)?.text.toString()
                var departureStation =
                    activity?.findViewById<EditText>(R.id.search_departure_station)
                var transitStation = activity?.findViewById<EditText>(R.id.search_transit_station)
                var arrivalStation = activity?.findViewById<EditText>(R.id.search_arrival_station)
                Log.d(departure + ' ' + arrival + " " + transit, "click")

                departureStation?.setText(departure)
                if (transit != "") {
                    transitStation?.setText(transit)
                } else {
                    transitStation?.setText("")
                }
                arrivalStation?.setText(arrival)
            }
            return convertView!!
        }

    }
}