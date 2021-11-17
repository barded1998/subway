package com.example.subway

import android.os.Bundle
import android.util.Log
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.subway.model.*

class ResultByDistanceFragment : Fragment() {
    // Store instance variables
    private var departureStation: String? = null
    private var transitStation: String? = null
    private var arrivalStation: String? = null

    // newInstance constructor for creating fragment with arguments
    public fun newInstance(
        departureStation: String?,
        transitStation: String? = null,
        arrivalStation: String?
    ): ResultByDistanceFragment? {
        val fragment = ResultByDistanceFragment()
        val args = Bundle()
        args.putString("departureStation", departureStation)
        args.putString("transitStation", transitStation)
        args.putString("arrivalStation", arrivalStation)
        fragment.setArguments(args)
        return fragment
    }

    // Store instance variables based on arguments passed
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        departureStation = arguments?.getString("departureStation")
        transitStation = arguments?.getString("transitStation")
        arrivalStation = arguments?.getString("arrivalStation")
        Log.d("onCreate", "Distance")
    }

    // Inflate the view for the fragment based on layout XML
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("onCreateView", "Distance")

        val view: View = inflater.inflate(
            com.example.subway.R.layout.fragment_result_by_distance,
            container,
            false
        )
        var recyclerview = view?.findViewById<RecyclerView>(R.id.recyclerview);
        recyclerview?.setLayoutManager(
            LinearLayoutManager(
                context,
                LinearLayoutManager.VERTICAL,
                false
            )
        );

        var path = getShortestPathByDistance(departureStation, arrivalStation)
        var makedPath = makePath(path)
        if(transitStation != "" && transitStation != null) {
            var path1 = getShortestPathByDistance(departureStation, transitStation)
            var path2 = getShortestPathByDistance(transitStation, arrivalStation)
            makedPath = makePath(path1).plus(makePath(path2)) as MutableList<MutableList<String?>>
            path1.removeAt(path1.size -1)
            path = path1.plus(path2) as MutableList<String?>
        }


        when (makedPath[0][0].toString().toInt()) {
            1 ->  activity?.findViewById<ImageView>(R.id.result_departure_station_img)?.setImageResource(R.drawable.line1)
            2 ->  activity?.findViewById<ImageView>(R.id.result_departure_station_img)?.setImageResource(R.drawable.line2)
            3 ->  activity?.findViewById<ImageView>(R.id.result_departure_station_img)?.setImageResource(R.drawable.line3)
            4 ->  activity?.findViewById<ImageView>(R.id.result_departure_station_img)?.setImageResource(R.drawable.line4)
            5 ->  activity?.findViewById<ImageView>(R.id.result_departure_station_img)?.setImageResource(R.drawable.line5)
            6 ->  activity?.findViewById<ImageView>(R.id.result_departure_station_img)?.setImageResource(R.drawable.line6)
            7 ->  activity?.findViewById<ImageView>(R.id.result_departure_station_img)?.setImageResource(R.drawable.line7)
            8 ->  activity?.findViewById<ImageView>(R.id.result_departure_station_img)?.setImageResource(R.drawable.line8)
            9 ->  activity?.findViewById<ImageView>(R.id.result_departure_station_img)?.setImageResource(R.drawable.line9)

        }
        when (makedPath[makedPath.size-1][0].toString().toInt()) {
            1 ->  activity?.findViewById<ImageView>(R.id.result_arrival_station_img)?.setImageResource(R.drawable.line1)
            2 ->  activity?.findViewById<ImageView>(R.id.result_arrival_station_img)?.setImageResource(R.drawable.line2)
            3 ->  activity?.findViewById<ImageView>(R.id.result_arrival_station_img)?.setImageResource(R.drawable.line3)
            4 ->  activity?.findViewById<ImageView>(R.id.result_arrival_station_img)?.setImageResource(R.drawable.line4)
            5 ->  activity?.findViewById<ImageView>(R.id.result_arrival_station_img)?.setImageResource(R.drawable.line5)
            6 ->  activity?.findViewById<ImageView>(R.id.result_arrival_station_img)?.setImageResource(R.drawable.line6)
            7 ->  activity?.findViewById<ImageView>(R.id.result_arrival_station_img)?.setImageResource(R.drawable.line7)
            8 ->  activity?.findViewById<ImageView>(R.id.result_arrival_station_img)?.setImageResource(R.drawable.line8)
            9 ->  activity?.findViewById<ImageView>(R.id.result_arrival_station_img)?.setImageResource(R.drawable.line9)

        }
        var data = makeExpandableItemList(makedPath)

        var time = view?.findViewById<TextView>(R.id.distance_time)
        time.setText((getTime(path) / 60).toString()+"분 " + (getTime(path) % 60).toString() + "초")

        var numOfStation = view?.findViewById<TextView>(R.id.distance_num_of_station)
        var size = 0
        numOfStation.setText((path.size -1).toString())

        var transfer = view?.findViewById<TextView>(R.id.distance_transfer)
        transfer.setText((makedPath.size -1).toString())

        var expense = view?.findViewById<TextView>(R.id.distance_expense)
        expense.setText(getExpense(path).toString())
        var timeBar = view?.findViewById<LinearLayout>(R.id.distance_bar)

        for(i in 0 until makedPath.size) {
            val layoutParams = LinearLayout.LayoutParams(
                0,
                LinearLayout.LayoutParams.MATCH_PARENT,
            )
            layoutParams.weight = getTime(makedPath[i]).toFloat()
            var linearLayout = LinearLayout(context)
            linearLayout.layoutParams = layoutParams
            linearLayout.gravity = Gravity.CENTER
            var textView = TextView(context)
            textView.setText((layoutParams.weight / 60).toInt().toString()+"분 " + (layoutParams.weight % 60).toInt().toString() + "초")
            textView.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
            textView.setTextSize(10f)
            linearLayout.addView(textView)
            timeBar.addView(linearLayout)
            when (makedPath[i][0].toString().toInt()) {
                1 ->  linearLayout.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.line1))
                2 -> linearLayout.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.line2))
                3 -> linearLayout.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.line3))
                4 -> linearLayout.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.line4))
                5 -> linearLayout.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.line5))
                6 -> linearLayout.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.line6))
                7 -> linearLayout.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.line7))
                8 -> linearLayout.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.line8))
                9 -> linearLayout.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.line9))

            }
        }
        recyclerview?.setAdapter(ExpandableListAdapter(data));

        return view
    }

    fun makeExpandableItemList(path: MutableList<MutableList<String?>>): ArrayList<ExpandableListAdapter.Item> {
        var data: ArrayList<ExpandableListAdapter.Item> = arrayListOf<ExpandableListAdapter.Item>();
        for(array in path) {
            var places:  ExpandableListAdapter.Item= ExpandableListAdapter.Item(ExpandableListAdapter.HEADER, array[1].toString(),array);
            places.invisibleChildren = arrayListOf()
            for(i in 2 until array.size -1 ) {
                (places.invisibleChildren as ArrayList<ExpandableListAdapter.Item?>).add( ExpandableListAdapter.Item(ExpandableListAdapter.CHILD, array[i].toString()));
            }
            data.add(places)
            data.add(ExpandableListAdapter.Item(ExpandableListAdapter.LAST, array[array.size-1].toString()));
        }
        return data
    }

}


