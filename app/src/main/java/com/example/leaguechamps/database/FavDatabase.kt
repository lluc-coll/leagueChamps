package com.example.leaguechamps.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.leaguechamps.database.entities.FavEntity
import com.example.leaguechamps.database.daos.FavDAO

@Database(entities = arrayOf(FavEntity::class), version = 1)
abstract class FavDatabase: RoomDatabase() {
    abstract fun FavDao(): FavDAO
}