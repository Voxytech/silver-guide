package com.eseo.silverguide.ui.history

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.eseo.silverguide.R
import com.eseo.silverguide.data.LocalPreferences
import com.eseo.silverguide.ui.localisation.LocationItem
import com.eseo.silverguide.ui.main.MainActivity
import kotlin.collections.ArrayList

class HistoryActivity : AppCompatActivity() {

    companion object {
        fun getStartIntent(context: Context): Intent {
            return Intent(context, HistoryActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        var rv = findViewById<RecyclerView>(R.id.rvHistory)
        rv.layoutManager = LinearLayoutManager(this)
        val locations = LocalPreferences.getInstance(this).getLocations()
        val historyItems = ArrayList<HistoryItem>() //tableau d'HistoryItem
        locations.forEach { location: LocationItem -> historyItems.add(HistoryItem(location))}

        rv.adapter = Adapter(historyItems.toTypedArray())
        findViewById<Button>(R.id.history_back).setOnClickListener {
            startActivity(MainActivity.getStartIntent(this))
        }
        findViewById<Button>(R.id.history_clear).setOnClickListener {
            LocalPreferences.getInstance(this).clear()
            startActivity(MainActivity.getStartIntent(this))
        }
    }
}