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

    //var newOrOld = false

    init {
        //val champ = Champion("base", "Base", "the Base Champion", "https://gitlab.com/lluc.coll.7e4/leaguechamps/-/blob/master/app/src/main/res/drawable-v24/base.png   ", "Lore of the Champion, in this case: Donec molestie velit eu diam vestibulum accumsan. Ut vel eleifend ante. Aliquam imperdiet dolor lorem, et dignissim elit vehicula vel. Donec cursus ex tincidunt rutrum pellentesque. Cras sed ligula placerat, faucibus sapien quis, ultrices lacus. Donec vitae laoreet ante. Curabitur tempor libero in metus porttitor facilisis. Nunc tempor sit amet mi vitae mattis. Donec mattis varius interdum. In non volutpat enim. Sed sed lacinia sem. Donec pretium ligula nec eros feugiat luctus. Duis iaculis dignissim nisl a finibus.", listOf("Tank", "Mage"), Random.nextInt(10), Random.nextInt(10), Random.nextInt(10), Random.nextInt(10), Random.nextBoolean(), null, null)
        //champList.add(champ)

        loadChamps()
        loadItems()
    }


    fun loadChamps(){
        val requestCall = APIService.create()
        val call = requestCall.getChampions()

        call!!.enqueue(object : Callback<ChampionListData> {
            override fun onResponse(call: Call<ChampionListData>, response: Response<ChampionListData>) {
                if (response.isSuccessful){
                    val champ = response.body()
                    val prova = champ!!.data!!.keys.toTypedArray()
                    for (i in 0..champ!!.data!!.size-1){
                        val cData = champ.data!![prova[i]]
                        val newChamp = Champion(cData!!.id!!, cData.name!!, cData.title!!, imageIcon(cData.image!!.full!!), cData.blurb!!,
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
        val call = requestCall.getChampion(idChamp)

        call!!.enqueue(object : Callback<ChampionListData> {
            override fun onResponse(call: Call<ChampionListData>, response: Response<ChampionListData>) {
                if (response.isSuccessful){
                    val champ = response.body()
                    val prova = champ!!.data!!.keys.toTypedArray()


                    for (i in 0..champ!!.data!!.size-1){
                        val cData = champ.data!![prova[i]]
                        val newSpells = Spells(idChamp, cData!!.name!!,
                            imagePassive(cData!!.passive!!.image!!.full!!), cData.passive!!.name!!, cData.passive!!.description!!,
                            imageSpell(cData.spells!![0].image!!.full!!), cData.spells!![0].name!!, cData.spells!![0].description!!,
                            imageSpell(cData.spells!![1].image!!.full!!), cData.spells!![1].name!!, cData.spells!![1].description!!,
                            imageSpell(cData.spells!![2].image!!.full!!), cData.spells!![2].name!!, cData.spells!![2].description!!,
                            imageSpell(cData.spells!![3].image!!.full!!), cData.spells!![3].name!!, cData.spells!![3].description!!,
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
        val call = requestCall.getItems()

        call!!.enqueue(object : Callback<ItemList> {
            override fun onResponse(call: Call<ItemList>, response: Response<ItemList>) {
                if (response.isSuccessful){
                    val item = response.body()
                    val prova = item!!.data!!.keys.toTypedArray()
                    for (i in 0..item!!.data!!.size-1){
                        val cData = item.data!![prova[i]]
                        val newItem = Item(imageItem(cData!!.image!!.full!!), cData.name!!, cData.plaintext!!, cData.gold!!.total!!)

                        itemList.add(newItem)
                    }
                }
            }
            override fun onFailure(call: Call<ItemList>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }


    fun imageIcon(name: String): String{ return "https://ddragon.leagueoflegends.com/cdn/11.23.1/img/champion/$name" }
    fun imageSpell(name: String): String{ return "https://ddragon.leagueoflegends.com/cdn/11.23.1/img/spell/$name" }
    fun imagePassive(name: String): String{ return "https://ddragon.leagueoflegends.com/cdn/11.23.1/img/passive/$name" }
    fun imageSkin(name: Any, num: Int): String{ return "https://ddragon.leagueoflegends.com/cdn/img/champion/loading/${name}_${num}.jpg" }
    fun imageSkinLand(name: Any, num: Int): String{ return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/${name}_${num}.jpg" }
    fun imageItem(name: String): String{ return "https://ddragon.leagueoflegends.com/cdn/11.23.1/img/item/$name"}

    fun jsonError(desc: String): String{
        var dinsError = false
        val frase = desc.toCharArray()
        var final = ""
        for(i in 0..desc.length-1){
            if(frase[i].equals("<")){
                dinsError = true
            }
            if (!dinsError){
                final = final.plus(frase[i])
            }
            else if(dinsError && frase[i].equals(">")){
                dinsError = false
            }

        }
        return final
    }

    fun getChamps() = champList
    fun getItems() = itemList

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