package com.example.leaguechamps

import android.os.Bundle
import android.os.Handler
import android.util.Log
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

        val lastlang = viewModel.language
        val lastver = viewModel.version

        val lang = viewModel.loadLanguages()
        val vers = viewModel.loadVersions()

        val adapterL = ArrayAdapter(context!!, android.R.layout.simple_spinner_item, lang)
        adapterL.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        languages.setAdapter(adapterL)

        val adapterV = ArrayAdapter(context!!, android.R.layout.simple_spinner_item, vers)
        adapterV.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        versions.setAdapter(adapterV)


        goBack.setOnClickListener{
            val action = ConfigFragmentDirections.actionConfigFragmentToListOfChampsFragment()
            findNavController().navigate(action)
        }

        done.setOnClickListener{
            if(languages.selectedItem != null) {
                viewModel.language = languages.selectedItem.toString()
            }
            if (versions.selectedItem != null) {
                viewModel.version = versions.selectedItem.toString()
            }
            if(lastlang != viewModel.language || lastver != viewModel.version) {
                viewModel.ft = true
                viewModel.loadChamps()
                viewModel.loadItems()
            }
            val action = ConfigFragmentDirections.actionConfigFragmentToListOfChampsFragment()
            findNavController().navigate(action)
        }
    }
}