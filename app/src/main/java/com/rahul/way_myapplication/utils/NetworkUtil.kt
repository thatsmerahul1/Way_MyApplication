package com.rahul.way_myapplication.utils

import android.content.BroadcastReceiver
import android.content.Context
import android.content.IntentFilter
import android.net.ConnectivityManager

object NetworkUtil {
    fun initNetworkReceiver(context: Context, receiver: BroadcastReceiver) {
        context.registerReceiver(receiver, IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))
    }

    fun isNetworkAvailable(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = connectivityManager.activeNetworkInfo
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting
    }

    fun unregisterNetworkReceiver(context: Context,  receiver: BroadcastReceiver) {
        receiver.let {
            context.unregisterReceiver(it)
        }
    }

    fun getNetworkType(context: Context): String {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = connectivityManager.activeNetworkInfo
        return when (activeNetwork?.type) {
            ConnectivityManager.TYPE_WIFI -> "WiFi"
            ConnectivityManager.TYPE_MOBILE -> "Mobile data"
            ConnectivityManager.TYPE_ETHERNET -> "Ethernet"
            else -> "Not connected"
        }
    }
}

