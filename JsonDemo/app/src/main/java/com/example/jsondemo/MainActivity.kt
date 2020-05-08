package com.example.jsondemo

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.row.*
import okhttp3.*
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException
import java.io.InputStream
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    lateinit var progress:ProgressBar
    lateinit var listView_details: ListView
    var arrayList_details:ArrayList<Model> = ArrayList();
    //OkHttpClient creates connection pool between client and server
    val client = OkHttpClient()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        progress.visibility = View.VISIBLE
        listView_details = findViewById<ListView>(R.id.json_listView) as ListView
        read_json()
    }
    fun read_json(){
        var json: String? = null
        try {
            val inputStream: InputStream = assets.open("course.json")
            json= inputStream.bufferedReader().use { it.readText()}

            var jsonarr= JSONArray(json)
            for (i in 0..jsonarr.length()-1){
                var jsonObj = jsonarr.getJSONObject(i)
                var model:Model= Model();
                model.name=jsonObj.getString("name")
                model.duration=jsonObj.getString("duration")
                model.fee=jsonObj.getString("fee")
                model.fategory=jsonObj.getString("fategoty")

                arrayList_details.add(model)

            }
            val obj_adapter : CustomAdapter
            obj_adapter = CustomAdapter(applicationContext,arrayList_details)
            listView_details.adapter=obj_adapter
        }
        catch (e: IOException){

        }
    }




}
