package com.abrselmantutorials.day1assignment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyAdapter(val dataList: ArrayList<Data>) :
    RecyclerView.Adapter<RecyAdapter.RecyViewHolder>() {

    inner class RecyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView1 = itemView.findViewById<TextView>(android.R.id.text1)
        val textView2 = itemView.findViewById<TextView>(android.R.id.text2)

        fun onBind(data: Data) {
            textView1.text = data.fName
            textView2.text = data.lName
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(android.R.layout.simple_list_item_2, parent, false)
        return RecyViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyViewHolder, position: Int) {
        holder.onBind(dataList[position])
    }

    override fun getItemCount() = dataList.size
}