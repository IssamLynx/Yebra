package com.example.projettdm.data.repositories

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.projettdm.data.api.ServiceProvider
import com.example.projettdm.data.model.Medecin
import com.sil1.autolibdz_rental.data.api.ServiceBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MedecinRepository {

    companion object {

        val api: ServiceProvider by lazy {
            ServiceBuilder.buildService(ServiceProvider::class.java)
        }

        fun getMedecins(TAG: String): MutableLiveData<List<Medecin>> {
            var call = api.getMedecins()// consommation de l'api
            var medecinRespond: List<Medecin>?
            var medecinList = mutableListOf<Medecin>()
            var finalList = MutableLiveData<List<Medecin>>()

            if (call != null) {
                call.enqueue(object : Callback<List<Medecin>> {
                    override fun onResponse(call: Call<List<Medecin>>, response: Response<List<Medecin>>) {
                        Log.i(TAG, "Display Medecins List: call enqueue")

                        if (!response.isSuccessful) {
                            Log.i(TAG, "CODE:" + response.code().toString())
                            return
                        }

                        medecinRespond = response.body()  // Getting the list
                        if (medecinRespond != null) {
                            Log.i(TAG, "REPONSES: HERE is ALL THE Medecins:")
                            for (m in medecinRespond!!) {
                                var content = ""
                                content += " $m \n"
                                Log.i(TAG, "\n=========\n$content")
                                medecinList.add(m)
                            }
                            finalList.value = medecinList
                        }
                    }

                    override fun onFailure(call: Call<List<Medecin>>, t: Throwable) {
                        Log.i(TAG, "error CODE:" + t.message)
                    }
                })
            }
            return finalList
        }

    }
}