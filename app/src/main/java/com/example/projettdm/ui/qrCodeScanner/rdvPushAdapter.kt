package com.example.projettdm.ui.qrCodeScanner

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.projettdm.R
import com.example.projettdm.data.model.RendezVous
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.zxing.BarcodeFormat
import com.google.zxing.qrcode.QRCodeWriter
import kotlinx.android.synthetic.main.detail_qr.view.*
import java.text.DateFormat
import java.text.SimpleDateFormat

private lateinit var materialAlertDialogBuilder: MaterialAlertDialogBuilder
private lateinit var customAlertDialogView : View

class rdvPushAdapter (val context: Context): RecyclerView.Adapter<rdvPushHolder>() {
    private var data = mutableListOf<RendezVous>()
    val formatter: DateFormat = SimpleDateFormat("dd/MM/yyyy")
    fun setRendezVous(rdvs: List<RendezVous>) {
        data.clear()
        data.addAll(rdvs)
        notifyDataSetChanged()
    }
    private fun launchCustomAlertDialog() {
        materialAlertDialogBuilder.setView(customAlertDialogView)
            .show()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): rdvPushHolder {
        return rdvPushHolder(LayoutInflater.from(context).inflate(R.layout.liste_rdv_pris, parent, false))
    }

    override fun onBindViewHolder(holder: rdvPushHolder, position: Int) {
        holder.datePris.text=formatter.format(data[position].date)
        holder.debutPris.text=data[position].debut
        holder.finPris.text=data[position].fin
        holder.patientPris.text=data[position].nom_patient + " " +data[position].prenom_patient

        val formatter: DateFormat = SimpleDateFormat("dd/MM/yyyy")
        val content = "${data[position].id_medecin},${data[position].prenom_patient} ${data[position].nom_patient},${formatter.format(data[position].date)},${data[position].debut},${data[position].fin}"
        val writer = QRCodeWriter()
        val bitMatrix = writer.encode(content, BarcodeFormat.QR_CODE, 200, 200)
        val width = bitMatrix.width
        val height = bitMatrix.height
        val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565)
        for (x in 0 until width) {
            for (y in 0 until height) {
                bitmap.setPixel(x, y, if (bitMatrix.get(x, y)) Color.BLACK else Color.WHITE)
            }
        }

        holder.qrConsultation.setImageBitmap(bitmap)
        materialAlertDialogBuilder = MaterialAlertDialogBuilder(context)
        holder.itemView.setOnClickListener {
            customAlertDialogView = LayoutInflater.from(context)
                .inflate(R.layout.detail_qr, null, false)
            customAlertDialogView.detail_qr_image.setImageBitmap(bitmap)
            // Launching the custom alert dialog
            launchCustomAlertDialog()

        }

    }

    override fun getItemCount()=data.size


}

class rdvPushHolder(view: View) : RecyclerView.ViewHolder(view) {

    val datePris = view.findViewById<TextView>(R.id.datePris)
    val debutPris = view.findViewById<TextView>(R.id.debutPris)
    val finPris = view.findViewById<TextView>(R.id.finPris)
    val patientPris = view.findViewById<TextView>(R.id.patientPris)
    val qrConsultation = view.findViewById<ImageView>(R.id.qrConsultation)


}


