package com.example.leaguechamps

import android.content.res.Configuration
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
    var numChamps = 5
    var numItems = 1

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        config = view.findViewById(R.id.config)
        search = view.findViewById(R.id.search)
        favIcon = view.findViewById(R.id.favIcon)
        champButton = view.findViewById(R.id.champ_button)
        itemButton = view.findViewById(R.id.item_button)
        recyclerView = view.findViewById(R.id.recycler_view)

        val orientation = resources.configuration.orientation
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            numChamps = 8
            numItems = 2
        }else{
            numChamps = 5
            numItems = 1
        }


        recyclerView.layoutManager = GridLayoutManager(requireContext(), numChamps)
        val champAdapter = ListOfChampsAdapter(viewModel.getChamps(), this)
        var favChamps = ListOfChampsAdapter(viewModel.favChamps(), this)
        val itemAdapter = ListOfItemsAdapter(viewModel.getItems(), this)
        var favItems = ListOfItemsAdapter(viewModel.favItems(), this)

        if (viewModel.ft){
            viewModel.ft = false
        Handler().postDelayed({
            recyclerView.adapter = champAdapter
        }, 1000)}
        else{
            recyclerView.adapter = champAdapter
        }


        config.setOnClickListener{
            val action = ListOfChampsFragmentDirections.actionListOfChampsFragmentToConfigFragment()
            findNavController().navigate(action)
        }

        champButton.setOnClickListener{
            if (viewModel.favs){
                recyclerView.layoutManager = GridLayoutManager(requireContext(), numChamps)
                recyclerView.adapter = favChamps
            }
            else{
                recyclerView.layoutManager = GridLayoutManager(requireContext(), numChamps)
                recyclerView.adapter = champAdapter
            }
            champButton.setImageResource(R.drawable.champ_text_high)
            itemButton.setImageResource(R.drawable.item_text)
        }

        itemButton.setOnClickListener{
            if (viewModel.favs){
                recyclerView.layoutManager = GridLayoutManager(requireContext(), numItems)
                recyclerView.adapter = favItems
            }
            else{
                recyclerView.layoutManager = GridLayoutManager(requireContext(), numItems)
                recyclerView.adapter = itemAdapter
            }
            champButton.setImageResource(R.drawable.champ_text)
            itemButton.setImageResource(R.drawable.item_text_high)
        }


        favIcon.setOnClickListener{
            if(viewModel.favs){
                viewModel.favs = false
                if(recyclerView.adapter!!.equals(favChamps)){
                    recyclerView.layoutManager = GridLayoutManager(requireContext(), numChamps)
                    recyclerView.adapter = champAdapter
                }
                else if (recyclerView.adapter!!.equals(favItems)){
                    recyclerView.layoutManager = GridLayoutManager(requireContext(), numItems)
                    recyclerView.adapter = itemAdapter
                }
            }
            else{
                viewModel.favs = true
                favChamps = ListOfChampsAdapter(viewModel.favChamps(), this)
                favItems = ListOfItemsAdapter(viewModel.favItems(), this)
                if (recyclerView.adapter!!.equals(champAdapter)) {
                    recyclerView.layoutManager = GridLayoutManager(requireContext(), numChamps)
                    recyclerView.adapter = favChamps
                } else {
                    recyclerView.layoutManager = GridLayoutManager(requireContext(), numItems)
                    recyclerView.adapter = favItems
                }

            }
        }
    }

    override fun onItemClick(position: Int) {
        val action = ListOfChampsFragmentDirections.actionListOfChampsFragmentToChampionFragment(position)
        findNavController().navigate(action)
    }
}