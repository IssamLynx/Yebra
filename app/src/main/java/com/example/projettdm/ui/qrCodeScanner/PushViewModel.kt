package com.example.projettdm.ui.qrCodeScanner

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.projettdm.data.model.RendezVous
import com.example.projettdm.data.repositories.RendezVousRepository


class PushViewModel : ViewModel() {
    lateinit var rdvMedecins: MutableLiveData<List<RendezVous>>
    private val TAG="PushViewModel"
    fun getRdvMedecins(id:Int){
        rdvMedecins= RendezVousRepository.getRendezVous(TAG,id)

    }


}