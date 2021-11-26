package com.example.leaguechamps

import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels

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

}