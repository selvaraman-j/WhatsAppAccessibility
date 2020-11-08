package com.selva.waaccessibility

import android.accessibilityservice.AccessibilityService
import android.accessibilityservice.AccessibilityServiceInfo
import android.util.Log
import android.view.accessibility.AccessibilityEvent
import android.view.accessibility.AccessibilityNodeInfo


class WhatsAppAccessibilityService : AccessibilityService() {

    companion object {
        private val PACKAGE_NAMES = arrayOf("com.whatsapp")
        private const val TAG = "WAAccessibilityService"
    }

    override fun onAccessibilityEvent(event: AccessibilityEvent?) {

        when (event?.eventType) {
            AccessibilityEvent.TYPE_WINDOW_CONTENT_CHANGED -> {
                printAll(rootInActiveWindow)
            }
        }
    }

    override fun onInterrupt() {}

    override fun onServiceConnected() {
        super.onServiceConnected()
        Log.d(TAG, "onServiceConnected")

        val info = AccessibilityServiceInfo().apply {
            flags = AccessibilityServiceInfo.DEFAULT
            packageNames = PACKAGE_NAMES
            eventTypes = AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED or
                    AccessibilityEvent.TYPE_WINDOW_CONTENT_CHANGED
            feedbackType = AccessibilityServiceInfo.FEEDBACK_GENERIC
        }
        serviceInfo = info
    }

    private fun printAll(nodeInfo: AccessibilityNodeInfo?) {
        nodeInfo?.run {

            text?.run {
                Log.v(TAG, this.toString())
            }

            for (position in 0 until childCount) {
                printAll(getChild(position))
            }
        }
    }
}