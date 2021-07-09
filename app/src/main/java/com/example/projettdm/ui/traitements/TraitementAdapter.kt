package com.example.projettdm.ui.traitements

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.graphics.Typeface
import android.text.InputType
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import androidx.work.*
import com.bumptech.glide.Glide
import com.example.projettdm.R
import com.example.projettdm.data.model.Medecin
import com.example.projettdm.data.model.Traitement
import com.example.projettdm.data.repositories.Pref
import com.example.projettdm.ui.conseil.ConseilWorker
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.traitment_dialog.view.*

private lateinit var customAlertDialogView : View

private lateinit var materialAlertDialogBuilder: MaterialAlertDialogBuilder

class TraitementAdapter(val context: Context): RecyclerView.Adapter<MyViewHolder>() {
    private var data = mutableListOf<Traitement>()
    fun setTraitements(traitements: List<Traitement>) {
        data.clear()
        data.addAll(traitements)
        notifyDataSetChanged()
    }

    private fun launchCustomAlertDialog() {
        materialAlertDialogBuilder.setView(customAlertDialogView).setTitle("Détails")

//            .setMessage("Enter your basic details")
            .setNegativeButton("Fermer") { dialog, _ ->
                displayMessage("Traitement Fermé!")
                dialog.dismiss()
            }
            .show()
    }
    private fun displayMessage(message : String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(context).inflate(R.layout.traitement_element, parent, false))
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.titreTraitment.text=data[position].titre.toString()
        holder.DateDebutTraitement.text=data[position].date_debut.toString()
        holder.DateFinTraitement.text=data[position].date_fin
        holder.medecinTraitement.text=data[position].nom_medecin + " " + data[position].prenom_medecin
        Glide.with(context).load("https://shielded-ravine-75627.herokuapp.com/${data[position].image_medecin}")
            .into(holder.imageTraitement)
        materialAlertDialogBuilder = MaterialAlertDialogBuilder(context)

        holder.itemView.setOnClickListener {
            customAlertDialogView = LayoutInflater.from(context)
                .inflate(R.layout.traitment_dialog, null, false)
            customAlertDialogView.detailDialog.text=data[position].instructions
            // Launching the custom alert dialog
            launchCustomAlertDialog()

        }
            val idMedecin=data[position].id_medecin
            holder.conseilButton.setOnClickListener {
            val builder: AlertDialog.Builder = android.app.AlertDialog.Builder(context)
            builder.setTitle("Conseil")

// Set up the input
            val input = EditText(context)
// Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
            input.setHint("Ecrire votre demande de conseil")
            input.inputType = InputType.TYPE_CLASS_TEXT
            builder.setView(input)

// Set up the buttons
            builder.setPositiveButton("OK", DialogInterface.OnClickListener { dialog, which ->
                // Here you get get input text from the Edittext
                var m_Text = input.text.toString()
                val constraint = Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build()
                val data = Data.Builder()
                data.putInt("id_medecin",idMedecin)
                Log.i("isso",Pref.getId().toString())
                data.putInt("id_patient", Pref.getId())
                data.putString("contenu",m_Text)
                val req = OneTimeWorkRequest.Builder(ConseilWorker::class.java).setConstraints(constraint).addTag("T1").setInputData(data.build()).build()

                val workManager = WorkManager.getInstance(context)
                workManager.enqueue(req)
            })
            builder.setNegativeButton("Annuler", DialogInterface.OnClickListener { dialog, which ->
                displayMessage("Conseil Fermé!")
                dialog.cancel() })

            builder.show()

        }


    }


    override fun getItemCount()=data.size


}


class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val titreTraitment = view.findViewById<TextView>(R.id.titreTraitment)
    val DateDebutTraitement = view.findViewById<TextView>(R.id.DateDebutTraitement)
    val DateFinTraitement = view.findViewById<TextView>(R.id.DateFinTraitement)
    val medecinTraitement = view.findViewById<TextView>(R.id.medecinTraitement)
    val imageTraitement=view.findViewById<ImageView>(R.id.imageTraitement)
    val conseilButton=view.findViewById<Button>(R.id.conseilButton)

}



