package com.example.projettdm.data.repositories

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.example.projettdm.data.api.ServiceProvider
import com.example.projettdm.data.model.Medecin
import com.example.projettdm.data.model.RdvDoneModel
import com.example.projettdm.data.model.RdvResponse
import com.example.projettdm.data.model.RendezVous
import com.sil1.autolibdz_rental.data.api.ServiceBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.DateFormat
import java.text.SimpleDateFormat

class RendezVousRepository {

    companion object {

        val api: ServiceProvider by lazy {
            ServiceBuilder.buildService(ServiceProvider::class.java)
        }

        fun getRendezVous(TAG: String,id:Int): MutableLiveData<List<RendezVous>> {
            var call = api.getRendezVous(id)// consommation de l'api
            var rendezVousRespond: List<RendezVous>?
            var rendezVousList = mutableListOf<RendezVous>()
            var finalList = MutableLiveData<List<RendezVous>>()

            if (call != null) {
                call.enqueue(object : Callback<List<RendezVous>> {
                    override fun onResponse(call: Call<List<RendezVous>>, response: Response<List<RendezVous>>) {
                        Log.i(TAG, "Display Rendez-vous List: call enqueue")

                        if (!response.isSuccessful) {
                            Log.i(TAG, "CODE:" + response.code().toString())
                            return
                        }

                        rendezVousRespond = response.body()  // Getting the list
                        if (rendezVousRespond != null) {
                            Log.i(TAG, "REPONSES: HERE is ALL THE Rendez-vous:")
                            for (m in rendezVousRespond!!) {
                                var content = ""
                                content += " $m \n"
                                val formatter: DateFormat = SimpleDateFormat("dd/MM/yyyy")
                                Log.i("issamou",formatter.format(m.date))

                                Log.i(TAG, "\n=========\n$content")
                                rendezVousList.add(m)
                            }

                            finalList.value = rendezVousList

                        }
                    }

                    override fun onFailure(call: Call<List<RendezVous>>, t: Throwable) {
                        Log.i(TAG, "error CODE:" + t.message)
                    }
                })
            }
            return finalList
        }


        fun prendreRdv(TAG:String,rdv:RdvDoneModel,context: Context)
        {
            var call = api.prendreRdv(rdv) // fonction de modification dans l'api

            call.enqueue(object:Callback<RdvResponse>{
                override fun onResponse(
                    call: Call<RdvResponse>,
                    response: Response<RdvResponse>
                ) {
                    if (!response.isSuccessful) {
                        Log.i(TAG, "CODE:" + response.code().toString())
                    } else {
                        Log.i(TAG, "REPONSES: updated successfully")
                    }}

                override fun onFailure(call: Call<RdvResponse>, t: Throwable) {
                    Log.i(TAG, "error CODE:" + t.message)

                }

            })

        }

        fun getRdvByPatient(TAG: String,id:Int): MutableLiveData<List<RendezVous>> {
            var call = api.getRdvByPatient(id)// consommation de l'api
            var rendezVousRespond: List<RendezVous>?
            var rendezVousList = mutableListOf<RendezVous>()
            var finalList = MutableLiveData<List<RendezVous>>()
            if (call != null) {

                call.enqueue(object : Callback<List<RendezVous>> {
                    override fun onResponse(call: Call<List<RendezVous>>, response: Response<List<RendezVous>>) {
                        Log.i(TAG, "Display Borne List: call enqueue")

                        if (!response.isSuccessful) {
                            Log.i(TAG, "CODE:" + response.code().toString())
                            return
                        }

                        rendezVousRespond = response.body()  // Getting the list
                        if (rendezVousRespond != null) {

                            Log.i(TAG, "REPONSES: HERE is ALL THE Rendez-vous:")
                            for (m in rendezVousRespond!!) {

                                var content = ""
                                content += " $m \n"

                                Log.i(TAG, "\n=========\n$content")
                                rendezVousList.add(m)
                            }
                            finalList.value = rendezVousList
                        }
                    }

                    override fun onFailure(call: Call<List<RendezVous>>, t: Throwable) {
                        Log.i(TAG, "error CODE:" + t.message)
                    }
                })
            }
            return finalList
        }
    }
}