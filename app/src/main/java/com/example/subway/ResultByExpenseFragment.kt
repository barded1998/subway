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
import android.widget.Toast
import androidx.core.content.ContextCompat

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.subway.model.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class ResultByExpenseFragment : Fragment() {
    // Store instance variables
    private var departureStation: String? = null
    private var transitStation: String? = null
    private var arrivalStation: String? = null

    // newInstance constructor for creating fragment with arguments
    fun newInstance(
        departureStation: String?,
        transitStation: String?,
        arrivalStation: String?
    ): ResultByExpenseFragment? {
        val fragment = ResultByExpenseFragment()
        val args = Bundle()
        args.putString("departureStation", departureStation)
        args.putString("transitStation", transitStation)
        args.putString("arrivalStation", arrivalStation)
        Log.d("newInstance", "Expense")
        fragment.setArguments(args)
        return fragment
    }

    // Store instance variables based on arguments passed
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        departureStation = arguments?.getString("departureStation")
        transitStation = arguments?.getString("transitStation")
        arrivalStation = arguments?.getString("arrivalStation")
    }

    // Inflate the view for the fragment based on layout XML
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(
            com.example.subway.R.layout.fragment_result_by_expense,
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

        //Get shortest path by time
        //#####The difference of other fragments#####
        var path = getShortestPathByExpense(departureStation, arrivalStation)
        //If departure == arrival -> set temporary route to prevent errors
        if(departureStation == arrivalStation) {
            path = mutableListOf("101", "102", "103")
        }

        //make combined path with lines and path
        var makedPath = makePath(path)

        //if transitStation exists, update path
        if(transitStation != "" && transitStation != null) {
            //#####The difference of other fragments#####
            var path1 = getShortestPathByExpense(departureStation, transitStation)
            var path2 = getShortestPathByExpense(transitStation, arrivalStation)
            makedPath = makePath(path1).plus(makePath(path2)) as MutableList<MutableList<String?>>
            path1.removeAt(path1.size -1)
            path = path1.plus(path2) as MutableList<String?>
        }

        //Set the images of the departureStation and arrivalStation at the top
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
        //Set ExpandableList(result list of path) by using ExpandableItemList
        var data = makeExpandableItemList(makedPath)

        //Set the time spent
        var time = view?.findViewById<TextView>(R.id.expense_time)
        var cost = getTime(path)
        var mm = cost/60
        var ss = cost % 60
        time.setText(mm.toString() + "분 " + ss.toString() + "초")

        //Set start time and end time
        val cal = Calendar.getInstance()
        cal.time = Date()
        var dataFormat = SimpleDateFormat("a h:mm:ss", Locale.KOREA)
        var timeStart = view?.findViewById<TextView>(R.id.time_start)
        timeStart.setText("${dataFormat.format(cal.time)}")
        cal.add(Calendar.MINUTE, mm)
        cal.add(Calendar.SECOND, ss)
        var timeEnd = view?.findViewById<TextView>(R.id.time_end)
        timeEnd.setText("${dataFormat.format(cal.time)}")

        //Set the number of station
        var numOfStation = view?.findViewById<TextView>(R.id.expense_num_of_station)
        var size = 0
        numOfStation.setText((path.size -1).toString())

        //Set the number of transfer
        var transfer = view?.findViewById<TextView>(R.id.expense_transfer)
        transfer.setText((makedPath.size -1).toString())

        //Set the time spent
        var expense = view?.findViewById<TextView>(R.id.expense_expense)
        expense.setText(getExpense(path).toString())
        var timeBar = view?.findViewById<LinearLayout>(R.id.expense_bar)

        //Add LinearLayout about time spent for each case
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

            //For divide the layout
            if(i != makedPath.size -1){
                if (makedPath[i][0] == makedPath[i + 1][0]) {
                    var divisionLayout = LinearLayout(context)
                    divisionLayout.layoutParams = LinearLayout.LayoutParams(
                        2,
                        LinearLayout.LayoutParams.MATCH_PARENT,
                    )
                    divisionLayout.setBackgroundColor(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.colorDivision
                        )
                    )
                    timeBar.addView(divisionLayout)
                }
            }
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
    // Make Expandable Item List with ArrayList
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