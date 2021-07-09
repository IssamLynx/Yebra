package com.example.projettdm.data.room

import android.app.Application
import android.content.Context
import com.example.projettdm.data.repositories.Pref
import com.example.projettdm.data.repositories.TraitementRepository

class App:Application() {
    override fun onCreate() {
        super.onCreate()
        Pref.sharedPref=getSharedPreferences("name", Context.MODE_PRIVATE)
        RoomService.context=applicationContext
    }
}