package com.example.projettdm.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.projettdm.R
import com.example.projettdm.data.repositories.Pref
import com.example.projettdm.ui.authentification.MainActivity
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.zxing.integration.android.IntentIntegrator
import com.google.zxing.integration.android.IntentResult
import kotlinx.android.synthetic.main.activity_medecin.*
import kotlinx.android.synthetic.main.fragment_qr_code_scanner.*


class MedecinActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_medecin)
        bottom_navigatin_Medecin.setupWithNavController(fragmentContainerView4.findNavController())
        buttonDecoM.setOnClickListener {
            val dialog = BottomSheetDialog(this)
            val view = layoutInflater.inflate(R.layout.fragment_bottom_sheet_menu, null)
            dialog.setContentView(view)
            dialog.show()
            view.setOnClickListener {
                Pref.clear()
                val intent= Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }

        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        val intentResult: IntentResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (intentResult != null) {
            if (intentResult.contents != null) {
                val delim = ","
                val result = intentResult.contents.split(delim)
                Log.i("testosa",intentResult.contents)

                medecinQr.setText(result[0]+" "+result[1])
                debutQr.setText(result[3])
                finQr.setText(result[4])
            } else {
                medecinQr.setText("nothing")
            }
        }
        super.onActivityResult(requestCode, resultCode, data);

    }
}