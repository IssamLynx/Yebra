package com.example.projettdm.ui.rendezVous

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.projettdm.R
import com.example.projettdm.data.repositories.Pref
import com.example.projettdm.ui.affichageMedecins.MedecinAdapter
import com.example.projettdm.ui.affichageMedecins.MedecinViewModel
import com.example.projettdm.ui.affichageMedecins.RdvViewModel
import kotlinx.android.synthetic.main.fragment_liste_medecins.*
import kotlinx.android.synthetic.main.fragment_liste_medecins.recyclerView
import kotlinx.android.synthetic.main.fragment_rdv_pris.*


class RdvPris : Fragment() {

    private lateinit var adapter: RdvPrisAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_rdv_pris, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        adapter = RdvPrisAdapter(requireActivity())

        val viewModel = ViewModelProvider(this).get(RdvViewModel::class.java)
        viewModel.getRdvByPatient(Pref.getId())
        viewModel.RdvByPatient.observe(requireActivity(), Observer { rdvs ->
            adapter.setRendezVous(rdvs)

        })
        rdvPriss.layoutManager = LinearLayoutManager(requireActivity())
        rdvPriss.adapter = adapter




    }
}