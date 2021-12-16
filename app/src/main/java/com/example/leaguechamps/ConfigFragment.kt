package com.example.leaguechamps

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels

import androidx.navigation.fragment.findNavController


class ConfigFragment: Fragment(R.layout.config)  {
    lateinit var goBack: ImageButton
    lateinit var languages: Spinner
    lateinit var versions: Spinner
    lateinit var done: ImageButton

    private val viewModel: LeagueViewModel by activityViewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        goBack = view.findViewById(R.id.goBack)
        languages = view.findViewById(R.id.language)
        versions = view.findViewById(R.id.versions)
        done = view.findViewById(R.id.done)


        val adapterL = ArrayAdapter(context!!, android.R.layout.simple_spinner_item, viewModel.loadLanguages())
        adapterL.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        languages.setAdapter(adapterL)
        languages.prompt = viewModel.language.toString()

        languages.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent:AdapterView<*>, view: View, position: Int, id: Long){
                viewModel.language = parent.getItemAtPosition(position).toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>){

            }
        }


        versions.prompt = viewModel.version.toString()
        val adapterV = ArrayAdapter(context!!, android.R.layout.simple_spinner_item, viewModel.loadVersions())
        adapterV.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        versions.setAdapter(adapterV)

        goBack.setOnClickListener{
            val action = ConfigFragmentDirections.actionConfigFragmentToListOfChampsFragment()
            findNavController().navigate(action)
        }

        done.setOnClickListener{
            viewModel.language = "es_ES"
            viewModel.version = versions.selectedItem.toString()
            viewModel.ft = true
            viewModel.loadChamps()
            viewModel.loadItems()
            val action = ConfigFragmentDirections.actionConfigFragmentToListOfChampsFragment()
            findNavController().navigate(action)
        }
    }
}