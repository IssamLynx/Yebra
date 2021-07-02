package com.example.projettdm.ui.affichageMedecins

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.projettdm.data.model.Medecin
import com.example.projettdm.data.repositories.MedecinRepository
import com.example.projettdm.utils.medecins

class MedecinViewModel : ViewModel() {
    private val TAG = "TAG-Medecin-View-Model"



    fun getMedecins() {
        medecins = MedecinRepository.getMedecins(TAG)
    }



}