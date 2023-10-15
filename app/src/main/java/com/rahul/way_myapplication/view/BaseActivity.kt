package com.rahul.way_myapplication.view

import android.content.BroadcastReceiver
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.rahul.way_myapplication.R
import com.rahul.way_myapplication.listeners.NetworkStatusChangeListener
import com.rahul.way_myapplication.receivers.NetworkReceiver
import com.rahul.way_myapplication.utils.NetworkUtil

open class BaseActivity: AppCompatActivity(), NetworkStatusChangeListener {
    private lateinit var networkReceiver: BroadcastReceiver
    override fun onStart() {
        super.onStart()
        networkReceiver = NetworkReceiver(this)
        NetworkUtil.initNetworkReceiver(this, networkReceiver)
    }

    override fun onStop() {
        NetworkUtil.unregisterNetworkReceiver(this, networkReceiver)
        super.onStop()
    }

    override fun onNetworkStatusChanged(isNetworkAvailable: Boolean) {
        val networkStatusText = if (isNetworkAvailable) getString(R.string.network_available_now) else getString(R.string.network_not_available)
        showToast(networkStatusText)
    }

    open fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
