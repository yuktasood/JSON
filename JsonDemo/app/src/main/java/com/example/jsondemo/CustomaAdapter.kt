package com.example.jsondemo

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView

class CustomAdapter(context: Context,arrayListDetails:ArrayList<Model>) : BaseAdapter() {
    private val layoutInflater: LayoutInflater
    private val arrayListDetails:ArrayList<Model>

    init {
        this.layoutInflater = LayoutInflater.from(context)
        this.arrayListDetails=arrayListDetails
    }

    override fun getItem(position: Int): Any {
        return arrayListDetails.get(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()      }

    override fun getCount(): Int {
        return arrayListDetails.size
    }
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        val view: View?
        val listRowHolder: ListRowHolder
        if (convertView == null) {
            view = this.layoutInflater.inflate(R.layout.row, parent, false)
            listRowHolder = ListRowHolder(view)
            view.tag = listRowHolder
        } else {
            view = convertView
            listRowHolder = view.tag as ListRowHolder
        }
        listRowHolder.course_name_tv.text = arrayListDetails.get(position).name
        listRowHolder.course_duration_tv.text = arrayListDetails.get(position).duration
        listRowHolder.course_fee_tv.text = arrayListDetails.get(position).fee
        listRowHolder.course_fategory_tv.text = arrayListDetails.get(position).fategory
        return view
    }
}

private class ListRowHolder(row: View?) {
    public val course_name_tv: TextView
    public val course_duration_tv: TextView
    public val course_fee_tv: TextView
    public val course_fategory_tv: TextView

    init {
        this.course_name_tv = row?.findViewById<TextView>(R.id.course_name_tv) as TextView
        this.course_duration_tv = row?.findViewById<TextView>(R.id.course_duration_tv) as TextView
        this.course_fee_tv = row?.findViewById<TextView>(R.id.course_fee_tv) as TextView
        this.course_fategory_tv= row?.findViewById<TextView>(R.id.course_category_tv) as TextView
    }
}
