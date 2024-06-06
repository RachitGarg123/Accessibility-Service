package com.example.accessibilityservicepresentation

import android.util.Log
import android.view.accessibility.AccessibilityEvent
import android.view.accessibility.AccessibilityNodeInfo

const val tag = "MyAccessibilityService"
const val CLICK_BUTTON = "CLICK BUTTON"

fun printAllNodes(event: AccessibilityEvent?, nodeInfo: AccessibilityNodeInfo?, performAction: (AccessibilityNodeInfo)-> Unit) {

    // base case
    if(nodeInfo == null || event == null) return

    // we can get all these data and more using AccessibilityNodeInfo
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

    if(nodeInfo.text == CLICK_BUTTON) {
        performAction(nodeInfo)
    }

    // calling recursively for every child
    for(i in 0 ..< nodeInfo.childCount) {
        printAllNodes(event, nodeInfo.getChild(i), performAction)
    }
}