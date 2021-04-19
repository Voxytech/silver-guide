package com.eseo.silverguide.ui.bonus

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.eseo.silverguide.R

class Adapter(private val deviceList: Array<DroneItem>, private val onClick: ((selectedDevice: String) -> Unit)? = null) : RecyclerView.Adapter<Adapter.ViewHolder>() {

    // Comment s'affiche ma vue
    @SuppressLint("Con")
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun showItem(device: DroneItem, onClick: ((selectedDevice: String) -> Unit)? = null) {
            itemView.findViewById<TextView>(R.id.title_drone).text = device.name
            itemView.findViewById<ImageView>(R.id.image_drone).setImageResource(device.icon)

            if(onClick != null && device.onClick != null) {
                itemView.setOnClickListener {
                    device.onClick.invoke()
                }
            }
        }
    }

    // Retourne une « vue » / « layout » pour chaque élément de la liste
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_drone, parent, false)
        return ViewHolder(view)
    }

    // Connect la vue ET la données
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.showItem(deviceList[position], onClick)
    }

    override fun getItemCount(): Int {
        return deviceList.size
    }

}