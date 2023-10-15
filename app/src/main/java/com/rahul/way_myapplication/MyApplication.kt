package com.rahul.way_myapplication

import android.app.Application

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
//        startKoin{
//            androidContext(this@MyApplication)
//            modules(appModule)
//        }
    }
}
