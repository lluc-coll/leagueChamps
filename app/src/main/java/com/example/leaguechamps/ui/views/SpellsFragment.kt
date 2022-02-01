package com.example.leaguechamps.ui.views

import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.leaguechamps.ui.models.ChampExtra
import com.example.leaguechamps.R
import com.example.leaguechamps.ui.viewModel.LeagueViewModel
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

    private val viewModel: LeagueViewModel by activityViewModels()
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
        val champ = viewModel.getOneChamp(position!!)
        viewModel.favs = false




        var extra = ChampExtra("null", "null", null, null)

        for (i in 0..viewModel.champExtras.size-1){
            if(viewModel.champExtras.get(i).champId!!.equals(champ.id)){
                extra = viewModel.champExtras.get(i)
            }
        }


        title.text = extra.name.plus(" Spells")
        Picasso.get().load(extra.spells!!.PIcon).into(PIcon);
        PName.text = extra.spells!!.PName
        PDesc.text = extra.spells!!.PDesc
        Picasso.get().load(extra.spells!!.QIcon).into(QIcon);
        QName.text = extra.spells!!.QName
        QDesc.text = extra.spells!!.QDesc
        Picasso.get().load(extra.spells!!.WIcon).into(WIcon);
        WName.text = extra.spells!!.WName
        WDesc.text = extra.spells!!.WDesc
        Picasso.get().load(extra.spells!!.EIcon).into(EIcon);
        EName.text = extra.spells!!.EName
        EDesc.text = extra.spells!!.EDesc
        Picasso.get().load(extra.spells!!.RIcon).into(RIcon);
        RName.text = extra.spells!!.RName
        RDesc.text = extra.spells!!.RDesc


        goBack.setOnClickListener{
            val action = SpellsFragmentDirections.actionSpellsFragmentToChampionFragment(position)
            findNavController().navigate(action)
        }

    }
}