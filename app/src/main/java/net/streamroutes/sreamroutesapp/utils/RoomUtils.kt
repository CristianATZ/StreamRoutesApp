package net.streamroutes.sreamroutesapp.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import net.streamroutes.sreamroutesapp.core.data.local.entity.PostEntity
import net.streamroutes.sreamroutesapp.core.data.local.entity.UserEntity
import net.streamroutes.sreamroutesapp.core.data.repository.PostWithInfo
import net.streamroutes.sreamroutesapp.core.domain.model.Post

/**
 * Método usado para saber si un usuario cuenta con internet o no
 */
fun isOnline(context: Context): Boolean {
    val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val capabilitites = connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
    return capabilitites?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) == true
}
