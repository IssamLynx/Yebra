package com.example.projettdm.ui

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.projettdm.R
import kotlinx.android.synthetic.main.activity_user.*
import androidx.navigation.ui.setupWithNavController
import com.example.projettdm.data.repositories.Pref
import com.example.projettdm.data.repositories.TraitementRepository
import com.example.projettdm.data.room.RoomService
import com.example.projettdm.ui.authentification.MainActivity
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.zxing.integration.android.IntentIntegrator
import com.google.zxing.integration.android.IntentResult
import kotlinx.android.synthetic.main.fragment_bottom_sheet_menu.view.*
import kotlinx.android.synthetic.main.fragment_qr_code_scanner.*

@Suppress("DEPRECATION")
class UserActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)
        bottom_navigatin_view.setupWithNavController(mainNavHostFragment.findNavController())

        buttonDeco.setOnClickListener {
            val dialog = BottomSheetDialog(this)
            val view = layoutInflater.inflate(R.layout.fragment_bottom_sheet_menu, null)
            dialog.setContentView(view)
            dialog.show()
            view.setOnClickListener {
                    Pref.clear()
                RoomService.database.getTraitementDao().deleteAllTraitements()
                val intent=Intent(this,MainActivity::class.java)
                startActivity(intent)
                finish()
            }

        }
    }


}
