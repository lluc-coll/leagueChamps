package com.example.leaguechamps.database.daos

import androidx.room.*
import com.example.leaguechamps.database.entities.FavEntity

@Dao
interface FavDAO {
    @Query("SELECT * FROM FavEntity")
    fun getAllFavs(): MutableList<FavEntity>

    @Insert
    fun addFav(fav: FavEntity)

    @Delete
    fun deleteFav(fav: FavEntity)
}
