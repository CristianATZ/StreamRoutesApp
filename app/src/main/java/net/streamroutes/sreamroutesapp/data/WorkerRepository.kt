package net.streamroutes.sreamroutesapp.data

import android.content.Context
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.Worker

interface WorkerRepository {
    // cambiar nombre en base a lo que hagamos jsjsjsjsjsjs
    fun guardadoWorker()

    //val outputWorkGuardado: Flow<WorkInfo>
}

class WorkerSupervisor(private val context : Context) : WorkerRepository{
    private val workManager = WorkManager.getInstance(context)

    override fun guardadoWorker() {
        var pullLogin = OneTimeWorkRequestBuilder<Worker>()
            .addTag("PullInfoWorker")
            .build()

        var saveLogin = OneTimeWorkRequestBuilder<Worker>()
            .addTag("SaveInfoWorker")
            .build()

        workManager.beginUniqueWork(
            "PullSaveLogin",
            ExistingWorkPolicy.REPLACE,
            pullLogin
        ).then(saveLogin).enqueue()
    }
}