package com.example.leaguechamps

import android.graphics.drawable.Drawable
import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.leaguechamps.dataclass.ChampionListData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.InputStream
import java.net.URL
import kotlin.random.Random


class ChampViewModel: ViewModel() {
    var champList = mutableListOf<Champion>()
    var ft = true

    //var newOrOld = false

    init {
        //val champ = Champion("base", "Base", "the Base Champion", "https://gitlab.com/lluc.coll.7e4/leaguechamps/-/blob/master/app/src/main/res/drawable-v24/base.png   ", "Lore of the Champion, in this case: Donec molestie velit eu diam vestibulum accumsan. Ut vel eleifend ante. Aliquam imperdiet dolor lorem, et dignissim elit vehicula vel. Donec cursus ex tincidunt rutrum pellentesque. Cras sed ligula placerat, faucibus sapien quis, ultrices lacus. Donec vitae laoreet ante. Curabitur tempor libero in metus porttitor facilisis. Nunc tempor sit amet mi vitae mattis. Donec mattis varius interdum. In non volutpat enim. Sed sed lacinia sem. Donec pretium ligula nec eros feugiat luctus. Duis iaculis dignissim nisl a finibus.", listOf("Tank", "Mage"), Random.nextInt(10), Random.nextInt(10), Random.nextInt(10), Random.nextInt(10), Random.nextBoolean(), null, null)
        //champList.add(champ)

        loadChamps()
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
                        val pr: String = prova[i]
                        val cData = champ.data!![pr]
                        Log.d("cacaca", "${cData!!.id} , ${cData!!.name}, ${cData!!.nameKey}")
                        val image = "".plus("https://ddragon.leagueoflegends.com/cdn/11.23.1/img/champion/".plus(cData.image!!.full))
                        val newChamp = Champion(cData.id, cData.name, cData.title, image, cData.blurb,
                            cData.tags, cData.info!!.attack, cData.info!!.defense, cData.info!!.magic, cData.info!!.difficulty, false, null, null)

                        champList.add(newChamp)
                    }
                    Log.d("meh", "asdsad")
                }
            }
            override fun onFailure(call: Call<ChampionListData>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
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