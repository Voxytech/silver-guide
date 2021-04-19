package com.eseo.silverguide.ui.settings

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.eseo.silverguide.R
import com.eseo.silverguide.ui.main.MainActivity

class SettingsActivity : AppCompatActivity() {

    companion object {
        fun getStartIntent(context: Context): Intent {
            return Intent(context, SettingsActivity::class.java)
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        findViewById<RecyclerView>(R.id.rvSettings).layoutManager = LinearLayoutManager(this)
        findViewById<RecyclerView>(R.id.rvSettings).adapter = Adapter(arrayOf(

            SettingsItem(getString(R.string.settings_info), R.drawable.info) {
                Toast.makeText(this@SettingsActivity, "Application développé par Clément Lévêque", Toast.LENGTH_SHORT).show()
            },
            SettingsItem(getString(R.string.settings_app_param), R.drawable.app_settings) {
                val targetIntent = Intent().apply {
                    action = Settings.ACTION_APPLICATION_SETTINGS
                }
                startActivity(targetIntent)
            },
            SettingsItem(getString(R.string.settings_loc_param), R.drawable.location) {
                val targetIntent = Intent().apply {
                    action = Settings.ACTION_LOCATION_SOURCE_SETTINGS
                }
                startActivity(targetIntent)
            },
            SettingsItem(getString(R.string.settings_contact), R.drawable.mail) {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("mailto:clement.leveque.147@gmail.com")))
            },
            SettingsItem(getString(R.string.settings_eseo_location), R.drawable.location) {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("geo:47.472822,-0.5621756")))
            },

            SettingsItem(getString(R.string.settings_eseo_website), R.drawable.laptop) {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://www.eseo.fr")))
            }))
            //Toast.makeText(this@SettingsActivity, "Toast", Toast.LENGTH_SHORT).show()

        findViewById<Button>(R.id.settings_back).setOnClickListener {
            startActivity(MainActivity.getStartIntent(this))
        }
    }
}