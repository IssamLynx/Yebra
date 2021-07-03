package com.example.projettdm.ui.affichageMedecins

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.projettdm.R
import com.example.projettdm.ui.UserActivity
import kotlinx.android.synthetic.main.fragment_rendez_vous.*


class DetailMedecin : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail_medecin, container, false)
    }


}
