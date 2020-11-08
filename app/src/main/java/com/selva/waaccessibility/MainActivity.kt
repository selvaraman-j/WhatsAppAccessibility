package com.selva.waaccessibility

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.view.View
import android.view.accessibility.AccessibilityManager
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private var serviceStatusView: TextView? = null
    private var enableAccessibilityView: View? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        serviceStatusView = findViewById(R.id.serviceStatusView)
        enableAccessibilityView = findViewById(R.id.enableAccessibilityBtn)
        enableAccessibilityView?.setOnClickListener(this)
    }

    override fun onResume() {
        super.onResume()
        val isConnected = isServiceConnected()
        updateServiceStatus(isConnected)
        setAccessibilityBtnVisibility(isConnected)
    }

    override fun onClick(view: View) {
        if (enableAccessibilityView == view) {
            launchAccessibilitySettings()
        }
    }

    private fun updateServiceStatus(isConnected: Boolean) {
        serviceStatusView?.text =
            getString(if (isConnected) R.string.connected else R.string.not_connected)
    }

    private fun setAccessibilityBtnVisibility(isConnected: Boolean) {
        enableAccessibilityView?.visibility = if (isConnected) View.GONE else View.VISIBLE
    }

    private fun launchAccessibilitySettings() {
        val intent = Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS)
        startActivity(intent)
    }

    private fun isServiceConnected(): Boolean {
        val systemService = getSystemService(Context.ACCESSIBILITY_SERVICE)
        if (systemService is AccessibilityManager) {
            return systemService.isEnabled
        }
        return false
    }
}