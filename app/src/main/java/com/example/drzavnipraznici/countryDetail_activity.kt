package com.example.drzavnipraznici

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class countryDetail_activity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: Holydays_recyclerView_adapter
    private var list: List<ResponseData_HolyDays>? = null
    private val gSon = Gson()

    var countryCode: String = "AT"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_country_detail)

        val extra: Bundle? = intent.extras
        countryCode = extra!!.get("countryCode").toString()


        recyclerView = findViewById(R.id.recyclerView_country_Holidays_list)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val apiUrl = "https://date.nager.at/api/v3/PublicHolidays/2023/" + countryCode
        var holydayData = CallAPILoginAsyncTask().execute(apiUrl).get()
        Log.i("TAG", "Holyday data : " + holydayData.toString())

        extractHolydayDataFromJSON(holydayData)


        // finish Recycler view setup
        adapter = Holydays_recyclerView_adapter(applicationContext, list!!)
        recyclerView.adapter = adapter
        adapter!!.notifyDataSetChanged()

    }

    // Get data from JSON here
    fun extractHolydayDataFromJSON(rawData: String) {
        var result: String? = rawData.toString()
        val listType: Type = object : TypeToken<List<ResponseData_HolyDays?>?>() {}.type

        list = gSon.fromJson(result, listType)

        for (i in list!!) {
            Log.i("TAG", "name : " + i.name + "-- code : " + i.name)
        }

    }
}