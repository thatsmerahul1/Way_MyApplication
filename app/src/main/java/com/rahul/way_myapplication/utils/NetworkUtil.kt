package com.rahul.way_myapplication.utils

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.widget.Toast
import com.rahul.way_myapplication.R

object NetworkUtil {


//        fun isInternetAvailable(context: Context): Boolean {
//            val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
//
//            if (connectivityManager != null) {
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//                    val network = connectivityManager.activeNetwork
//                    val networkCapabilities = connectivityManager.getNetworkCapabilities(network)
//                    return networkCapabilities != null &&
//                            (networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
//                                    networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ||
//                                    networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET))
//                } else {
//                    val networkInfo = connectivityManager.activeNetworkInfo
//                    return networkInfo != null && networkInfo.isConnected
//                }
//            }
//            return false
//        }

    fun initNetworkReceiver(context: Context, receiver: BroadcastReceiver) {
        context.registerReceiver(receiver, IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))
    }

    fun isNetworkAvailable(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = connectivityManager.activeNetworkInfo
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting
    }

    fun unregisterNetworkReceiver(context: Context,  receiver: BroadcastReceiver) {
        receiver?.let {
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

