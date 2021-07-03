package com.example.projettdm.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.projettdm.R
import com.example.projettdm.ui.affichageMedecins.ListeMedecins
import com.example.projettdm.ui.traitements.TraitementsFragment
import kotlinx.android.synthetic.main.activity_user.*
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.zxing.integration.android.IntentIntegrator
import com.google.zxing.integration.android.IntentResult
import kotlinx.android.synthetic.main.fragment_qr_code_scanner.*

@Suppress("DEPRECATION")
class UserActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)
        bottom_navigatin_view.setupWithNavController(mainNavHostFragment.findNavController())
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Log.i("testosa","eeeeeeeeee")

        val intentResult: IntentResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (intentResult != null) {
            if (intentResult.contents != null) {
                textView15.text=intentResult.contents
                Log.i("testosa",intentResult.contents)

            } else {
                textView15.setText("nothing")
            }
        }
        super.onActivityResult(requestCode, resultCode, data);

    }
}
