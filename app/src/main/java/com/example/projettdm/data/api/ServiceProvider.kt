package com.example.projettdm.data.api

import com.example.projettdm.data.model.Medecin
import com.example.projettdm.data.model.RendezVous
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ServiceProvider {

    @GET("getMedecins")
    fun getMedecins(): Call<List<Medecin>>

    @GET("getRendezVousMedecin/{id}")
    fun getRendezVous(@Path("id") id:String?): Call<List<RendezVous>>
}