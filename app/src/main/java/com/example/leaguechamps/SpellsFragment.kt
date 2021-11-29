package com.example.leaguechamps

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels

class SpellsFragment: Fragment(R.layout.spells_fragment)  {
    lateinit var title: TextView

    lateinit var PIcon: ImageView
    lateinit var PName: TextView
    lateinit var PDesc: TextView

    lateinit var QIcon: ImageView
    lateinit var QName: TextView
    lateinit var QDesc: TextView

    lateinit var WIcon: ImageView
    lateinit var WName: TextView
    lateinit var WDesc: TextView

    lateinit var EIcon: ImageView
    lateinit var EName: TextView
    lateinit var EDesc: TextView

    lateinit var RIcon: ImageView
    lateinit var RName: TextView
    lateinit var RDesc: TextView

    private val viewModel: ChampViewModel by activityViewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        title = view.findViewById(R.id.title)
        PIcon = view.findViewById(R.id.passive)
        PName = view.findViewById(R.id.passiveName)
        PDesc = view.findViewById(R.id.passiveDesc)
        QIcon = view.findViewById(R.id.qSpell)
        QName = view.findViewById(R.id.qSpellName)
        QDesc = view.findViewById(R.id.qSpellDesc)
        WIcon = view.findViewById(R.id.wSpell)
        WName = view.findViewById(R.id.wSpellName)
        WDesc = view.findViewById(R.id.wSpellDesc)
        EIcon = view.findViewById(R.id.eSpell)
        EName = view.findViewById(R.id.eSpellName)
        EDesc = view.findViewById(R.id.eSpellDesc)
        RIcon = view.findViewById(R.id.rSpell)
        RName = view.findViewById(R.id.rSpellName)
        RDesc = view.findViewById(R.id.rSpellDesc)

        PDesc.movementMethod = ScrollingMovementMethod()
        QDesc.movementMethod = ScrollingMovementMethod()
        WDesc.movementMethod = ScrollingMovementMethod()
        EDesc.movementMethod = ScrollingMovementMethod()
        RDesc.movementMethod = ScrollingMovementMethod()
    }
}