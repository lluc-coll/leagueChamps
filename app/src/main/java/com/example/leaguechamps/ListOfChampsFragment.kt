package com.example.leaguechamps

import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ListOfChampsFragment : Fragment(R.layout.list_of_champs_fragment), ListOfChampsAdapter.OnItemClickListener{
    lateinit var config: ImageButton
    lateinit var search: ImageButton
    lateinit var favIcon: ImageButton
    lateinit var champButton: ImageButton
    lateinit var itemButton: ImageButton
    private  lateinit var recyclerView: RecyclerView
    private val viewModel: LeagueViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        config = view.findViewById(R.id.config)
        search = view.findViewById(R.id.search)
        favIcon = view.findViewById(R.id.favIcon)
        champButton = view.findViewById(R.id.champ_button)
        itemButton = view.findViewById(R.id.item_button)
        recyclerView = view.findViewById(R.id.recycler_view)
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 5)
        val champAdapter = ListOfChampsAdapter(viewModel.getChamps(), this)
        val itemAdapter = ListOfItemsAdapter(viewModel.getItems(), this)
        if (viewModel.ft){
            viewModel.ft = false
        Handler().postDelayed({
            recyclerView.adapter = champAdapter
        }, 1000)}
        else{
            recyclerView.adapter = champAdapter
        }


        champButton.setOnClickListener{
            champButton.setImageResource(R.drawable.champ_text_high)
            itemButton.setImageResource(R.drawable.item_text)
            recyclerView.layoutManager = GridLayoutManager(requireContext(), 5)
            recyclerView.adapter = champAdapter
            viewModel.favs = false
        }

        itemButton.setOnClickListener{
            champButton.setImageResource(R.drawable.champ_text)
            itemButton.setImageResource(R.drawable.item_text_high)
            recyclerView.layoutManager = GridLayoutManager(requireContext(), 1)
            recyclerView.adapter = itemAdapter
            viewModel.favs = false
        }

        favIcon.setOnClickListener{
            if(recyclerView.adapter!!.equals(champAdapter)){
                recyclerView.adapter = ListOfChampsAdapter(viewModel.favChamps(), this)
                viewModel.favs = true
            }
        }
    }

    override fun onItemClick(position: Int) {
        val action = ListOfChampsFragmentDirections.actionListOfChampsFragmentToChampionFragment(position)
        findNavController().navigate(action)
    }
}