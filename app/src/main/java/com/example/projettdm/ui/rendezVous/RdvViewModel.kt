package com.example.projettdm.ui.affichageMedecins

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.projettdm.data.model.Medecin
import com.example.projettdm.data.model.RdvDoneModel
import com.example.projettdm.data.model.RendezVous
import com.example.projettdm.data.repositories.MedecinRepository
import com.example.projettdm.data.repositories.RendezVousRepository


class RdvViewModel : ViewModel() {
    private val TAG = "TAG-rdv-View-Model"
    lateinit var rdv:RendezVous

    lateinit var RdvByPatient:MutableLiveData<List<RendezVous>>
    lateinit var rendezVous:MutableLiveData<List<RendezVous>>
    lateinit var rendezVousFiltered:MutableLiveData<List<RendezVous>>
    fun getRdv(id:Int) {
        rendezVous = RendezVousRepository.getRendezVous(TAG,id)
        Log.i("viewReservation",rendezVous.value?.get(0)?.date.toString())
    }
    fun prendreRdv(rdv:RdvDoneModel,context: Context){
        RendezVousRepository.prendreRdv(TAG,rdv,context)

    }
    fun getRdvByPatient(id:Int){
        RdvByPatient= RendezVousRepository.getRdvByPatient(TAG,id)

    }


}