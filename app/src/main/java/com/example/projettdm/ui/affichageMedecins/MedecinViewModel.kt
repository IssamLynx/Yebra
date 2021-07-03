package com.example.projettdm.ui.affichageMedecins

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.projettdm.data.model.Medecin
import com.example.projettdm.data.repositories.MedecinRepository

class MedecinViewModel : ViewModel() {
    private val TAG = "TAG-Medecin-View-Model"
    lateinit var medecin:Medecin
    var medecins = MutableLiveData<List<Medecin>>()


    fun getMedecins() {
        medecins= MedecinRepository.getMedecins(TAG)
    }



}