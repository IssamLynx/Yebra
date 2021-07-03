package com.example.projettdm.ui.qrCodeScanner

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.projettdm.R
import com.google.zxing.integration.android.IntentIntegrator
import com.google.zxing.integration.android.IntentResult
import kotlinx.android.synthetic.main.fragment_qr_code_scanner.*


class QrCodeScanner : Fragment() {
lateinit var intentIntegrator: IntentIntegrator
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_qr_code_scanner, container, false)
    }






    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        intentIntegrator= IntentIntegrator(requireActivity())
        Log.i("testosa","eeeeeeeeee")
        scanQr.setOnClickListener {
            intentIntegrator.setBeepEnabled(true).initiateScan()
        }
    }

}