package com.example.jsondemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray
import java.io.InputStream
import java.io.File.*
import java.io.IOException

class MainActivity : AppCompatActivity() {
    var name = arrayListOf<String>()
    var duration = arrayListOf<String>()
    var fee= arrayListOf<String>()
    var category= arrayListOf<String>()
    var course_image= arrayListOf<Int>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        read_json()
    }
    fun read_json(){
        var json: String? = null
        try {
            val inputStream:InputStream= assets.open("course.json")
            json= inputStream.bufferedReader().use { it.readText()}

            var jsonarr= JSONArray(json)
            for (i in 0..jsonarr.length()-1){
                var jsonObj = jsonarr.getJSONObject(i)
                name.add(jsonObj.getString("name"))
                duration.add(jsonObj.getString("duration"))

            }
            var adpt = ArrayAdapter(this, android.R.layout.simple_list_item_1,name)

            json_listView.adapter = adpt
        }
        catch (e: IOException){

        }
    }

}
