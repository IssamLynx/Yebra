package com.example.projettdm.ui.qrCodeScanner

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
import com.example.projettdm.ui.MedecinActivity
import com.example.projettdm.ui.UserActivity
import kotlinx.android.synthetic.main.fragment_rdv_en_cours.*


class RdvEnCours : Fragment() {


    private lateinit var adapter: rdvPushAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_rdv_en_cours, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        adapter = rdvPushAdapter(requireActivity())

        val vm = ViewModelProvider(context as MedecinActivity).get(PushViewModel::class.java)
        vm.getRdvMedecins(Pref.getId())
        vm.rdvMedecins.observe(requireActivity(), Observer { rdv ->
            val filtre=rdv.filter { it.id_patient!=-1 }
            adapter.setRendezVous(filtre)

        })
        pushRecycle.layoutManager = LinearLayoutManager(requireActivity())
        pushRecycle.adapter = adapter

    }
}