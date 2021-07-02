package com.example.projettdm.ui.rendezVous

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.projettdm.R
import com.example.projettdm.data.model.Medecin
import com.example.projettdm.utils.rendezVousFiltered
import kotlinx.android.synthetic.main.fragment_rendez_vous.*


class RendezVousFragment : Fragment() {



    private var data1 = mutableListOf<Medecin>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_rendez_vous, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        rendezVousFiltered.value =data1

        datePickerTimeline.setInitialDate(2019, 3, 21)
// Set a date Selected Listener
// Set a date Selected Listener
        datePickerTimeline.setOnDateSelectedListener { year, month, day, dayOfWeek ->
            Log.i("issamaa",year.toString())
            Toast.makeText(requireActivity(),year.toString(),Toast.LENGTH_SHORT)
            val reser = rendezVousFiltered.value?.filter { it.nom =="Issam" }
                rendezVousFiltered.value=reser
        }
        if((rendezVousFiltered.value as MutableList<Medecin>).size>1) {
            button5.visibility=View.GONE
            var adapter = RendezVousAdapter(requireActivity())
            rendezVousFiltered.observe(requireActivity(), Observer { rendezVousFiltered ->
                adapter.setRendezVous(rendezVousFiltered)

            })

            adapter.setRendezVous(data1)
            recycleview.layoutManager = LinearLayoutManager(requireActivity())
            recycleview.adapter = adapter
        }
        else
        button5.visibility=View.VISIBLE


    }


}