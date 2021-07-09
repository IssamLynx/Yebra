package com.example.projettdm.data.api

import com.example.projettdm.data.model.*
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ServiceProvider {

    @GET("getMedecins")
    fun getMedecins(): Call<List<Medecin>>

    @GET("getRendezVousMedecin/{id}")
    fun getRendezVous(@Path("id") id:Int?): Call<List<RendezVous>>
    @GET("getListeTraitements/{id}")
    fun getTraitements(@Path("id") id:String?): Call<List<Traitement>>

    @POST("auth")
    fun authentifier(@Body reservation: authModel): Call<Medecin>

    @POST("createConseil")
    fun createConseil( @Body conseil: Conseil):Call<String>

    @POST("prendreRendezVous")
    fun prendreRdv( @Body rdv: RdvDoneModel):Call<RdvResponse>

    @GET("getRendezVousPatient/{id}")
    fun getRdvByPatient(@Path("id") id:Int): Call<List<RendezVous>>

    @POST("subscribe")
    fun subscribe (@Body subscriptionData: SubscriptionData): Call<String>




}