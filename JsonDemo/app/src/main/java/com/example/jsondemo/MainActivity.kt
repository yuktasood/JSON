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
        lateinit var listView_details: ListView
    var arrayList_details:ArrayList<Model> = ArrayList();
    //OkHttpClient creates connection pool between client and server
    val client = OkHttpClient()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
               listView_details = findViewById<ListView>(R.id.json_listView) as ListView
        run("https://api.jsonbin.io/b/5eb8e22f47a2266b14766ec0")
    }
    fun run(url: String){
        val request= Request.Builder()
            .url(url)
            .build()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
            }
            override fun onResponse(call: Call, response: Response) {
                var str_response = response.body()!!.string()
                //creating json object
                val json_contact:JSONObject = JSONObject(str_response)
                //creating json array
                var jsonarray_info:JSONArray= json_contact.getJSONArray("info")
                var i:Int = 0
                var size:Int = jsonarray_info.length()
                arrayList_details= ArrayList();
                for (i in 0.. size-1) {
                    var json_objectdetail:JSONObject=jsonarray_info.getJSONObject(i)
                    var model:Model= Model();
                    model.name=json_objectdetail.getString("name")
                    model.duration=json_objectdetail.getString("duration")
                    model.fee=json_objectdetail.getString("fee")
                    model.fategory=json_objectdetail.getString("category")

                    arrayList_details.add(model)
                }
                runOnUiThread {
                    //stuff that updates ui
                    val obj_adapter : CustomAdapter
                    obj_adapter = CustomAdapter(applicationContext,arrayList_details)
                    listView_details.adapter=obj_adapter
                }
            }

        })
}}
//    fun read_json(){
//        var json: String? = null
//        try {
//            val inputStream: InputStream = assets.open("course.json")
//            json= inputStream.bufferedReader().use { it.readText()}
//
//            var jsonarr= JSONArray(json)
//            for (i in 0..jsonarr.length()-1){
//                var jsonObj = jsonarr.getJSONObject(i)
//                var model:Model= Model();
//                model.name=jsonObj.getString("name")
//                model.duration=jsonObj.getString("duration")
//                model.fee=jsonObj.getString("fee")
//                model.fategory=jsonObj.getString("fategoty")
//
//                arrayList_details.add(model)
//
//            }
//            val obj_adapter : CustomAdapter
//            obj_adapter = CustomAdapter(applicationContext,arrayList_details)
//            listView_details.adapter=obj_adapter
//        }
//        catch (e: IOException){
//
//        }
