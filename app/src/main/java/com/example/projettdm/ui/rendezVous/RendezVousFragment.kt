package com.example.projettdm.ui.rendezVous

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.projettdm.R
import com.example.projettdm.data.model.Medecin
import com.example.projettdm.data.model.RendezVous
import com.example.projettdm.ui.UserActivity
import com.example.projettdm.ui.affichageMedecins.MedecinViewModel
import com.example.projettdm.ui.affichageMedecins.RdvViewModel
import com.example.projettdm.utils.rendezVous
import com.example.projettdm.utils.rendezVousFiltered
import kotlinx.android.synthetic.main.fragment_rendez_vous.*
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*


@Suppress("DEPRECATION")
class RendezVousFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_rendez_vous, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        var adapter = RendezVousAdapter(requireActivity())
        val viewModel = ViewModelProvider(context as UserActivity).get(MedecinViewModel::class.java)
        val vm = ViewModelProvider(context as UserActivity).get(RdvViewModel::class.java)
        button5.visibility=View.GONE
        nomRd.text=viewModel.medecin.nom
        prenomRd.text=viewModel.medecin.prenom
        specialiteRd.text=viewModel.medecin.specialite
        telephoneRd.text=viewModel.medecin.tel
        Glide.with(requireActivity()).load("https://shielded-ravine-75627.herokuapp.com/${viewModel.medecin.image}")
            .into(imgMedecinLd)


        vm.getRdv()

        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)
        datePickerTimeline.setInitialDate(year, month, day)
        rendezVous.observe(requireActivity(), Observer {
            rendezVous.value?.let { adapter.setRendezVous(it) }
            rendezVousFiltered.value?.let { adapter.setRendezVous(it) }

            recycleview.layoutManager = LinearLayoutManager(requireActivity())
            recycleview.adapter = adapter
            rendezVous.value?.let { it1 -> adapter.setRendezVous(it1) }

        })
        datePickerTimeline.setOnDateSelectedListener { year, month, day, dayOfWeek ->
            Toast.makeText(requireActivity(),year.toString(),Toast.LENGTH_SHORT)
            val formatter: DateFormat = SimpleDateFormat("dd/MM/yyyy")
            rendezVousFiltered.value = rendezVous.value?.filter {
                formatter.format(it.date).equals("${day.toString()}/${month.toString()}/${year.toString()}")
                        ||formatter.format(it.date).equals("0${day.toString()}/0${month.toString()}/${year.toString()}")

            }

            Log.i("issamyear","${rendezVousFiltered.value?.size}")
            Log.i("issamyear","${rendezVous.value?.size}")
            Log.i("issamyear","${day.toString()}/${month.toString()}/${year.toString()}")


            if(rendezVousFiltered.value!=null)
            {
            if((rendezVousFiltered.value as MutableList<RendezVous>).size>1) {
                button5.visibility=View.GONE
                adapter.setRendezVous(rendezVousFiltered.value!!)
                recycleview.layoutManager = LinearLayoutManager(requireActivity())
                recycleview.adapter = adapter
            }}




        }


}}