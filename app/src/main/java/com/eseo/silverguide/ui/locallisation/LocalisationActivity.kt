package com.eseo.silverguide.ui.locallisation

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.eseo.silverguide.R
import com.eseo.silverguide.databinding.ActivityLocalisationBinding
import com.eseo.silverguide.ui.MainActivity
import kotlinx.android.synthetic.main.activity_localisation.*
import java.util.*

class LocalisationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLocalisationBinding // <-- Référence à notre ViewBinding

    companion object {
        fun getStartIntent(context: Context): Intent {
            return Intent(context, LocalisationActivity::class.java)
        }

        const val PERMISSION_REQUEST_LOCATION = 9999;
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_localisation)

        // NOUVELLE METHODE --> Indique que l'on utilise le ViewBinding
        binding = ActivityLocalisationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonLoc.setOnClickListener{
            requestPermission()
        }
        binding.buttonRetour.setOnClickListener{
            startActivity(MainActivity.getStartIntent(this))
        }

    }


    private fun hasPermission(): Boolean {
        return ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
    }
    private fun requestPermission() {
        if (!hasPermission()) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                PERMISSION_REQUEST_LOCATION
            )
        } else {
            getLocation()
        }
    }
    //Function pour obtenir la permission de géolocaliser l'utilisateur
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when (requestCode) {
            PERMISSION_REQUEST_LOCATION -> {
                // If request is cancelled, the result arrays are empty.
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    // Permission obtenue, Nous continuons la suite de la logique.
                    getLocation()
                    Toast.makeText(this, getString(R.string.loc_accepte), Toast.LENGTH_LONG).show()
                } else {
                    //Permission non accepté, expliqué ici via une activité ou une dialog pourquoi nous avons besoin de la permission
                    Toast.makeText(this, getString(R.string.loc_refuse), Toast.LENGTH_LONG).show()
                }
                return
            }
        }
    }

    private fun geoCode(location: Location){
        val geocoder = Geocoder(this, Locale.getDefault())
        val results = geocoder.getFromLocation(location.latitude, location.longitude, 1)
        val eseo = Location("")
        eseo.longitude = -0.5508474733889329
        eseo.latitude = 47.49341932098021

        var distance = String.format("%.3f", location.distanceTo(eseo)/1000) + "km"

        if (results.isNotEmpty()) {
            findViewById<TextView>(R.id.text_localisation).text = distance
        }
    }

    //Function pour obtenir la localisation de l'utilisateur
    private fun getLocation() {
        if (hasPermission()) {
            val locationManager = applicationContext.getSystemService(LOCATION_SERVICE) as LocationManager?
            locationManager?.run {
               locationManager.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER)?.run {
                    geoCode(this)
                }
            }
        }
    }
}