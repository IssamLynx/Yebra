package com.example.projettdm.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.projettdm.data.model.Traitement
import com.example.projettdm.data.room.Converter

//cette classe représentre notre base de données
@Database(entities = arrayOf(Traitement::class),version=1)
@TypeConverters(Converter::class)
abstract class Database: RoomDatabase() {
    abstract fun getTraitementDao():TraitementDao


}