package com.example.jsondemo

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView

class CustomAdapter(val context: Context,val list:ArrayList<Model>) : BaseAdapter() {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        val view: View= LayoutInflater.from(context).inflate(R.layout.row,parent, false)
        val name= view.findViewById<TextView>(R.id.course_name_tv)
        val duration = view.findViewById<TextView>(R.id.course_duration_tv)
        val fee= view.findViewById<TextView>(R.id.course_fee_tv)
        val category= view.findViewById<TextView>(R.id.course_category_tv)

        name.text = list[position].name.toString()
        duration.text= list[position].duration.toString()
        fee.text= list[position].fee.toString()
        category.text= list[position].fategory.toString()
        return view
    }

    override fun getItem(position: Int): Any {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        return position
    }

    override fun getItemId(position: Int): Long {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        return position.toLong()
    }

    override fun getCount(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        return list.size
    }

}
