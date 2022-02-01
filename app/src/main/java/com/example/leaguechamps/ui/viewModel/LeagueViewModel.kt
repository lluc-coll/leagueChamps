package com.example.leaguechamps.ui.viewModel

import android.text.Html
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.leaguechamps.data.api.APIService
import com.example.leaguechamps.data.dataclass.ChampionListData
import com.example.leaguechamps.data.dataclass.ItemList
import com.example.leaguechamps.data.repository.Repository
import com.example.leaguechamps.ui.models.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LeagueViewModel: ViewModel() {
    var champList = mutableListOf<Champion>()
    var mutableChampList = MutableLiveData<List<Champion>>()
    var champExtras = mutableListOf<ChampExtra>()
    var mutableChampExtras = MutableLiveData<List<ChampExtra>>()
    var itemList = mutableListOf<Item>()
    var mutableItemList = MutableLiveData<List<Item>>()
    var ft = true
    var version = "12.2.1"
    var language = "en_US"
    var favs = false
    var searching = false
    var toSearch = ""


    init {
        //val champ = Champion("base", "Base", "the Base Champion", "https://gitlab.com/lluc.coll.7e4/leaguechamps/-/blob/master/app/src/main/res/drawable-v24/base.png   ", "Lore of the Champion, in this case: Donec molestie velit eu diam vestibulum accumsan. Ut vel eleifend ante. Aliquam imperdiet dolor lorem, et dignissim elit vehicula vel. Donec cursus ex tincidunt rutrum pellentesque. Cras sed ligula placerat, faucibus sapien quis, ultrices lacus. Donec vitae laoreet ante. Curabitur tempor libero in metus porttitor facilisis. Nunc tempor sit amet mi vitae mattis. Donec mattis varius interdum. In non volutpat enim. Sed sed lacinia sem. Donec pretium ligula nec eros feugiat luctus. Duis iaculis dignissim nisl a finibus.", listOf("Tank", "Mage"), Random.nextInt(10), Random.nextInt(10), Random.nextInt(10), Random.nextInt(10), Random.nextBoolean(), null, null)
        //champList.add(champ)

        loadChamps()
        //loadItems()
    }


    fun loadChamps() {
        champList.removeAll(champList)

        viewModelScope.launch {
            val response = withContext(Dispatchers.IO) {
                Repository().getChamps(version, language)
            }
            Log.d("meh", "$response")
        }

        /*val requestCall = APIService.create()
        val call = requestCall.getChampions(version, language)

        call!!.enqueue(object : Callback<ChampionListData> {
            override fun onResponse(
                call: Call<ChampionListData>,
                response: Response<ChampionListData>
            ) {
                if (response.isSuccessful) {
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
            }

            override fun onFailure(call: Call<ChampionListData>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })*/
    }


    fun extendedChamp(idChamp: String, position: Int) {
        champExtras.removeAll(champExtras)
        val requestCall = APIService.create()
        val call = requestCall.getChampion(version, language, idChamp)

        call!!.enqueue(object : Callback<ChampionListData> {
            override fun onResponse(
                call: Call<ChampionListData>,
                response: Response<ChampionListData>
            ) {
                if (response.isSuccessful) {
                    val champ = response.body()
                    val prova = champ!!.data!!.keys.toTypedArray()

                    champList.get(position).lore = champ.data!![prova[0]]!!.lore!!


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

            override fun onFailure(call: Call<ChampionListData>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }

    fun loadItems() {
        itemList.removeAll(itemList)
        val requestCall = APIService.create()
        val call = requestCall.getItems(version, language)

        call!!.enqueue(object : Callback<ItemList> {
            override fun onResponse(call: Call<ItemList>, response: Response<ItemList>) {
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
            }

            override fun onFailure(call: Call<ItemList>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }

    fun loadLanguages(): ArrayList<String> {
        var languages = arrayListOf("")
        val requestCall = APIService.create()
        val call = requestCall.getLanguages()

        call!!.enqueue(object : Callback<List<String>> {
            override fun onResponse(call: Call<List<String>>, response: Response<List<String>>) {
                if (response.isSuccessful) {
                    val langs = response.body()
                    languages[0] = langs!![0]
                    for (i in 1 .. langs!!.size-1) {
                        languages.add(langs[i])
                    }
                }
            }

            override fun onFailure(call: Call<List<String>>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
        return languages
    }

    fun loadVersions(): ArrayList<String>{
        var versions = arrayListOf("")
        val requestCall = APIService.create()
        val call = requestCall.getVersions()

        call!!.enqueue(object : Callback<List<String>> {
            override fun onResponse(call: Call<List<String>>, response: Response<List<String>>) {
                if (response.isSuccessful) {
                    val langs = response.body()
                    versions[0] = langs!![0]
                    for (i in 1 .. langs.size-1) {
                        versions.add(langs[i])
                    }
                }
            }

            override fun onFailure(call: Call<List<String>>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
        return versions
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