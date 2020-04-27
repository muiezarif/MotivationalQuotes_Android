package com.muiezarif.motivationalquotes

import androidx.multidex.MultiDexApplication
import com.google.android.gms.ads.MobileAds

class MyApplication:MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()
        MobileAds.initialize(applicationContext)
    }
}