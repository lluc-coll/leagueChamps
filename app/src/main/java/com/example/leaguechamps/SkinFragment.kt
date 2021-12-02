package com.example.leaguechamps

import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.viewpager.widget.ViewPager
import com.synnapps.carouselview.CarouselView
import com.synnapps.carouselview.ImageListener

class SkinFragment : Fragment(R.layout.skins_fragment){
    lateinit var goBack: ImageButton
    private val viewModel: ChampViewModel by activityViewModels()

    lateinit var viewPage: ViewPager

    val skins = arrayOf(
        Skin("https://ddragon.leagueoflegends.com/cdn/img/champion/loading/Aatrox_0.jpg", "Aatrox"),
        Skin("https://ddragon.leagueoflegends.com/cdn/img/champion/loading/Aatrox_1.jpg", "Mecha Aatrox"),
        Skin("https://ddragon.leagueoflegends.com/cdn/img/champion/loading/Aatrox_11.jpg", "Celestial Aatrox"))

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        goBack = view.findViewById(R.id.goBack)

        viewPage = view.findViewById(R.id.viewPagerSkins)
        viewPage.adapter = SkinsAdapter(context, skins)


        val position = arguments?.getInt("position")
        val champ = viewModel.getOneList(position!!)

        goBack.setOnClickListener{
            val action = SkinFragmentDirections.actionSkinFragmentToChampionFragment(position)
            findNavController().navigate(action)
        }
    }
}