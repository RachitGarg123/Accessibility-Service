package com.example.accessibilityservicepresentation

import android.accessibilityservice.AccessibilityService
import android.accessibilityservice.AccessibilityServiceInfo
import android.content.Intent
import android.os.Handler
import android.os.Looper
import android.view.accessibility.AccessibilityEvent
import android.view.accessibility.AccessibilityNodeInfo
import com.example.accessibilityservicepresentation.AppConstants.DEMO_APP

class MyAccessibilityService: AccessibilityService() {

    // this is optional and called once initially our accessibility service starts
    // Use this Method to perform one time setup
    override fun onServiceConnected() {
        val info = AccessibilityServiceInfo()
        info.apply {
//            we can use the packageNames property to use the service for the provided apps else it will apply to all apps
//            packageNames = arrayOf(DEMO_APP)
            eventTypes = AccessibilityEvent.TYPES_ALL_MASK
//            flags can be used to access gestures events like fingerprint or touch exploration mode
            flags = (AccessibilityServiceInfo.FLAG_REPORT_VIEW_IDS or AccessibilityServiceInfo.CAPABILITY_CAN_RETRIEVE_WINDOW_CONTENT)
            feedbackType = AccessibilityServiceInfo.FEEDBACK_ALL_MASK
            /* The minimal period in milliseconds between two accessibility events of the same type are sent to this service.
            This setting can be changed at runtime by calling */
            notificationTimeout = 100
        }
        this.serviceInfo = info
    }

/*     this is the required method and this function is called everytime when any of the
       mentioned Accessibility event occurs and we can retrieve the window content using
       event.source which gives us an object of Accessibility Node Info through we can
       traverse the entire tree using recursion */
    override fun onAccessibilityEvent(event: AccessibilityEvent?) {
        printAllNodes(event, rootInActiveWindow) {
            it.performAction(AccessibilityNodeInfo.ACTION_CLICK)
             /*This is Delayed this much because we are clicking on another app's ui
             which can take some time in rendering the view*/
            Handler(Looper.getMainLooper()).postDelayed({
                it.performAction(AccessibilityNodeInfo.ACTION_CLEAR_FOCUS)
            }, 10000)
        }
    }

/*     this is the required method
     the system calls this method when the system wants to interrupt the feedback
     your service is providing, usually in response to a user action such as
     moving focus to a different control */
    override fun onInterrupt() {

    }

    // this is optional method and will be called when the service is shutting down
    // use this method to perform work you need to before the service stops
    override fun onUnbind(intent: Intent?): Boolean {
        return super.onUnbind(intent)
    }

}