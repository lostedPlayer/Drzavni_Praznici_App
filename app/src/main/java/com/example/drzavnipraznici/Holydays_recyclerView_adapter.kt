package com.example.drzavnipraznici

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

 class Holydays_recyclerView_adapter(
    val context: Context,
    val list: List<ResponseData_HolyDays>
) :
    RecyclerView.Adapter<Holydays_recyclerView_adapter.myViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.country_holidays_card, parent, false)
        return myViewHolder(view)
    }

    override fun onBindViewHolder(holder: myViewHolder, position: Int) {
        val curentData = list.get(position)
        val date = curentData.date
        val name = curentData.name
        val localName = curentData.localName


        holder.tv_holyday_date.text = date
        holder.tv_holyday_name.text = "$name ($localName)"

    }

    override fun getItemCount(): Int {
        return list.size
    }

    //View Holder class here
    inner class myViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tv_holyday_date =
            itemView.findViewById<TextView>(R.id.textView_holydayDate_countryHolydays_activity)
        val tv_holyday_name =
            itemView.findViewById<TextView>(R.id.textView_holydayName_countryHolydays_activity)

    }
}