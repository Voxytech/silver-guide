package com.eseo.silverguide.data

import android.content.Context
import android.content.SharedPreferences
import android.location.Geocoder
import android.location.Location
import com.eseo.silverguide.ui.localisation.LocationItem
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashSet

class LocalPreferences private constructor(context: Context){

    private val sharedPreferences: SharedPreferences = context.getSharedPreferences("", Context.MODE_PRIVATE)
    private val locationGeocode = Geocoder(context, Locale.getDefault())

    fun addLocation(loc: Location) {
        val set = sharedPreferences.getStringSet(PREFERENCES_GEOCODE, HashSet<String>())
        val address = locationGeocode.getFromLocation(loc.latitude, loc.longitude, 2)
        val history = HashSet<String>(set)
        val id = history.size + 1

        val addressToSave = if (address.isNotEmpty()) {
            address[0].getAddressLine(0)
        } else {
            loc.latitude.toString() + " " + loc.longitude.toString()
        }
        history.add(id.toString() + ":" + loc.latitude.toString() + ":" + loc.longitude.toString() + ":" + addressToSave)
        sharedPreferences.edit().putStringSet(PREFERENCES_GEOCODE, history).apply()
    }

    fun getLocations(): ArrayList<LocationItem> {
        val set = sharedPreferences.getStringSet(PREFERENCES_GEOCODE, HashSet<String>())
        val locations = ArrayList<LocationItem>()
        set?.forEach { loc: String ->
            val data = loc.split(":")
            if (data.size == 4) {
                val loc = Location("")
                loc.latitude = data[1].toDouble()
                loc.longitude = data[2].toDouble()
                locations.add(LocationItem(loc, data[3]))
            }
        }
        return locations
    }

    fun clear() {
        sharedPreferences.edit().clear().apply()
    }

    companion object {
        private const val PREFERENCES_GEOCODE = "preferences_geocode"
        private var INSTANCE: LocalPreferences? = null
        fun getInstance(context: Context): LocalPreferences {
            return INSTANCE?.let {
                INSTANCE
            } ?: run {
                INSTANCE = LocalPreferences(context)
                return INSTANCE!!
            }
        }
    }
}