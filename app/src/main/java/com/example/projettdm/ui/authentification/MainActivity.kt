package com.example.projettdm.ui.authentification

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.projettdm.R
import com.example.projettdm.data.model.authModel
import com.example.projettdm.data.repositories.Pref
import com.example.projettdm.ui.MedecinActivity
import com.example.projettdm.ui.UserActivity
import com.example.projettdm.ui.affichageMedecins.AuthViewModel
import com.example.projettdm.ui.affichageMedecins.MedecinViewModel
import androidx.core.os.HandlerCompat.postDelayed
import androidx.lifecycle.Observer
import com.example.projettdm.utils.type
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.delay as delay1

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val actionBar: ActionBar? = supportActionBar
        actionBar?.hide()
        if(Pref.get().equals("patient",true))
        {
            val intent=Intent(this,UserActivity::class.java)
            startActivity(intent)
            finish()
        }
        else
            if(Pref.get().equals("medecin",true))
            {
                val intent=Intent(this,MedecinActivity::class.java)
                startActivity(intent)
                finish()
            }
            setContentView(R.layout.activity_main)
//notification Compact
        type.observe(this, Observer {
            if(Pref.get().equals("patient",true))
            {
                val intent=Intent(this,UserActivity::class.java)
                startActivity(intent)
                finish()
            }
            else
            if(Pref.get().equals("medecin",true))
            {
                val intent=Intent(this,MedecinActivity::class.java)
                startActivity(intent)
                finish()
            }
        })


        connexionAuth.setOnClickListener {
            val viewModel = ViewModelProvider(this).get(AuthViewModel::class.java)
            val auth=authModel(telAuth.text.toString(),mdpAuth.text.toString())
            viewModel.authentifier(auth,this)


        }
        }
    }
