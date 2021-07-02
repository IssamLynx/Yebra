package com.example.projettdm.data.api

import com.example.projettdm.data.model.Medecin
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ServiceProvider {

    @GET("getMedecins")
    fun getMedecins(): Call<List<Medecin>>
}