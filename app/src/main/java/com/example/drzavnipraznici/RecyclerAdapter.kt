package com.example.drzavnipraznici

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class RecyclerAdapter(
    val context: Context,
    val countryClickListner: onCountryClickListner,
    val list: List<ResponseData_CountryList>
) :
    RecyclerView.Adapter<RecyclerAdapter.viewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.country_info_card, parent, false)
        return viewHolder(view)
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        val data = list.get(position)
        val countryName = data.name
        val countryCode = data.countryCode
        val imageUrl = "https://countryflagsapi.com/png/" + countryCode

        holder.countryName.text = countryName
        Glide.with(context).load(imageUrl).circleCrop().into(holder.flag)

        // handle when card is clicked
        holder.cardView.setOnClickListener {
            countryClickListner.onCountryClickedListner(countryCode, countryName)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    //view Holder
    inner class viewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val countryName: TextView = itemView.findViewById(R.id.textView_country_name)
        val flag = itemView.findViewById<ImageView>(R.id.imageView_country_flag)
        val cardView: CardView = itemView.findViewById(R.id.cardView)
    }

}