package com.example.subway

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.subway.model.BookmarkListViewItem
import org.json.JSONArray
import org.json.JSONException


class BookmarkFragment : Fragment() {
    companion object{
        var adpater: BookmarkListViewAdapter?  = null
        var listView: ListView?  = null
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_bookmark, container, false)
        Log.d("heloo", "hello")
        // Inflate the layout for this fragment
        var bookmark = getStringArrayPref("bookmark")
        val boomarkList = mutableListOf<BookmarkListViewItem>()
        for (b in bookmark) {
            val bookmarkItem = BookmarkListViewItem(b)
            boomarkList.add(bookmarkItem)
        }
        adpater = BookmarkListViewAdapter(boomarkList)
        listView =  view.findViewById(R.id.bookmark) as ListView
        listView?.setAdapter(adpater)
        return view
    }
    fun adapterChanged(station: String?) {
        var bookmark = getStringArrayPref("bookmark")
        bookmark.remove(station)
        setStringArrayPref("bookmark", bookmark)
        val boomarkList = mutableListOf<BookmarkListViewItem>()
        for (b in bookmark) {
            val bookmarkItem = BookmarkListViewItem(b)
            boomarkList.add(bookmarkItem)
        }
        adpater = BookmarkListViewAdapter(boomarkList)
        listView?.setAdapter(adpater)
        adpater?.notifyDataSetChanged()
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
                var departure = activity?.findViewById<EditText>(R.id.search_departure_station)
                departure?.setText(item.station)
            }
            var transitBtn = convertView?.findViewById<Button>(R.id.bookmark_list_item_transit)
            transitBtn?.setOnClickListener {
                var transit = activity?.findViewById<EditText>(R.id.search_transit_station)
                transit?.setText(item.station)
            }
            var arrivalBtn = convertView?.findViewById<Button>(R.id.bookmark_list_item_arrival)
            arrivalBtn?.setOnClickListener {
                var arrival = activity?.findViewById<EditText>(R.id.search_arrival_station)
                arrival?.setText(item.station)
            }
            var removeBtn = convertView?.findViewById<Button>(R.id.bookmark_list_item_remove)
            removeBtn?.setOnClickListener {
                adapterChanged(station?.text.toString())
                notifyDataSetChanged()
            }
            return convertView!!
        }
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
}
