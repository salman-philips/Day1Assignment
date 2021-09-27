package com.abrselmantutorials.day1assignment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CountryAdapter(private val countryList: ArrayList<String>) :
    RecyclerView.Adapter<CountryAdapter.CountryViewHolder>() {

    class CountryViewHolder(countryRowItem: View) : RecyclerView.ViewHolder(countryRowItem) {
        private val rowItemText = countryRowItem.findViewById(R.id.row_item_text) as TextView
        fun onBind(text: String) {
            rowItemText.text = text
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        return CountryViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.recycler_row_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.onBind(countryList[position])
    }

    override fun getItemCount(): Int = countryList.size

}