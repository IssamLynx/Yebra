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
import com.example.projettdm.data.model.RendezVous
import com.example.projettdm.ui.UserActivity
import com.example.projettdm.ui.affichageMedecins.RdvViewModel
import java.text.DateFormat
import java.text.SimpleDateFormat

class RendezVousAdapter (val context: Context): RecyclerView.Adapter<RendezVousHolder>() {
    private var data = mutableListOf<RendezVous>()
    val formatter: DateFormat = SimpleDateFormat("dd/MM/yyyy")
    fun setRendezVous(rdvs: List<RendezVous>) {
        data.clear()
        data.addAll(rdvs.filter { it.id_patient==-1 })
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RendezVousHolder {
        return RendezVousHolder(LayoutInflater.from(context).inflate(R.layout.rendez_vous_element, parent, false))
    }

    override fun onBindViewHolder(holder: RendezVousHolder, position: Int) {
        holder.date.text = formatter.format(data[position].date)
        holder.debut.text=data[position].debut
        holder.fin.text=data[position].fin

        holder.itemView.setOnClickListener {view->
            val viewModel = ViewModelProvider(context as UserActivity).get(RdvViewModel::class.java)
            viewModel.rdv= RendezVous(data[position].id,data[position].date,data[position].debut,data[position].id_medecin
            ,data[position].fin,data[position].id_patient,data[position].nom_patient,data[position].prenom_patient)
            view?.findNavController()?.navigate(R.id.action_medecinToRdv_to_confirmerRdv)


        }


    }

    override fun getItemCount()=data.size



}
class RendezVousHolder(view: View) : RecyclerView.ViewHolder(view) {

    val date = view.findViewById<TextView>(R.id.dateRv)
    val debut = view.findViewById<TextView>(R.id.debut)
    val fin = view.findViewById<TextView>(R.id.fin)

}