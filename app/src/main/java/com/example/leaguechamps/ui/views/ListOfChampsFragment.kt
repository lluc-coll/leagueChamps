package com.example.leaguechamps.ui.views

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.leaguechamps.R
import com.example.leaguechamps.ui.adapters.ListOfChampsAdapter
import com.example.leaguechamps.ui.adapters.ListOfItemsAdapter
import com.example.leaguechamps.ui.viewModel.LeagueViewModel

class ListOfChampsFragment : Fragment(R.layout.list_of_champs_fragment), ListOfChampsAdapter.OnItemClickListener {
    lateinit var title: TextView
    lateinit var config: ImageButton
    lateinit var search: ImageButton
    lateinit var searchBar: EditText
    lateinit var favIcon: ImageButton
    lateinit var champButton: ImageButton
    lateinit var itemButton: ImageButton
    private lateinit var recyclerView: RecyclerView
    private val viewModel: LeagueViewModel by activityViewModels()
    var numChamps = 5
    var numItems = 1

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        title = view.findViewById(R.id.title)
        config = view.findViewById(R.id.config)
        search = view.findViewById(R.id.search)
        searchBar = view.findViewById(R.id.searchInputLayout)
        favIcon = view.findViewById(R.id.favIcon)
        champButton = view.findViewById(R.id.champ_button)
        itemButton = view.findViewById(R.id.item_button)
        recyclerView = view.findViewById(R.id.recycler_view)

        val orientation = resources.configuration.orientation
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            numChamps = 8
            numItems = 2
        } else {
            numChamps = 5
            numItems = 1
        }


        recyclerView.layoutManager = GridLayoutManager(requireContext(), numChamps)
        val champAdapter = ListOfChampsAdapter(viewModel.getChamps(), this)
        var favChamps = ListOfChampsAdapter(viewModel.favChamps(), this)
        var searchChamps = ListOfChampsAdapter(viewModel.searchChamps(""), this)
        val itemAdapter = ListOfItemsAdapter(viewModel.getItems(), this)
        var favItems = ListOfItemsAdapter(viewModel.favItems(), this)
        var searchItems = ListOfItemsAdapter(viewModel.searchItems(""), this)

        /*if (viewModel.ft) {
            viewModel.ft = false
            Handler().postDelayed({
                recyclerView.adapter = champAdapter
            }, 1000)
        }*/

        if (viewModel.favs) {
            recyclerView.layoutManager = GridLayoutManager(requireContext(), numChamps)
            viewModel.mutableChampList.observe(viewLifecycleOwner, {recyclerView.adapter = favChamps})
        }
        else if(viewModel.searching){
            searchChamps = ListOfChampsAdapter(viewModel.searchChamps(viewModel.toSearch), this)
            recyclerView.layoutManager = GridLayoutManager(requireContext(), numChamps)
            viewModel.mutableChampList.observe(viewLifecycleOwner, {recyclerView.adapter = searchChamps})
        }
        else {
            recyclerView.layoutManager = GridLayoutManager(requireContext(), numChamps)
            viewModel.mutableChampList.observe(viewLifecycleOwner, {recyclerView.adapter = champAdapter})
        }

        title.setOnClickListener {
            viewModel.searching = false
            recyclerView.layoutManager = GridLayoutManager(requireContext(), numChamps)
            recyclerView.adapter = champAdapter
        }


        config.setOnClickListener {
            val action = ListOfChampsFragmentDirections.actionListOfChampsFragmentToConfigFragment()
            findNavController().navigate(action)
        }

        champButton.setOnClickListener {
            if (viewModel.favs) {
                recyclerView.layoutManager = GridLayoutManager(requireContext(), numChamps)
                recyclerView.adapter = favChamps
            }
            else if(viewModel.searching){
                searchChamps = ListOfChampsAdapter(viewModel.searchChamps(viewModel.toSearch), this)
                recyclerView.layoutManager = GridLayoutManager(requireContext(), numChamps)
                recyclerView.adapter = searchChamps
            }
            else {
                recyclerView.layoutManager = GridLayoutManager(requireContext(), numChamps)
                recyclerView.adapter = champAdapter
            }
            champButton.setImageResource(R.drawable.champ_text_high)
            itemButton.setImageResource(R.drawable.item_text)
        }

        itemButton.setOnClickListener {
            if (viewModel.favs) {
                recyclerView.layoutManager = GridLayoutManager(requireContext(), numItems)
                recyclerView.adapter = favItems
            }else if(viewModel.searching){
                recyclerView.layoutManager = GridLayoutManager(requireContext(), numItems)
                recyclerView.adapter = searchItems
            }
            else {
                searchItems = ListOfItemsAdapter(viewModel.searchItems(viewModel.toSearch), this)
                recyclerView.layoutManager = GridLayoutManager(requireContext(), numItems)
                recyclerView.adapter = itemAdapter
            }
            champButton.setImageResource(R.drawable.champ_text)
            itemButton.setImageResource(R.drawable.item_text_high)
        }


        favIcon.setOnClickListener {
            viewModel.searching = false
            if (viewModel.favs) {
                viewModel.favs = false
                if (recyclerView.adapter!!.equals(favChamps)) {
                    recyclerView.layoutManager = GridLayoutManager(requireContext(), numChamps)
                    recyclerView.adapter = champAdapter
                } else if (recyclerView.adapter!!.equals(favItems)) {
                    recyclerView.layoutManager = GridLayoutManager(requireContext(), numItems)
                    recyclerView.adapter = itemAdapter
                }
            } else {
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

        search.setOnClickListener {
            viewModel.searching = true
            viewModel.toSearch = searchBar.text.toString()
            if (recyclerView.adapter!!.equals(champAdapter) || recyclerView.adapter!!.equals(favChamps) || recyclerView.adapter!!.equals(searchChamps)
            ) {
                searchChamps = ListOfChampsAdapter(viewModel.searchChamps(viewModel.toSearch), this)
                recyclerView.layoutManager = GridLayoutManager(requireContext(), numChamps)
                recyclerView.adapter = searchChamps
            } else {
                searchItems = ListOfItemsAdapter(viewModel.searchItems(viewModel.toSearch), this)
                recyclerView.layoutManager = GridLayoutManager(requireContext(), numItems)
                recyclerView.adapter = searchItems
            }
        }
    }


    override fun onItemClick(position: Int) {
        val action = ListOfChampsFragmentDirections.actionListOfChampsFragmentToChampionFragment(position)
        findNavController().navigate(action)
    }
}