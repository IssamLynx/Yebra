package com.example.projettdm.ui.affichageMedecins

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.projettdm.R
import kotlinx.android.synthetic.main.fragment_liste_medecins.*

@Suppress("DEPRECATION")
class ListeMedecins : Fragment() {

    private lateinit var adapter: MedecinAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_liste_medecins, container, false)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        adapter = MedecinAdapter(requireActivity())
        progressBarM.visibility=View.VISIBLE
        val viewModel = ViewModelProvider(this).get(MedecinViewModel::class.java)
        viewModel.getMedecins();
        viewModel.medecins.observe(requireActivity(), Observer { medecins ->
            adapter.setMedecins(medecins)
            progressBarM.visibility=View.GONE

        })

        recyclerView.layoutManager = LinearLayoutManager(requireActivity())
        recyclerView.adapter = adapter


    }


}