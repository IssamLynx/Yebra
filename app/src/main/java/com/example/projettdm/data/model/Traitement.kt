package com.example.projettdm.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "Traitements",primaryKeys = ["id_medecin","id_patient","instructions","date_debut","date_fin"])
data class Traitement(

    val id_medecin:Int,
    val id_patient:Int,
    val instructions: String,
    val date_debut : String,
    val date_fin : String,
    val terminer: Boolean,
    val nom_medecin:String,
    val prenom_medecin:String,
    val image_medecin:String,
    val titre:String)
