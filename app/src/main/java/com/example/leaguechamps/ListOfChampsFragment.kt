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
    lateinit var search: ImageButton
    lateinit var favIcon: ImageButton
    private  lateinit var recyclerView: RecyclerView
    private val viewModel: ChampViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        search = view.findViewById(R.id.search)
        favIcon = view.findViewById(R.id.favIcon)
        recyclerView = view.findViewById(R.id.recycler_view)
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 5)
        val adapter = ListOfChampsAdapter(viewModel.getLists(), this)
        if (viewModel.ft){
            viewModel.ft = false
        Handler().postDelayed({
            recyclerView.adapter = adapter
        }, 1000)}
        else{
            recyclerView.adapter = adapter
        }
    }

    override fun onItemClick(position: Int) {
        val action = ListOfChampsFragmentDirections.actionListOfChampsFragmentToChampionFragment(position)
        findNavController().navigate(action)
    }
}