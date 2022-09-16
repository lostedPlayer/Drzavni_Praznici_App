package com.example.drzavnipraznici

import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.lang.reflect.Type
import java.net.HttpURLConnection
import java.net.SocketTimeoutException
import java.net.URL


class MainActivity : AppCompatActivity(), onCountryClickListner {

    var listMain: List<ResponseData_CountryList>? = null
    lateinit var recyclerViewMain: RecyclerView
    var adapter: RecyclerAdapter? = null
    val gson = Gson()
    var countryCode: String = "AT"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        recyclerViewMain = findViewById(R.id.recyclerView_country_list)
        recyclerViewMain.layoutManager = LinearLayoutManager(this)

        //getting data for country list
        val apiUrl: String = "https://date.nager.at/api/v3/AvailableCountries"

        var countryData = CallAPILoginAsyncTask().execute(apiUrl).get()
        Log.i("TAG", "returned data: $countryData")

        extractCountryDataFromJSON(countryData)


        // finish Recycler view setup
        adapter = RecyclerAdapter(this, this, listMain!!)
        recyclerViewMain.adapter = adapter
        adapter!!.notifyDataSetChanged()
    }


    // Get data from JSON here
    fun extractCountryDataFromJSON(rawData: String) {
        var result: String? = rawData.toString()
        val listType: Type = object : TypeToken<List<ResponseData_CountryList?>?>() {}.type

        listMain = gson.fromJson(result, listType)

        for (i in listMain!!) {
            Log.i("TAG", "name : " + i.name + "-- code : " + i.countryCode)
        }

    }


    //
    override fun onCountryClickedListner(countryCode: String , countryNamey : String) {
        this.countryCode = countryCode
        val intent = Intent(this, countryDetail_activity::class.java)
        intent.putExtra("countryCode", countryCode)
        this.startActivity(intent)
    }
}