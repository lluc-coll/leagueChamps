package com.example.leaguechamps

import android.graphics.drawable.Drawable
import androidx.lifecycle.ViewModel
import java.io.InputStream
import java.net.URL
import kotlin.random.Random


class ChampViewModel: ViewModel() {
    var champList = mutableListOf<Champion>()
    private var icons = mutableListOf<String>("Aatrox", "Varus", "Senna", "Mordekaiser")
    val tags = mutableListOf<String>("Fighter", "Tank", "Support", "Assassin", "mage", "Marksman")

    //var newOrOld = false

    init {
        for (i in 1..50){
            val j = Random.nextInt(4)
            val tagsList = mutableListOf<String>(tags.get(Random.nextInt(6)), tags.get(Random.nextInt(6)))
            val icon = "".plus("https://ddragon.leagueoflegends.com/cdn/11.23.1/img/champion/".plus(icons.get(j))).plus(".png")
            val champ = Champion("aatrox", icons.get(j), "the Darkin Blade", icon, "Once honored defenders of Shurima against the Void, Aatrox and his brethren would eventually become an even greater threat to Runeterra, and were defeated only by cunning mortal sorcery. But after centuries of imprisonment, Aatrox was the first to find freedom once more, corrupting and transforming those foolish enough to try and wield the magical weapon that contained his essence. Now, with stolen flesh, he walks Runeterra in a brutal approximation of his previous form, seeking an apocalyptic and long overdue vengeance.", tagsList, Random.nextInt(10), Random.nextInt(10), Random.nextInt(10), Random.nextInt(10), Random.nextBoolean(), null, null)
            champList.add(champ)
        }

    }

    fun getLists() = champList

    fun getOneList(position: Int) = champList[position]

    fun urlToDrawable(url: String?): Drawable? {
        return try {
            val `is`: InputStream = URL(url).getContent() as InputStream
            Drawable.createFromStream(`is`, "src name")
        } catch (e: Exception) {
            null
        }
    }
}