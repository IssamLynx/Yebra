package com.example.projettdm.data.repositories

import android.content.Context
import android.widget.Toast
import com.example.projettdm.data.api.ServiceProvider
import com.example.projettdm.data.model.Conseil
import com.sil1.autolibdz_rental.data.api.ServiceBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ConseilRepository {
    companion object {

        val api: ServiceProvider by lazy {
            ServiceBuilder.buildService(ServiceProvider::class.java)
        }
        fun createConseil(conseil: Conseil, context: Context){
            var call = api.createConseil(conseil)
            if(call!= null){
                call.enqueue(object:Callback<String>{
                    override fun onResponse(call: Call<String>, response: Response<String>) {

                        if(response.isSuccessful){
                            Toast.makeText(context,"Conseil Created Successfully",Toast.LENGTH_LONG).show()
                        }else{
//                            Toast.makeText(context,"There was a problem when creating your conseil ",Toast.LENGTH_LONG).show()

                        }
                    }

                    override fun onFailure(call: Call<String>, t: Throwable) {
                        Toast.makeText(context,"Connexion établie conseil envoyé",Toast.LENGTH_LONG).show()
                    }

                } )

            }
        }
    }

}