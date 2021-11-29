package com.example.leaguechamps

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import android.text.method.ScrollingMovementMethod
import androidx.navigation.fragment.findNavController


class ChampionFragment: Fragment(R.layout.champion_fragment) {
    lateinit var champIcon: ImageView
    lateinit var champName: TextView
    lateinit var champTitle: TextView
    lateinit var champLore: TextView
    lateinit var champTags: TextView
    lateinit var attackBar: ProgressBar
    lateinit var defenceBar: ProgressBar
    lateinit var magicBar: ProgressBar
    lateinit var difficultyBar: ProgressBar
    lateinit var skinsButton: ImageView
    lateinit var spellsButton: ImageView
    private val viewModel: ChampViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        champIcon = view.findViewById(R.id.icon)
        champName = view.findViewById(R.id.name)
        champTitle = view.findViewById(R.id.title)
        champLore = view.findViewById(R.id.lore)
        champTags = view.findViewById(R.id.tags)
        attackBar = view.findViewById(R.id.attack)
        defenceBar = view.findViewById(R.id.defense)
        magicBar = view.findViewById(R.id.magic)
        difficultyBar = view.findViewById(R.id.difficulty)
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
        defenceBar.progress = champ.defence
        magicBar.progress = champ.magic
        difficultyBar.progress = champ.difficulty


        spellsButton.setOnClickListener{
            //val spells = Spells(1, "d", "d", 1, "d", "d", 1, "d", "d", 1, "d", "d", 1, "d", "d")
            val action = ChampionFragmentDirections.actionChampionFragmentToSpellsFragment()
            findNavController().navigate(action)
        }
    }

}