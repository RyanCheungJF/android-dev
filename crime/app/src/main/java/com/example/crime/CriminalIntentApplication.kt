package com.example.crime

import android.app.Application

class CriminalIntentApplication: Application() {

    // sets this to be entry point in manifest.xml
    // even on configuration change, this does not trigger again, so only triggered once
    override fun onCreate() {
        super.onCreate()
        CrimeRepository.initialize(this)
    }
}
