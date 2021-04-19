package com.eseo.silverguide.ui.history

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.eseo.silverguide.R
import com.eseo.silverguide.ui.localisation.LocationItem

class Adapter(private val historyItem: Array<HistoryItem>) : RecyclerView.Adapter<Adapter.ViewHolder>() {

    // Comment s'affiche ma vue
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun showItem(location: LocationItem) {
            itemView.findViewById<TextView>(R.id.title_history).text = location.address
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_history, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.showItem(historyItem.get(position).location)
    }

    override fun getItemCount(): Int {
        return historyItem.size
    }

}