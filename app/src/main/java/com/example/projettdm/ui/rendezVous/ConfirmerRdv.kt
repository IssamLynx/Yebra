package com.example.projettdm.ui.rendezVous

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import com.example.projettdm.R
import com.example.projettdm.ui.UserActivity
import com.example.projettdm.ui.affichageMedecins.MedecinViewModel
import com.example.projettdm.ui.affichageMedecins.RdvViewModel
import kotlinx.android.synthetic.main.fragment_confirmer_rdv.*
import java.text.DateFormat
import java.text.SimpleDateFormat

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class ConfirmerRdv : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_confirmer_rdv, container, false)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val vm1 = ViewModelProvider(context as UserActivity).get(MedecinViewModel::class.java)
        val vm2 = ViewModelProvider(context as UserActivity).get(RdvViewModel::class.java)
        val formatter: DateFormat = SimpleDateFormat("dd/MM/yyyy")

        nomRdc.text=vm1.medecin.nom
        prenomRdc.text=vm1.medecin.prenom
        specialiteRdc.text=vm1.medecin.specialite
        telephoneRdc.text=vm1.medecin.tel
        heureDebut.text=vm2.rdv.debut
        heureFin.text=vm2.rdv.fin
        daterdc.text= formatter.format(vm2.rdv.date)
        Glide.with(requireActivity()).load("https://shielded-ravine-75627.herokuapp.com/${vm1.medecin.image}")
            .into(imgMedecinLdc)
        confirmerrdvc.setOnClickListener {
            view?.findNavController()?.navigate(R.id.action_confirmerRdv_to_rdvDone)

        }

    }
}