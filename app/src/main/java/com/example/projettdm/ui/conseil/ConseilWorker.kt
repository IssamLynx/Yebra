package com.example.projettdm.ui.conseil

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.projettdm.data.model.Conseil
import com.example.projettdm.data.repositories.ConseilRepository

class ConseilWorker(val ctx : Context, val paramters: WorkerParameters ): Worker(ctx,paramters) {
    override fun doWork(): Result {
        Log.i("Worker","Workde")

        ConseilRepository.createConseil(Conseil(inputData.getInt("id_medecin",-99),inputData.getInt("id_patient",-99),inputData.getString("contenu")!!),ctx)
        return Result.success()
    }
}