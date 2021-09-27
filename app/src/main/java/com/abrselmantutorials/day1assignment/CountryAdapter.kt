package com.abrselmantutorials.day1assignment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class CountryAdapter(private val countryList: ArrayList<String>) :
    RecyclerView.Adapter<CountryAdapter.CountryViewHolder>() {

    inner class CountryViewHolder(countryRowItem: View) : RecyclerView.ViewHolder(countryRowItem),
        View.OnClickListener {
        private val rowItemText = countryRowItem.findViewById(R.id.row_item_text) as TextView
        fun onBind(text: String) {
            rowItemText.text = text
        }

        init {
            countryRowItem.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            if (v != null) {
                Toast.makeText(
                    v.context,
                    "Country clicked is ${countryList[adapterPosition]}",
                    Toast.LENGTH_SHORT
                ).show()
            }
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