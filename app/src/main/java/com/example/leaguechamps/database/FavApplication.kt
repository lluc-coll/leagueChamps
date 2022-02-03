package com.example.leaguechamps.database

import android.app.Application
import androidx.room.Room

class FavApplication : Application() {
    companion object {
        lateinit var database: FavDatabase
    }

    override fun onCreate() {
        super.onCreate()
        database = Room.databaseBuilder(this, FavDatabase::class.java,"FavDatabase").build()
    }
}
