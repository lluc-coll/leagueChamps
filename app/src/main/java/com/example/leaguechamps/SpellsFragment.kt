package com.example.leaguechamps

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.squareup.picasso.Picasso

class SpellsFragment: Fragment(R.layout.spells_fragment)  {
    lateinit var goBack: ImageButton
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

        goBack = view.findViewById(R.id.goBack)
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

        val position = arguments?.getInt("position")
        val champ = viewModel.getOneList(position!!)

        PDesc.movementMethod = ScrollingMovementMethod()
        QDesc.movementMethod = ScrollingMovementMethod()
        WDesc.movementMethod = ScrollingMovementMethod()
        EDesc.movementMethod = ScrollingMovementMethod()
        RDesc.movementMethod = ScrollingMovementMethod()


        if(champ.spells != null){
            title.text = champ.name.plus("Spells")
            Picasso.get().load(champ.spells!!.PIcon).into(PIcon);
            PName.text = champ.spells!!.PName
            PDesc.text = champ.spells!!.PDesc
            Picasso.get().load(champ.spells!!.QIcon).into(QIcon);
            QName.text = champ.spells!!.QName
            QDesc.text = champ.spells!!.QDesc
            Picasso.get().load(champ.spells!!.WIcon).into(WIcon);
            WName.text = champ.spells!!.WName
            WDesc.text = champ.spells!!.WDesc
            Picasso.get().load(champ.spells!!.EIcon).into(EIcon);
            EName.text = champ.spells!!.EName
            EDesc.text = champ.spells!!.EDesc
            Picasso.get().load(champ.spells!!.RIcon).into(RIcon);
            RName.text = champ.spells!!.RName
            RDesc.text = champ.spells!!.RDesc
        }

        goBack.setOnClickListener{
            val action = SpellsFragmentDirections.actionSpellsFragmentToChampionFragment(position)
            findNavController().navigate(action)
        }
    }
}