package com.example.leaguechamps

import androidx.lifecycle.ViewModel
import kotlin.random.Random

class ChampViewModel: ViewModel() {
    private var champList = mutableListOf<Champion>()

    //var newOrOld = false

    init {
        for (i in 1..50){
            val tagsList = mutableListOf<String>("Fighter", "Tank")
            val champ = Champion("aatrox", "Aatrox $i", "the Darkin Blade", (R.drawable.base), "Once honored defenders of Shurima against the Void, Aatrox and his brethren would eventually become an even greater threat to Runeterra, and were defeated only by cunning mortal sorcery. But after centuries of imprisonment, Aatrox was the first to find freedom once more, corrupting and transforming those foolish enough to try and wield the magical weapon that contained his essence. Now, with stolen flesh, he walks Runeterra in a brutal approximation of his previous form, seeking an apocalyptic and long overdue vengeance.", tagsList, 8, 4, 3, 4)
            champList.add(champ)
        }

    }

    fun getLists() = champList

    fun getOneList(position: Int) = champList[position]
}