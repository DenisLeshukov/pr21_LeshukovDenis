package com.bignerdranch.android.prakt21_leshukovdenis

import androidx.recyclerview.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.view.LayoutInflater
import android.widget.TextView
class AdapterRecycler(private val records: List<CurrencyRecord>) :
    RecyclerView.Adapter<AdapterRecycler.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val cur: TextView = view.findViewById(R.id.textCurrency)
        val sum: TextView = view.findViewById(R.id.textSum)
        val date: TextView = view.findViewById(R.id.textDate)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_record, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val record = records[position]
        holder.cur.text = record.currency
        holder.sum.text = record.sum.toString()
        holder.date.text = record.date
    }

    override fun getItemCount() = records.size
}