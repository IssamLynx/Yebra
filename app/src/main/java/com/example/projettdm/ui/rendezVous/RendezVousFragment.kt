package com.example.projettdm.ui.rendezVous

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.projettdm.R
import com.example.projettdm.ui.UserActivity
import com.example.projettdm.ui.affichageMedecins.MedecinViewModel
import com.example.projettdm.ui.affichageMedecins.RdvViewModel
import kotlinx.android.synthetic.main.fragment_rendez_vous.*
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.LocalDate
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

    @RequiresApi(Build.VERSION_CODES.O)
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


        vm.getRdv(viewModel.medecin.id)

        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        Log.i("issambakhaissam",year.toString())
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)
        datePickerTimeline.setInitialDate(year, month, day)

        val formatter: DateFormat = SimpleDateFormat("yyyy-MM-dd")

        val test = LocalDate.of(year,month,day)

        vm.rendezVous.observe(requireActivity(),Observer {rdv->
            adapter.setRendezVous(rdv)
        })

        datePickerTimeline.setOnDateSelectedListener { year, month, day, dayOfWeek ->

          val liste = vm.rendezVous.value?.filter { formatter.format(it.date).replace("-","").toInt()==LocalDate.of(year,month+1,day).toString().replace("-","").toInt()}
           if(liste!=null)
            adapter.setRendezVous(liste)

            }

            recycleMainRdv.layoutManager = LinearLayoutManager(requireActivity())
            recycleMainRdv.adapter = adapter



        }


}