package com.eseo.silverguide.ui.bonus

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.eseo.silverguide.R
import com.eseo.silverguide.databinding.ActivityDroneBinding
import com.eseo.silverguide.databinding.ActivityLocalisationBinding
import com.eseo.silverguide.ui.main.MainActivity
import com.eseo.silverguide.ui.settings.Adapter
import com.eseo.silverguide.ui.settings.SettingsItem

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

        val rv = findViewById<RecyclerView>(R.id.rvDrones) // anciennement var
        rv.layoutManager = LinearLayoutManager(this)
        rv.addItemDecoration(DividerItemDecoration(rv.context, DividerItemDecoration.VERTICAL))
        rv.adapter = Adapter(arrayOf(
                DroneItem(getString(R.string.drone_bleu), R.drawable.drone_bleu) {
                    Toast.makeText(this@DroneActivity, "drone bleu", Toast.LENGTH_SHORT).show()
                },
                DroneItem(getString(R.string.drone_rouge), R.drawable.drone_red) {
                    Toast.makeText(this@DroneActivity, "drone rouge", Toast.LENGTH_SHORT).show()
                },
                DroneItem(getString(R.string.drone_3D), R.drawable.drone_3d) {
                    Toast.makeText(this@DroneActivity, "drone imprimé en 3D", Toast.LENGTH_SHORT).show()
                },
                DroneItem(getString(R.string.drone_antho), R.drawable.drone_antho) {
                    Toast.makeText(this@DroneActivity, "drone imprimé en 3D", Toast.LENGTH_SHORT).show()
                },
                DroneItem(getString(R.string.drone_pilou), R.drawable.drone_pilou) {
                    Toast.makeText(this@DroneActivity, "tondeuse de pilou", Toast.LENGTH_SHORT).show()
                },
                DroneItem(getString(R.string.drone_tom), R.drawable.drone_tom) {
                    Toast.makeText(this@DroneActivity, "tondeuse de pilou", Toast.LENGTH_SHORT).show()
                })){

        }

        findViewById<Button>(R.id.drone_back).setOnClickListener {
            startActivity(MainActivity.getStartIntent(this))
        }
    }
}