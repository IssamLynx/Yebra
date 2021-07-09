package com.example.projettdm.ui.traitements

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.projettdm.data.model.Traitement
import com.example.projettdm.data.repositories.TraitementRepository

class TraitementViewModel: ViewModel() {
    private val TAG = "TAG-Traitement-View-Model"

    var traitements = MutableLiveData<List<Traitement>>()

    fun getTraitements() {
        traitements= TraitementRepository.getTraitements(TAG)
    }

}