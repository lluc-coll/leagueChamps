package com.example.leaguechamps

import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.viewpager.widget.ViewPager

class SkinFragment : Fragment(R.layout.skins_fragment){
    lateinit var goBack: ImageButton
    private val viewModel: LeagueViewModel by activityViewModels()

    lateinit var viewPage: ViewPager


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        goBack = view.findViewById(R.id.goBack)


        viewPage = view.findViewById(R.id.viewPagerSkins)

        val position = arguments?.getInt("position")
        val champ = viewModel.getOneChamp(position!!)
        var extra = ChampExtra("null", "null",null, null)
        viewModel.favs = false


        for (i in 0..viewModel.champExtras.size-1){
            if(viewModel.champExtras.get(i).champId!!.equals(champ.id)){
                extra = viewModel.champExtras.get(i)
            }
        }

        val orientation = resources.configuration.orientation
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            viewPage.adapter = SkinsAdapter(context, extra!!.skins, true)
        }
        else{
            viewPage.adapter = SkinsAdapter(context, extra!!.skins, false)
        }

        goBack.setOnClickListener{
            val action = SkinFragmentDirections.actionSkinFragmentToChampionFragment(position)
            findNavController().navigate(action)
        }
    }
}