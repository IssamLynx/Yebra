package com.example.projettdm.ui.affichageMedecins

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.projettdm.R
import com.example.projettdm.data.model.Medecin

class MedecinAdapter(val context: Context): RecyclerView.Adapter<MyViewHolder>() {

    private var data = mutableListOf<Medecin>()
    fun setMedecins(medecins: List<Medecin>) {
        data.clear()
        data.addAll(medecins)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(context).inflate(R.layout.medecin_element, parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.nom.text=data[position].nom
        holder.prenom.text=data[position].prenom
        holder.specialite.text=data[position].specialite
        holder.telephone.text=data[position].tel

        Glide.with(context).load("https://shielded-ravine-75627.herokuapp.com/${data[position].image}")
            .into(holder.image)

        Log.i("issamm",data[position].nom)
        holder.btn.setOnClickListener {view ->

            view?.findNavController()?.navigate(R.id.action_listeMedecins_to_confirmerRdv)

        }

    }

    override fun getItemCount()=data.size
}

class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val nom = view.findViewById<TextView>(R.id.nomR)
    val prenom = view.findViewById<TextView>(R.id.prenomR)
    val specialite = view.findViewById<TextView>(R.id.specialiteR)
    val telephone = view.findViewById<TextView>(R.id.telephoneR)
    val image=view.findViewById<ImageView>(R.id.imgMedecinL)
    val btn=view.findViewById<Button>(R.id.button8)

}