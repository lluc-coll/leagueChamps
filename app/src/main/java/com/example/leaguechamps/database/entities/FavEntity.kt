package com.example.leaguechamps.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class FavEntity (@PrimaryKey var id: String,
                      var champ: Int)