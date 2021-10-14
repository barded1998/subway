package com.example.subway

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.R

import android.widget.EditText
class ResultByTimeFragment : Fragment() {
    // Store instance variables
    private var title: String? = null
    private var page = 0

    // newInstance constructor for creating fragment with arguments
    public fun newInstance(page: Int, title: String?): ResultByTimeFragment? {
        val fragment = ResultByTimeFragment()
        val args = Bundle()
        args.putInt("someInt", page)
        args.putString("someTitle", title)
        fragment.setArguments(args)
        return fragment
    }

    // Store instance variables based on arguments passed
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        page = arguments?.getInt("someInt", 0)!!
        title = arguments?.getString("someTitle")!!
    }

    // Inflate the view for the fragment based on layout XML
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(com.example.subway.R.layout.fragment_result_by_time, container, false)
        val tvLabel = view.findViewById<View>(com.example.subway.R.id.editText) as EditText
        tvLabel.setText("$page -- $title")
        return view
    }

}