//                ArrayList<ExpandableListAdapter.Item> = arrayListOf<ExpandableListAdapter.Item>();
//        data.add(ExpandableListAdapter.Item(ExpandableListAdapter.HEADER, "101"));
//        data.add(ExpandableListAdapter.Item(ExpandableListAdapter.CHILD, "102"));
//        data.add(ExpandableListAdapter.Item(ExpandableListAdapter.CHILD, "103"));
//        data.add(ExpandableListAdapter.Item(ExpandableListAdapter.CHILD, "104"));
//        data.add(ExpandableListAdapter.Item(ExpandableListAdapter.LAST, "201"));

//        var places:  ExpandableListAdapter.Item= ExpandableListAdapter.Item(ExpandableListAdapter.HEADER, "노바");
//        places.invisibleChildren = arrayListOf()
//        (places.invisibleChildren as ArrayList<ExpandableListAdapter.Item?>).add( ExpandableListAdapter.Item(ExpandableListAdapter.CHILD, "카이저"));
//        (places.invisibleChildren as ArrayList<ExpandableListAdapter.Item?>).add( ExpandableListAdapter.Item(ExpandableListAdapter.CHILD, "카인"));
//        (places.invisibleChildren as ArrayList<ExpandableListAdapter.Item?>).add( ExpandableListAdapter.Item(ExpandableListAdapter.CHILD, "카데나"));
//        (places.invisibleChildren as ArrayList<ExpandableListAdapter.Item?>).add( ExpandableListAdapter.Item(ExpandableListAdapter.CHILD, "엔젤릭버스터"));
//        data.add(places)