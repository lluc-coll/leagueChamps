package com.example.leaguechamps

import android.graphics.drawable.Drawable
import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.leaguechamps.dataclass.ChampionListData
import com.example.leaguechamps.dataclass.ItemList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.InputStream
import java.net.URL
import kotlin.random.Random


class LeagueViewModel: ViewModel() {
    var champList = mutableListOf<Champion>()
    var champExtras = mutableListOf<ChampExtra>()
    var itemList = mutableListOf<Item>()
    var ft = true
    var version = "11.24.1"
    var language = "en_US"
    var favs = false

    //var newOrOld = false

    init {
        //val champ = Champion("base", "Base", "the Base Champion", "https://gitlab.com/lluc.coll.7e4/leaguechamps/-/blob/master/app/src/main/res/drawable-v24/base.png   ", "Lore of the Champion, in this case: Donec molestie velit eu diam vestibulum accumsan. Ut vel eleifend ante. Aliquam imperdiet dolor lorem, et dignissim elit vehicula vel. Donec cursus ex tincidunt rutrum pellentesque. Cras sed ligula placerat, faucibus sapien quis, ultrices lacus. Donec vitae laoreet ante. Curabitur tempor libero in metus porttitor facilisis. Nunc tempor sit amet mi vitae mattis. Donec mattis varius interdum. In non volutpat enim. Sed sed lacinia sem. Donec pretium ligula nec eros feugiat luctus. Duis iaculis dignissim nisl a finibus.", listOf("Tank", "Mage"), Random.nextInt(10), Random.nextInt(10), Random.nextInt(10), Random.nextInt(10), Random.nextBoolean(), null, null)
        //champList.add(champ)

        loadChamps()
        loadItems()
    }


    fun loadChamps(){
        val requestCall = APIService.create()
        val call = requestCall.getChampions(version, language)

        call!!.enqueue(object : Callback<ChampionListData> {
            override fun onResponse(call: Call<ChampionListData>, response: Response<ChampionListData>) {
                if (response.isSuccessful){
                    val champ = response.body()
                    val prova = champ!!.data!!.keys.toTypedArray()
                    for (i in 0..champ.data!!.size-1){
                        val cData = champ.data!![prova[i]]
                        val newChamp = Champion(i, cData!!.id!!, cData.name!!, cData.title!!, imageIcon(version, cData.image!!.full!!), cData.blurb!!,
                            cData.tags!!, cData.info!!.attack!!, cData.info!!.defense!!, cData.info!!.magic!!, cData.info!!.difficulty!!, false)

                        champList.add(newChamp)
                    }
                }
            }
            override fun onFailure(call: Call<ChampionListData>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }


    fun extendedChamp(idChamp: String, position: Int){
        val requestCall = APIService.create()
        val call = requestCall.getChampion(version, language, idChamp)

        call!!.enqueue(object : Callback<ChampionListData> {
            override fun onResponse(call: Call<ChampionListData>, response: Response<ChampionListData>) {
                if (response.isSuccessful){
                    val champ = response.body()
                    val prova = champ!!.data!!.keys.toTypedArray()

                    champList.get(position).lore = champ.data!![prova[0]]!!.lore!!


                    for (i in 0..champ.data!!.size-1){
                        val cData = champ.data!![prova[i]]
                        val newSpells = Spells(idChamp, cData!!.name!!,
                            imagePassive(version, cData.passive!!.image!!.full!!), cData.passive!!.name!!, cData.passive!!.description!!,
                            imageSpell(version, cData.spells!![0].image!!.full!!), cData.spells!![0].name!!, cData.spells!![0].description!!,
                            imageSpell(version, cData.spells!![1].image!!.full!!), cData.spells!![1].name!!, cData.spells!![1].description!!,
                            imageSpell(version, cData.spells!![2].image!!.full!!), cData.spells!![2].name!!, cData.spells!![2].description!!,
                            imageSpell(version, cData.spells!![3].image!!.full!!), cData.spells!![3].name!!, cData.spells!![3].description!!,
                        )


                        var skins = mutableListOf<Skin>(Skin(imageSkin(cData.id!!, cData.skins!![0].num!!), imageSkinLand(cData.id!!, cData.skins!![0].num!!), cData.name!!))
                        for (i in 1..cData.skins!!.size-1){
                            val newSkin = Skin(imageSkin(cData.id!!, cData.skins!![i].num!!),
                                imageSkinLand(cData.id!!, cData.skins!![i].num!!),
                                cData.skins!![i].name!!)
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

    fun loadItems(){
        val requestCall = APIService.create()
        val call = requestCall.getItems(version, language)

        call!!.enqueue(object : Callback<ItemList> {
            override fun onResponse(call: Call<ItemList>, response: Response<ItemList>) {
                if (response.isSuccessful){
                    val item = response.body()
                    val prova = item!!.data!!.keys.toTypedArray()
                    for (i in 0..item.data!!.size-1){
                        val cData = item.data!![prova[i]]
                        var text = cData!!.plaintext
                        if (cData.plaintext.equals("")){
                            text = cData.description
                        }
                        val newItem = Item(imageItem(version, cData.image!!.full!!), cData.name!!, text!!, cData.gold!!.total!!, false)

                        if(newItem.gold != 0) {
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


    fun imageIcon(version: String, name: String): String{ return "https://ddragon.leagueoflegends.com/cdn/$version/img/champion/$name" }
    fun imageSpell(version: String, name: String): String{ return "https://ddragon.leagueoflegends.com/cdn/$version/img/spell/$name" }
    fun imagePassive(version: String, name: String): String{ return "https://ddragon.leagueoflegends.com/cdn/$version/img/passive/$name" }
    fun imageSkin(name: Any, num: Int): String{ return "https://ddragon.leagueoflegends.com/cdn/img/champion/loading/${name}_${num}.jpg" }
    fun imageSkinLand(name: Any, num: Int): String{ return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/${name}_${num}.jpg" }
    fun imageItem(version: String, name: String): String{ return "https://ddragon.leagueoflegends.com/cdn/$version/img/item/$name"}

    fun favChamps(): List<Champion>{
        var favChamps = mutableListOf<Champion>()
        for(i in 0..champList.size-1){
            if(champList.get(i).fav){
                favChamps.add(champList.get(i))
            }
        }
        return favChamps
    }

    fun getChamps() = champList
    fun getItems() = itemList

    fun getOneChamp(position: Int) = champList[position]
    fun getOneFavChamp(position: Int) = favChamps()[position]

}