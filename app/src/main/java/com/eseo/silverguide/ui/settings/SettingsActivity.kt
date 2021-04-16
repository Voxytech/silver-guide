package com.eseo.silverguide.ui.settings

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.eseo.silverguide.R

class SettingsActivity : AppCompatActivity() {

    companion object {
        fun getStartIntent(context: Context): Intent {
            return Intent(context, SettingsActivity::class.java)
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        var rv = findViewById<RecyclerView>(R.id.rvDevices);

        rv.layoutManager = LinearLayoutManager(this)
        rv.adapter = Adapter(arrayOf(
            SettingsItem("Paramètre Bluetooth", R.drawable.bluetooth) {
                val targetIntent = Intent().apply {
                    action = android.provider.Settings.ACTION_BLUETOOTH_SETTINGS
                }
                startActivity(targetIntent);
            },
            SettingsItem("Informations", R.drawable.info) {
                Toast.makeText(this@SettingsActivity, "Application développé par Clément Lévêque", Toast.LENGTH_SHORT).show()
            },
            SettingsItem("Paramètres de l'application", R.drawable.app_settings) {
                val targetIntent = Intent().apply {
                    action = android.provider.Settings.ACTION_APPLICATION_SETTINGS
                }
                startActivity(targetIntent);
            },
            SettingsItem("Paramètres de localisation", R.drawable.location) {
                val targetIntent = Intent().apply {
                    action = android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS
                }
                startActivity(targetIntent);
            },
            SettingsItem("Me contacter par mail", R.drawable.mail) {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("mailto:clement.leveque.147@gmail.com")));
            },
            SettingsItem("Position de l'ESEO", R.drawable.location) {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("geo:47.472822,-0.5621756")));
            },

            SettingsItem("Site de l'ESEO", R.drawable.laptop) {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://www.eseo.fr")));
            })) {
            //Toast.makeText(this@SettingsActivity, "Toast", Toast.LENGTH_SHORT).show()
        }
    }
}