package com.example.leaguechamps.ui.viewModel

import android.text.Html
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.leaguechamps.data.repository.Repository
import com.example.leaguechamps.database.FavApplication
import com.example.leaguechamps.database.entities.FavEntity
import com.example.leaguechamps.ui.models.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class LeagueViewModel: ViewModel() {
    var champList = mutableListOf<Champion>()
    var mutableChampList = MutableLiveData<List<Champion>>()
    var champExtras = mutableListOf<ChampExtra>()
    var mutableChampExtras = MutableLiveData<List<ChampExtra>>()
    var itemList = mutableListOf<Item>()
    var mutableItemList = MutableLiveData<List<Item>>()
    var versionList = arrayListOf("")
    var mutableVersionList = MutableLiveData<List<String>>()
    var languageList = arrayListOf("")
    var mutableLanguageList = MutableLiveData<List<String>>()
    var version = "12.1.1"
    var language = "en_US"
    var favs = false
    var searching = false
    var toSearch = ""


    init {
        loadVersions()
        loadLanguages()

    }


    fun loadChamps() {
        champList.removeAll(champList)

        //mutableVersionList.observe(viewLifecycleOwner, {
        viewModelScope.launch {
            val response = withContext(Dispatchers.IO) { Repository().getChamps(version, language) }

            if (response.isSuccessful){
                val champ = response.body()
                val prova = champ!!.data!!.keys.toTypedArray()
                for (i in 0..champ.data!!.size - 1) {
                    val cData = champ.data!![prova[i]]
                    val newChamp = Champion(
                        i,
                        cData!!.id!!,
                        cData.name!!,
                        cData.title!!,
                        imageIcon(version, cData.image!!.full!!),
                        cData.blurb!!,
                        cData.tags!!,
                        cData.info!!.attack!!,
                        cData.info!!.defense!!,
                        cData.info!!.magic!!,
                        cData.info!!.difficulty!!,
                        false
                    )

                    champList.add(newChamp)
                }
            }

            mutableChampList.postValue(champList)
            getFavs()
        }
    }


    fun extendedChamp(idChamp: String, position: Int) {
        champExtras.removeAll(champExtras)

        viewModelScope.launch {
            val response = withContext(Dispatchers.IO) { Repository().getChamp(version, language, idChamp) }

            if (response.isSuccessful) {
                val champ = response.body()
                val prova = champ!!.data!!.keys.toTypedArray()

                champList.get(position).lore = champ.data!![prova[0]]!!.lore!!
                mutableChampList.postValue(champList)


                for (i in 0..champ.data!!.size - 1) {
                    val cData = champ.data!![prova[i]]
                    val newSpells = Spells(
                        idChamp,
                        cData!!.name!!,
                        imagePassive(version, cData.passive!!.image!!.full!!),
                        cData.passive!!.name!!,
                        parse(cData.passive!!.description!!),
                        imageSpell(version, cData.spells!![0].image!!.full!!),
                        cData.spells!![0].name!!,
                        parse(cData.spells!![0].description!!),
                        imageSpell(version, cData.spells!![1].image!!.full!!),
                        cData.spells!![1].name!!,
                        parse(cData.spells!![1].description!!),
                        imageSpell(version, cData.spells!![2].image!!.full!!),
                        cData.spells!![2].name!!,
                        parse(cData.spells!![2].description!!),
                        imageSpell(version, cData.spells!![3].image!!.full!!),
                        cData.spells!![3].name!!,
                        parse(cData.spells!![3].description!!)
                    )


                    var skins = mutableListOf<Skin>(
                        Skin(
                            imageSkin(cData.id!!, cData.skins!![0].num!!),
                            imageSkinLand(cData.id!!, cData.skins!![0].num!!),
                            cData.name!!
                        )
                    )
                    for (i in 1..cData.skins!!.size - 1) {
                        val newSkin = Skin(
                            imageSkin(cData.id!!, cData.skins!![i].num!!),
                            imageSkinLand(cData.id!!, cData.skins!![i].num!!),
                            cData.skins!![i].name!!
                        )
                        skins.add(newSkin)

                    }

                    champExtras.add(ChampExtra(cData.id!!, cData.name, newSpells, skins))
                }
            }
        }
        mutableChampExtras.postValue(champExtras)
    }

    fun loadItems() {
        itemList.removeAll(itemList)

        viewModelScope.launch {
            val response = withContext(Dispatchers.IO) { Repository().getItems(version, language) }
            if (response.isSuccessful) {
                val item = response.body()
                val prova = item!!.data!!.keys.toTypedArray()
                for (i in 0..item.data!!.size - 1) {
                    val cData = item.data!![prova[i]]

                    val newItem = Item(
                        imageItem(version, cData!!.image!!.full!!),
                        cData.name!!,
                        parse(cData.description!!),
                        cData.gold!!.total!!,
                        false
                    )

                    if (newItem.gold != 0) {
                        itemList.add(newItem)
                    }
                }
                itemList.sortBy { it.gold }
            }
            mutableItemList.postValue(itemList)
            getFavs()
        }
    }

    fun loadLanguages(){
        viewModelScope.launch {
            val response = withContext(Dispatchers.IO) { Repository().getLanguages() }
            if (response.isSuccessful) {
                val langs = response.body()!!
                languageList[0] = langs!![0]
                for (i in 1 .. langs.size-1) {
                    languageList.add(langs[i])
                }
            }
            mutableLanguageList.postValue(languageList)
        }
    }

    fun loadVersions(){
        viewModelScope.launch {
            val response = withContext(Dispatchers.IO) { Repository().getVersions() }
            if (response.isSuccessful) {
                val langs = response.body()!!
                versionList[0] = langs!![0]
                for (i in 1 .. langs.size-1) {
                    versionList.add(langs!![i])
                }
            }
            mutableVersionList.postValue(versionList)
            version = versionList[0]
            loadChamps()
            loadItems()
        }
    }


    fun imageIcon(version: String, name: String): String{ return "https://ddragon.leagueoflegends.com/cdn/$version/img/champion/$name" }
    fun imageSpell(version: String, name: String): String{ return "https://ddragon.leagueoflegends.com/cdn/$version/img/spell/$name" }
    fun imagePassive(version: String, name: String): String{ return "https://ddragon.leagueoflegends.com/cdn/$version/img/passive/$name" }
    fun imageSkin(name: Any, num: Int): String{ return "https://ddragon.leagueoflegends.com/cdn/img/champion/loading/${name}_${num}.jpg" }
    fun imageSkinLand(name: Any, num: Int): String{ return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/${name}_${num}.jpg" }
    fun imageItem(version: String, name: String): String{ return "https://ddragon.leagueoflegends.com/cdn/$version/img/item/$name"}

    fun parse (HTML: String): String{
        val decoded: String = Html.fromHtml(HTML, Html.FROM_HTML_MODE_COMPACT).toString()
        return decoded
    }

    fun getFavs(){
        CoroutineScope(Dispatchers.Main).launch {
            withContext(Dispatchers.IO) {
                var favs = FavApplication.database.FavDao().getAllFavs()
                for (i in favs) {
                    if (i.champ == 0) {
                        for (j in champList) {
                            if (j.id == i.id) {
                                j.fav = true
                            }
                        }
                    } else if (i.champ == 1) {
                        for (j in itemList) {
                            if (j.name == i.id) {
                                j.fav = true
                            }
                        }
                    }
                }
            }
        }
    }

    fun addFav(fav: FavEntity){
        CoroutineScope(Dispatchers.Main).launch {
            withContext(Dispatchers.IO) {
                FavApplication.database.FavDao().addFav(fav)
            }
        }
    }

    fun delFav(fav: FavEntity){
        CoroutineScope(Dispatchers.Main).launch {
            withContext(Dispatchers.IO) {
                FavApplication.database.FavDao().deleteFav(fav)
            }
        }
    }

    fun favChamps(): List<Champion>{
        var favChamps = mutableListOf<Champion>()
        for(i in 0..champList.size-1){
            if(champList.get(i).fav){
                favChamps.add(champList.get(i))
            }
        }
        return favChamps
    }

    fun favItems(): List<Item>{
        var favItems = mutableListOf<Item>()
        for(i in 0..itemList.size-1){
            if(itemList.get(i).fav){
                favItems.add(itemList.get(i))
            }
        }
        return favItems
    }

    fun searchChamps(search: String): List<Champion>{
        var searchChamps = mutableListOf<Champion>()

        for (i in champList){
            if(i.name.contains(search, ignoreCase = true)){
                searchChamps.add(i)
            }
        }

        return searchChamps
    }

    fun searchItems(search: String): List<Item>{
        var searchItems = mutableListOf<Item>()

        for (i in itemList){
            if(i.name.contains(search, ignoreCase = true)){
                searchItems.add(i)
            }
        }

        return searchItems
    }

    fun getChamps() = champList
    fun getItems() = itemList

    fun getOneChamp(position: Int) = champList[position]
    fun getOneFavChamp(position: Int) = favChamps()[position]

}