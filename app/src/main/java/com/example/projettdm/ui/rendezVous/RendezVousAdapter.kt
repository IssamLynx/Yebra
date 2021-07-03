package com.example.projettdm.ui.rendezVous

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.projettdm.R
import com.example.projettdm.data.model.Medecin
import com.example.projettdm.data.model.RendezVous
import com.example.projettdm.ui.UserActivity
import com.example.projettdm.ui.affichageMedecins.MedecinViewModel
import com.example.projettdm.ui.affichageMedecins.RdvViewModel
import java.text.DateFormat
import java.text.SimpleDateFormat

class RendezVousAdapter (val context: Context): RecyclerView.Adapter<RendezVousHolder>() {
    private var data = mutableListOf<RendezVous>()
    val formatter: DateFormat = SimpleDateFormat("dd/MM/yyyy")
    fun setRendezVous(rdvs: List<RendezVous>) {
        data.clear()
        data.addAll(rdvs)
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RendezVousHolder {
        return RendezVousHolder(LayoutInflater.from(context).inflate(R.layout.rendez_vous_element, parent, false))
    }

    override fun onBindViewHolder(holder: RendezVousHolder, position: Int) {
        holder.date.text = "Date: "+formatter.format(data[position].date)

        holder.duree.text="Durée: "+data[position].dure

        holder.itemView.setOnClickListener {view->
            val viewModel = ViewModelProvider(context as UserActivity).get(RdvViewModel::class.java)
            viewModel.rdv= RendezVous(data[position].date,data[position].temps_debut,data[position].id_medecin
            ,data[position].dure,data[position].id_patient)
            view?.findNavController()?.navigate(R.id.action_medecinToRdv_to_confirmerRdv)


        }


    }

    override fun getItemCount()=data.size



}
class RendezVousHolder(view: View) : RecyclerView.ViewHolder(view) {

    val medecin = view.findViewById<TextView>(R.id.medecinRv)
    val date = view.findViewById<TextView>(R.id.dateRv)
    val duree = view.findViewById<TextView>(R.id.dureeRv)

}