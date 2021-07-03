package com.example.projettdm.ui.rendezVous

import android.graphics.Bitmap
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.projettdm.R
import com.example.projettdm.ui.UserActivity
import com.example.projettdm.ui.affichageMedecins.MedecinViewModel
import com.example.projettdm.ui.affichageMedecins.RdvViewModel
import com.google.zxing.BarcodeFormat
import com.google.zxing.qrcode.QRCodeWriter
import kotlinx.android.synthetic.main.fragment_rdv_done.*
import java.text.DateFormat
import java.text.SimpleDateFormat

class RdvDone : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_rdv_done, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val vm1 = ViewModelProvider(context as UserActivity).get(MedecinViewModel::class.java)
        val vm2 = ViewModelProvider(context as UserActivity).get(RdvViewModel::class.java)
        val formatter: DateFormat = SimpleDateFormat("dd/MM/yyyy")
        val content = "${vm1.medecin.nom},${vm1.medecin.prenom},${formatter.format(vm2.rdv.date)},${vm2.rdv.temps_debut},${vm2.rdv.dure}"

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
        qrcode.setImageBitmap(bitmap)

    }
}