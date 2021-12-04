package com.example.leaguechamps

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import android.text.method.ScrollingMovementMethod
import android.widget.ImageButton
import androidx.navigation.fragment.findNavController
import com.squareup.picasso.Picasso


class ChampionFragment: Fragment(R.layout.champion_fragment) {
    lateinit var goBack: ImageButton
    lateinit var favIcon: ImageButton
    lateinit var champIcon: ImageView
    lateinit var champName: TextView
    lateinit var champTitle: TextView
    lateinit var champLore: TextView
    lateinit var champTag1: ImageView
    lateinit var champTag2: ImageView
    lateinit var attackBar: ProgressBar
    lateinit var attackValue: TextView
    lateinit var defenceBar: ProgressBar
    lateinit var defenceValue: TextView
    lateinit var magicBar: ProgressBar
    lateinit var magicValue: TextView
    lateinit var difficultyBar: ProgressBar
    lateinit var difficultyValue: TextView
    lateinit var skinsButton: ImageButton
    lateinit var spellsButton: ImageButton
    private val viewModel: LeagueViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        goBack = view.findViewById(R.id.goBack)
        favIcon = view.findViewById(R.id.favIcon)
        champIcon = view.findViewById(R.id.icon)
        champName = view.findViewById(R.id.name)
        champTitle = view.findViewById(R.id.title)
        champLore = view.findViewById(R.id.lore)
        champTag1 = view.findViewById(R.id.tag1)
        champTag2 = view.findViewById(R.id.tag2)
        attackBar = view.findViewById(R.id.attack)
        attackValue = view.findViewById(R.id.attackValue)
        defenceBar = view.findViewById(R.id.defense)
        defenceValue = view.findViewById(R.id.defenceValue)
        magicBar = view.findViewById(R.id.magic)
        magicValue = view.findViewById(R.id.magicValue)
        difficultyBar = view.findViewById(R.id.difficulty)
        difficultyValue = view.findViewById(R.id.difficultyValue)
        skinsButton = view.findViewById(R.id.skins)
        spellsButton = view.findViewById(R.id.spells)

        val position = arguments?.getInt("position")
        val champ = viewModel.getOneList(position!!)

        champLore.movementMethod = ScrollingMovementMethod()

        if(champ.fav){
            favIcon.setImageResource(R.drawable.fav_true)
        }

        Picasso.get().load(champ.iconUrl).into(champIcon);
        champName.text = champ.name
        champTitle.text = champ.title
        champLore.text = champ.lore

        val tag1 = "".plus("https://raw.communitydragon.org/10.1/plugins/rcp-fe-lol-hover-card/global/default/roleicon-").plus(champ.tags!!.get(0).lowercase()).plus(".png")
        Picasso.get().load(tag1).into(champTag1);
        if(champ.tags.size == 2){
            val tag1 = "".plus("https://raw.communitydragon.org/10.1/plugins/rcp-fe-lol-hover-card/global/default/roleicon-").plus(champ.tags!!.get(1).lowercase()).plus(".png")
            Picasso.get().load(tag1).into(champTag2);
        }
        attackBar.progress = champ.attack!!
        attackValue.text = "".plus(champ.attack).plus("/10")
        defenceBar.progress = champ.defence!!
        defenceValue.text = "".plus(champ.defence).plus("/10")
        magicBar.progress = champ.magic!!
        magicValue.text = "".plus(champ.magic).plus("/10")
        difficultyBar.progress = champ.difficulty!!
        difficultyValue.text = "".plus(champ.difficulty).plus("/10")


        goBack.setOnClickListener{
            val action = ChampionFragmentDirections.actionChampionFragmentToListOfChampsFragment()
            findNavController().navigate(action)
        }

        favIcon.setOnClickListener{
            if(champ.fav){
                viewModel.champList.get(position).fav = false
                favIcon.setImageResource(R.drawable.fav_false)
            }
            else{
                viewModel.champList.get(position).fav = true
                favIcon.setImageResource(R.drawable.fav_true)
            }
        }

        spellsButton.setOnClickListener{
            val action = ChampionFragmentDirections.actionChampionFragmentToSpellsFragment(position)
            findNavController().navigate(action)
        }

        skinsButton.setOnClickListener{
            val action = ChampionFragmentDirections.actionChampionFragmentToSkinFragment(position)
            findNavController().navigate(action)
        }
    }
}
