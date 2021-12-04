package com.example.leaguechamps

import android.os.Bundle
import android.os.Handler
import android.util.Log
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
        val champ = viewModel.getOneList(position!!)
        var extra = ChampExtra("null", "null",null, null)

        Log.d("asdasd", "${viewModel.champExtras}")

        for (i in 0..viewModel.champExtras.size-1){
            if(viewModel.champExtras.get(i).champId!!.equals(champ.id)){
                extra = viewModel.champExtras.get(i)
            }
        }

        if(extra.champId!!.equals("null")){
            viewModel.extendedChamp(champ.id.toString(), position)
            Handler().postDelayed({
                for (i in 0..viewModel.champExtras.size-1){
                    if(viewModel.champExtras.get(i).champId!!.equals(champ.id)){
                        extra = viewModel.champExtras.get(i)
                    }
                }
                viewPage.adapter = SkinsAdapter(context, extra!!.skins)
            }, 1000)
        }
        else{
            viewPage.adapter = SkinsAdapter(context, extra!!.skins)
        }

        goBack.setOnClickListener{
            val action = SkinFragmentDirections.actionSkinFragmentToChampionFragment(position)
            findNavController().navigate(action)
        }
    }
}