package com.rahul.way_myapplication.listeners

interface NetworkStatusChangeListener {
    fun onNetworkStatusChanged(isNetworkAvailable: Boolean)
}