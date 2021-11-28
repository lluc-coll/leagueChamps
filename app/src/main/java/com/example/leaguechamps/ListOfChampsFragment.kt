package com.example.leaguechamps

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ListOfChampsFragment : Fragment(R.layout.list_of_champs_fragment), ListOfChampsAdapter.OnItemClickListener{
    private  lateinit var recyclerView: RecyclerView
    private val viewModel: ChampViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.recycler_view)
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 4)
        val adapter = ListOfChampsAdapter(viewModel.getLists(), this)
        recyclerView.adapter = adapter
    }

    override fun onItemClick(position: Int) {
        val action = ListOfChampsFragmentDirections.actionListOfChampsFragmentToChampionFragment(position)
        findNavController().navigate(action)
    }
}