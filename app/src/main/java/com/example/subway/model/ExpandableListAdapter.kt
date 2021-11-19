package com.example.subway.model

import com.example.subway.R
import android.content.Context
import android.util.Log

import android.widget.TextView

import androidx.recyclerview.widget.RecyclerView

import android.view.ViewGroup

import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.view.marginRight


class ExpandableListAdapter(private val data: ArrayList<Item>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder?>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        var view: View? = null
        val context: Context = parent.context
        val dp: Float = context.getResources().getDisplayMetrics().density
        val subItemPaddingLeft = (38 * dp).toInt()
        val subItemPaddingTopAndBottom = (5 * dp).toInt()
        when (viewType) {
            HEADER -> {
                val inflater =
                    parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
                view = inflater.inflate(com.example.subway.R.layout.list_header2, parent, false)
                return ListHeaderViewHolder(view)
            }
            CHILD -> {
                val itemTextView = TextView(context)
                itemTextView.setPadding(
                    subItemPaddingLeft,
                    subItemPaddingTopAndBottom,
                    0,
                    subItemPaddingTopAndBottom
                )
                itemTextView.setTextColor(-0x78000000)
                itemTextView.layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
                return object : RecyclerView.ViewHolder(itemTextView) {}
            }
            LAST -> {
                val inflater =
                    parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
                view = inflater.inflate(com.example.subway.R.layout.list_last, parent, false)
                return ListLastViewHolder(view)
            }
        }

        return object : RecyclerView.ViewHolder(view!!) {}
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = data[position]
        when (item!!.type) {
            HEADER -> {
                val itemController = holder as ListHeaderViewHolder?
                itemController!!.refferalItem = item
                itemController.header_title.text = item.text
                var path = mutableListOf<String?>()
                var children = item.children
                for(i in 1..children?.size!! -1 ) {
                    path.add(children?.get(i))
                }
                if(children?.size == 3) {
                    itemController.btn_expand_toggle.visibility = View.GONE
                }
                itemController.header_time.text =(getTime(path)/60).toString() + "분" + (getTime(path)%60).toString() + "초 소요"
                itemController.header_station.text = (children?.size-2).toString() + "개 역 이동"
                itemController.header_expense.text = (getExpense(path)).toString() + "원"

                if (item.invisibleChildren == null) {
                    itemController.btn_expand_toggle.setImageResource(R.drawable.circle_minus)
                } else {
                    itemController.btn_expand_toggle.setImageResource(R.drawable.cirlce_plus)
                }
                itemController.btn_expand_toggle.setOnClickListener(View.OnClickListener {
                    if (item.invisibleChildren == null) {
                        item.invisibleChildren = ArrayList()
                        var count = 0
                        val pos = data.indexOf(itemController.refferalItem)
                        while (data.size > pos + 1 && data[pos + 1]!!.type == CHILD) {
                            (item.invisibleChildren as ArrayList<Item?>).add(data.removeAt(pos + 1))
                            count++
                        }
                        notifyItemRangeRemoved(pos + 1, count)
                        itemController.btn_expand_toggle.setImageResource(R.drawable.cirlce_plus)
                    } else {
                        val pos = data.indexOf(itemController.refferalItem)
                        var index = pos + 1
                        for (i in item.invisibleChildren!!) {
                            data.add(index, i!!)
                            index++
                        }
                        notifyItemRangeInserted(pos + 1, index - pos - 1)
                        itemController.btn_expand_toggle.setImageResource(R.drawable.circle_minus)
                        item.invisibleChildren = null
                    }
                })
            }
            CHILD -> {
                val itemTextView = holder!!.itemView as TextView
                itemTextView.text = data[position]!!.text
            }
            LAST -> {
                val itemController = holder as ListLastViewHolder?
                itemController!!.refferalItem = item
                itemController.last_title.text = item.text
            }
        }
    }


    override fun getItemViewType(position: Int): Int {
        return data[position]!!.type
    }

    override fun getItemCount(): Int {
        return data.size
    }

    private class ListHeaderViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {
        var header_title: TextView
        var header_time: TextView
        var header_station: TextView
        var header_expense: TextView
        var btn_expand_toggle: ImageView
        var refferalItem: Item? = null

        init {
            header_title = itemView?.findViewById(R.id.header_title)!!
            header_time = itemView?.findViewById(R.id.header_time)!!
            header_station = itemView?.findViewById(R.id.header_station)!!
            header_expense = itemView?.findViewById(R.id.header_expense)!!
            btn_expand_toggle = itemView?.findViewById(R.id.btn_expand_toggle) as ImageView
        }
    }
    private class ListLastViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {
        var last_title: TextView
        var refferalItem: Item? = null

        init {
            last_title = itemView?.findViewById(R.id.last_title)!!
        }
    }

    class Item {
        var type = 0
        var text: String? = null
        var invisibleChildren: MutableList<Item?>? = null
        var children : MutableList<String?>? = null
        
        constructor(type: Int, text: String?, children: MutableList<String?>? = null) {
            this.type = type
            this.text = text
            this.children = children
        }
    }

    companion object {
        const val HEADER = 0
        const val CHILD = 1
        const val LAST = 2
    }
}
