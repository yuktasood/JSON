package com.example.jsondemo

import android.app.ProgressDialog
import android.os.AsyncTask
import android.os.Bundle
import android.view.View
import android.widget.Adapter
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
import java.net.HttpURLConnection
import java.net.URL
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    lateinit var pDialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val url = "https://api.jsonbin.io/b/5eb8e22f47a2266b14766ec0"
        AsyncTaskHandler().execute(url)
    }
    inner class AsyncTaskHandler: AsyncTask<String,String,String>(){
        override fun onPreExecute() {
            super.onPreExecute()
            pDialog= ProgressDialog(this@MainActivity)
            pDialog.setMessage("PLEASE WAIT")
            pDialog.setCancelable(false)
            pDialog.show()
        }

        override fun doInBackground(vararg url: String?): String {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            val res: String
            val connection = URL(url[0]).openConnection() as HttpURLConnection
            try {
                connection.connect()
                res = connection.inputStream.use { it.reader().use { reader-> reader.readText() } }
            }
            finally {
                connection.disconnect()
            }
            return res
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            if (pDialog.isShowing())
                pDialog.dismiss()
            jsonResult(result)

        }
        private fun jsonResult(jsonString: String?){
            val jsonArray = JSONArray(jsonString)
            val list = ArrayList<Model>()
            var i = 0
            while (i<jsonArray.length())
            {
                val jsonObject = jsonArray.getJSONObject(i)
                list.add(
                    Model(
                    jsonObject.getString("name"),
                    jsonObject.getString("duration"),
                        jsonObject.getString("fee"),
                        jsonObject.getString("fategory")

                )
                )
                i++
            }
            val adapter= CustomAdapter(this@MainActivity, list)
            json_listView.adapter= adapter
        }
    }
    }
