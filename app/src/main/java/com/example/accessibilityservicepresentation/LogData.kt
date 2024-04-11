package com.example.accessibilityservicepresentation

import android.util.Log
import android.view.accessibility.AccessibilityEvent
import android.view.accessibility.AccessibilityNodeInfo
import com.example.accessibilityservicepresentation.AppConstants.CLICK_BUTTON

val tag = "MyAccessibilityService"
var i = 0

fun printAllNodes(event: AccessibilityEvent?, nodeInfo: AccessibilityNodeInfo?, performAction: (AccessibilityNodeInfo)-> Unit) {

    // base case
    if(nodeInfo == null || event == null) return

    // we can get all these data and more using accessibilityService and AccessibilityNodeInfo
    Log.i(tag, "event ---> $event")
    Log.i(tag, "action ---> ${event.action}")
    Log.i(tag, "eventTime ---> ${event.eventTime}")
    Log.i(tag, "eventType ---> ${event.eventType}")
    Log.i(tag, "nodeInfo ----> $nodeInfo")
    Log.i(tag, "childCount ----> ${nodeInfo.childCount}")
    Log.i(tag, "text ----> ${nodeInfo.text}")
    Log.i(tag, "viewIdResourceName ----> ${nodeInfo.viewIdResourceName}")
    Log.i(tag, "className ----> ${nodeInfo.className}")
    Log.i(tag, "packageName ----> ${nodeInfo.packageName}")
    Log.i(tag, "isClickable ----> ${nodeInfo.isClickable}")
    Log.i(tag, "contentDescription ----> ${nodeInfo.contentDescription}")
    Log.i(tag, "isFocused ----> ${nodeInfo.isFocused}")
    Log.i(tag, "isScrollable ----> ${nodeInfo.isScrollable}")
    Log.i(tag, "isVisibleToUser ----> ${nodeInfo.isVisibleToUser}")
    Log.i(tag, "windowId ----> ${nodeInfo.windowId}")

    if(nodeInfo.text == CLICK_BUTTON && i == 0) {
        ++i
        performAction(nodeInfo)
    }

    // calling recursively for every child
    for(i in 0 ..< nodeInfo.childCount) {
        printAllNodes(event, nodeInfo.getChild(i), performAction)
    }
}