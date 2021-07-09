package com.example.projettdm.data.repositories

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.work.Constraints
import com.example.projettdm.data.api.ServiceProvider
import com.example.projettdm.data.model.Medecin
import com.example.projettdm.data.model.RendezVous
import com.example.projettdm.data.model.Traitement
import com.example.projettdm.data.room.RoomService
import com.example.projettdm.data.room.RoomService.context
import com.sil1.autolibdz_rental.data.api.ServiceBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.DateFormat
import java.text.SimpleDateFormat

@Suppress("DEPRECATION")
class TraitementRepository {

    companion object {

        val api: ServiceProvider by lazy {
            ServiceBuilder.buildService(ServiceProvider::class.java)
        }

        fun getTraitements(TAG: String): MutableLiveData<List<Traitement>> {
            if (checkNetwork()) {
                var call = api.getTraitements("0")// consommation de l'api
            var traitementRespond: List<Traitement>?
            var traitementList = mutableListOf<Traitement>()
            var finalList = MutableLiveData<List<Traitement>>()

            if (call != null) {
                call.enqueue(object : Callback<List<Traitement>> {
                    override fun onResponse(call: Call<List<Traitement>>, response: Response<List<Traitement>>) {
                        Log.i(TAG, "Display Traitements List: call enqueue")

                        if (!response.isSuccessful) {
                            Log.i(TAG, "CODE:" + response.code().toString())
                            return
                        }

                        traitementRespond = response.body()  // Getting the list
                        if (traitementRespond != null) {
                            Log.i(TAG, "REPONSES: HERE is ALL THE Traitements:")
                            for (m in traitementRespond!!) {
                                var content = ""
                                content += " $m \n"

                                Log.i(TAG, "\n=========\n$content")
                                traitementList.add(m)
                                RoomService.database.getTraitementDao().addTraitement(m)

                            }
                            finalList.value = traitementList
                        }
                    }

                    override fun onFailure(call: Call<List<Traitement>>, t: Throwable) {
                        Log.i(TAG, "error CODE:" + t.message)
                    }
                })
            }
            return finalList
        }else {
                var finalList = MutableLiveData<List<Traitement>>()
                finalList.value = RoomService.database.getTraitementDao().selectTraitements()
                return finalList
            }
        }
        private fun checkNetwork(): Boolean {
            val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
            val isConnected: Boolean = activeNetwork?.isConnectedOrConnecting == true
            return isConnected
        }


    }
}