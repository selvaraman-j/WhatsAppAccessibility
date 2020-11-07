package com.selva.waaccessibility

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.view.accessibility.AccessibilityManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val systemService = getSystemService(Context.ACCESSIBILITY_SERVICE)
        if (systemService is AccessibilityManager) {
            if (systemService.isEnabled.not()) {
                val intent = Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS)
                startActivity(intent)

                Toast.makeText(
                    this,
                    "Please enable WhatsAppAccessibility service!",
                    Toast.LENGTH_LONG
                )
                    .show()
            }
        }
    }
}