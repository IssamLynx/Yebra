package com.example.projettdm.ui.affichageMedecins

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.projettdm.data.model.Medecin
import com.example.projettdm.data.model.authModel
import com.example.projettdm.data.repositories.MedecinRepository

class AuthViewModel : ViewModel() {
    private val TAG = "TAG-Auth-View-Model"



    fun authentifier(user: authModel, context: Context) {
        AuthentificationRepository.authentifier(TAG,user,context)

    }




}