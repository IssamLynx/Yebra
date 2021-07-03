package com.example.projettdm.ui.affichageMedecins

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.projettdm.data.model.Medecin
import com.example.projettdm.data.model.RendezVous
import com.example.projettdm.data.repositories.MedecinRepository
import com.example.projettdm.data.repositories.RendezVousRepository
import com.example.projettdm.utils.rendezVous
import com.example.projettdm.utils.rendezVousFiltered

class RdvViewModel : ViewModel() {
    private val TAG = "TAG-Medecin-View-Model"
    lateinit var rdv:RendezVous

    fun getRdv() {
        rendezVous = RendezVousRepository.getRendezVous(TAG)
        rendezVousFiltered=rendezVous
    }



}