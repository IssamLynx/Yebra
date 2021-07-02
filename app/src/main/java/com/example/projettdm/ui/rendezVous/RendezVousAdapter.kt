package com.example.projettdm.ui.rendezVous

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.projettdm.R
import com.example.projettdm.data.model.Medecin

class RendezVousAdapter (val context: Context): RecyclerView.Adapter<RendezVousHolder>() {
    private var data = mutableListOf<Medecin>()
    fun setRendezVous(reservations: List<Medecin>) {
        data.clear()
        data.addAll(reservations)
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RendezVousHolder {
        return RendezVousHolder(LayoutInflater.from(context).inflate(R.layout.rendez_vous_element, parent, false))
    }

    override fun onBindViewHolder(holder: RendezVousHolder, position: Int) {
        holder.medecin.text = data[position].nom
        holder.date.text = data[position].tel
        holder.duree.text=data[position].nom


    }

    override fun getItemCount()=data.size



}
class RendezVousHolder(view: View) : RecyclerView.ViewHolder(view) {

    val medecin = view.findViewById<TextView>(R.id.medecinRv)
    val date = view.findViewById<TextView>(R.id.dateRv)
    val duree = view.findViewById<TextView>(R.id.dureeRv)

}