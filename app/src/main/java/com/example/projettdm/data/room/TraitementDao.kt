package com.example.projettdm.data.room

import androidx.lifecycle.MutableLiveData
import androidx.room.*
import com.example.projettdm.data.model.Traitement

//l'interface pour manipuler la table de docteur
@Dao
interface TraitementDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addTraitement(reservation: Traitement)
    @Update
    fun updateTraitement(reservation:Traitement)
    @Delete
    fun deleteTraitement(reservation:Traitement)
    @Query("SELECT * FROM Traitements")
    fun selectTraitements():List<Traitement>

    @Query("DELETE FROM Traitements")
    fun deleteAllTraitements()

}



