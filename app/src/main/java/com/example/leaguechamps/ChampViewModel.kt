package com.example.leaguechamps

import androidx.lifecycle.ViewModel
import kotlin.random.Random

class ChampViewModel: ViewModel() {
    private var champList = mutableListOf<Champion>()

    //var newOrOld = false

    init {
        for (i in 1..20){
            val tagsList = mutableListOf<String>("prva", "asdasd")
            val champ = Champion("aatrox", "Aatrox $i", "asdeje", (R.drawable.base), "loreeeeeeeeeeeeeeeeeeeeee", tagsList, 3, 4, 5, 6)
            champList.add(champ)
        }

    }

    fun getLists() = champList

    fun getOneList(position: Int) = champList[position]
}