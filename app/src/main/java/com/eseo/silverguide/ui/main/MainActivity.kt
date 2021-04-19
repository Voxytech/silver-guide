package com.eseo.silverguide.ui.main

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import com.eseo.silverguide.R
import com.eseo.silverguide.data.LocalPreferences
import com.eseo.silverguide.databinding.ActivityMainBinding
import com.eseo.silverguide.ui.bonus.DroneActivity
import com.eseo.silverguide.ui.history.HistoryActivity
import com.eseo.silverguide.ui.localisation.LocalisationActivity
import com.eseo.silverguide.ui.settings.SettingsActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding // <-- Référence à notre ViewBinding

    companion object {
        fun getStartIntent(context: Context): Intent {
            return Intent(context, MainActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // NOUVELLE METHODE --> Indique que l'on utilise le ViewBinding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.distance.setOnClickListener{
            startActivity(LocalisationActivity.getStartIntent(this))
        }
        binding.settings.setOnClickListener{
            startActivity(SettingsActivity.getStartIntent(this))
        }

        binding.historique.setOnClickListener {
            val historyEnable = LocalPreferences.getInstance(this).getLocations()
            if(historyEnable.size > 0){
                startActivity(HistoryActivity.getStartIntent(this))
            }
            else{
                Toast.makeText(this@MainActivity, getString(R.string.mainWarningNoHistory), Toast.LENGTH_SHORT).show()
            }
        }

        binding.github.setOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/Voxytech/silver-guide")))
        }

        binding.drone.setOnClickListener {
            startActivity(DroneActivity.getStartIntent(this))
        }
    }
}