package net.streamroutes.sreamroutesapp.utils

import android.app.usage.StorageStatsManager
import android.content.Context
import android.os.Environment
import android.os.StatFs
import android.os.storage.StorageManager
import java.util.UUID

object StorageUtils {
    fun getTotalStorage(): Long {
        val stat = StatFs(Environment.getDataDirectory().path)
        val totalBytes = stat.blockSizeLong * stat.blockCountLong
        return totalBytes // Retorna en bytes
    }

    fun getAvailableStorage(): Long {
        val stat = StatFs(Environment.getDataDirectory().path)
        val availableBytes = stat.blockSizeLong * stat.availableBlocksLong
        return availableBytes // Retorna en bytes
    }

    fun getSystemUsedStorage(): Long {
        val stat = StatFs(Environment.getDataDirectory().path)
        val totalBytes = stat.blockSizeLong * stat.blockCountLong
        val availableBytes = stat.blockSizeLong * stat.availableBlocksLong
        val systemOccupiedBytes = totalBytes - availableBytes
        return systemOccupiedBytes // Retorna en bytes
    }

    fun getAppStorageUsage(context: Context): Long {
        val storageStatsManager = context.getSystemService(Context.STORAGE_STATS_SERVICE) as StorageStatsManager
        val storageManager = context.getSystemService(Context.STORAGE_SERVICE) as StorageManager
        val appUuid: UUID = storageManager.getUuidForPath(context.filesDir)
        val storageStats = storageStatsManager.queryStatsForUid(appUuid, android.os.Process.myUid())
        return storageStats.appBytes // Retorna en bytes el uso de la app
    }

    fun formatBytes(bytes: Long): String {
        val kilobyte = 1024L
        val megabyte = kilobyte * 1024
        val gigabyte = megabyte * 1024

        return when {
            bytes >= gigabyte -> "${bytes / gigabyte} GB"
            bytes >= megabyte -> "${bytes / megabyte} MB"
            bytes >= kilobyte -> "${bytes / kilobyte} KB"
            else -> "$bytes B"
        }
    }
}