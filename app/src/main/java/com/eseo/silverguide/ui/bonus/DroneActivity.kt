package com.eseo.silverguide.ui.bonus

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.eseo.silverguide.R
import com.eseo.silverguide.databinding.ActivityDroneBinding
import com.eseo.silverguide.databinding.ActivityLocalisationBinding
import com.eseo.silverguide.ui.main.MainActivity

class DroneActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDroneBinding // <-- Référence à notre ViewBinding

    companion object {
        fun getStartIntent(context: Context): Intent {
            return Intent(context, DroneActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drone)

        // NOUVELLE METHODE --> Indique que l'on utilise le ViewBinding
        binding = ActivityDroneBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.droneBack.setOnClickListener{
            startActivity(MainActivity.getStartIntent(this))
        }
    }
}