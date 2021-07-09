package com.example.projettdm.ui.traitements

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.projettdm.R
import kotlinx.android.synthetic.main.fragment_traitements.*
import kotlinx.android.synthetic.main.fragment_traitements.view.*
import java.text.SimpleDateFormat
import java.util.*


class TraitementsFragment : Fragment() {

    private lateinit var adapter: TraitementAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_traitements, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        enCours.isSelected = true
        adapter = TraitementAdapter(requireActivity())

        val viewModel = ViewModelProvider(this).get(TraitementViewModel::class.java)

        viewModel.getTraitements();

        val currentDate = SimpleDateFormat("dd/mm/yyyy").parse(SimpleDateFormat("dd/mm/yyyy").format(
            Date()
        ))
        viewModel.traitements.observe(requireActivity(), Observer { traitements ->
            val filtre = viewModel.traitements.value?.filter { it.date_fin<currentDate.toString() }
            if (filtre != null) {
                adapter.setTraitements(filtre)
            }

        })

        termine.setOnClickListener {
            enCours.isSelected = false
            termine.isSelected=true

            val filtre = viewModel.traitements.value?.filter { it.date_fin>currentDate.toString() }
            if (filtre != null) {
                adapter.setTraitements(filtre)
            }
        }
        enCours.setOnClickListener {
            enCours.isSelected = true
            termine.isSelected=false

            val filtre = viewModel.traitements.value?.filter { it.date_fin<currentDate.toString() }
            if (filtre != null) {
                adapter.setTraitements(filtre)
            }
        }
        recycleTraitement.layoutManager = LinearLayoutManager(requireActivity())
        recycleTraitement.adapter = adapter


    }


}