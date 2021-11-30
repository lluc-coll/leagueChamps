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


class ChampionFragment: Fragment(R.layout.champion_fragment) {
    lateinit var goBack: ImageButton
    lateinit var favIcon: ImageButton
    lateinit var champIcon: ImageView
    lateinit var champName: TextView
    lateinit var champTitle: TextView
    lateinit var champLore: TextView
    lateinit var champTags: TextView
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
    private val viewModel: ChampViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        goBack = view.findViewById(R.id.goBack)
        favIcon = view.findViewById(R.id.favIcon)
        champIcon = view.findViewById(R.id.icon)
        champName = view.findViewById(R.id.name)
        champTitle = view.findViewById(R.id.title)
        champLore = view.findViewById(R.id.lore)
        champTags = view.findViewById(R.id.tags)
        attackBar = view.findViewById(R.id.attack)
        attackValue = view.findViewById(R.id.attackValue)
        defenceBar = view.findViewById(R.id.defense)
        defenceValue = view.findViewById(R.id.defenceValue)
        magicBar = view.findViewById(R.id.magic)
        magicValue = view.findViewById(R.id.defenceValue)
        difficultyBar = view.findViewById(R.id.difficulty)
        difficultyValue = view.findViewById(R.id.difficultyValue)
        skinsButton = view.findViewById(R.id.skins)
        spellsButton = view.findViewById(R.id.spells)

        val position = arguments?.getInt("position")
        val champ = viewModel.getOneList(position!!)

        champLore.movementMethod = ScrollingMovementMethod()

        champIcon.setImageResource(champ.iconUrl)
        champName.text = champ.name
        champTitle.text = champ.title
        champLore.text = champ.lore
        val separator = ", "
        val tags = champ.tags.joinToString(separator)
        champTags.text = tags
        attackBar.progress = champ.attack
        attackValue.text = "".plus(champ.attack).plus("/10")
        defenceBar.progress = champ.defence
        defenceValue.text = "".plus(champ.defence).plus("/10")
        magicBar.progress = champ.magic
        magicValue.text = "".plus(champ.magic).plus("/10")
        difficultyBar.progress = champ.difficulty
        difficultyValue.text = "".plus(champ.difficulty).plus("/10")


        goBack.setOnClickListener{
            val action = ChampionFragmentDirections.actionChampionFragmentToListOfChampsFragment()
            findNavController().navigate(action)
        }

        spellsButton.setOnClickListener{
            //val spells = Spells(1, "d", "d", 1, "d", "d", 1, "d", "d", 1, "d", "d", 1, "d", "d")
            val action = ChampionFragmentDirections.actionChampionFragmentToSpellsFragment(position)
            findNavController().navigate(action)
        }

        skinsButton.setOnClickListener{
            val action = ChampionFragmentDirections.actionChampionFragmentToSkinFragment()
            findNavController().navigate(action)
        }
    }

}