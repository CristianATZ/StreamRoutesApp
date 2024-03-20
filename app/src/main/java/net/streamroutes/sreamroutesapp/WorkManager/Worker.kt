package net.streamroutes.sreamroutesapp.WorkManager

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import net.streamroutes.sreamroutesapp.R
import net.streamroutes.sreamroutesapp.utils.makeStatusNotification

class Worker(ctx: Context, params: WorkerParameters) : CoroutineWorker(ctx, params) {
    override suspend fun doWork(): Result {

        // hace notificacion
        makeStatusNotification(
            applicationContext.resources.getString(R.string.app_name),
            applicationContext
        )

        return withContext(Dispatchers.IO) {

            delay(3000)

            return@withContext try {
                Result.success()
            } catch (throwable: Throwable) {
                Result.failure()
            }
        }


    }




}