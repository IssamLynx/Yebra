package com.example.projettdm.data.repositories

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.core.content.edit
import androidx.lifecycle.MutableLiveData
import com.example.projettdm.data.api.ServiceProvider
import com.example.projettdm.data.model.Medecin
import com.example.projettdm.data.model.RendezVous
import com.example.projettdm.data.model.Traitement
import com.sil1.autolibdz_rental.data.api.ServiceBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.DateFormat
import java.text.SimpleDateFormat

class Pref {

    companion object {
        const val KEY_Pref="compte"
        const val KEY_Id="id"


        lateinit var sharedPref: SharedPreferences

       fun save(type:String?,id:Int?){
           sharedPref.edit {
               putString(KEY_Pref,type)
               id?.let { putInt(KEY_Id, it) }

           }
       }

        fun getId():Int{
            return sharedPref.getInt(KEY_Id,-1)
        }
        fun get():String {
            return sharedPref.getString(Pref.KEY_Pref,"nothing").toString()
        }

        fun clear(){
            sharedPref.edit {
                putString(KEY_Pref,"nothing")
                putInt(KEY_Id,-1)

            }

        }

    }
}