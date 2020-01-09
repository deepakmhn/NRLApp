package com.foxsports.nrlapp.util

import android.content.Context
import android.net.ConnectivityManager
import javax.inject.Inject

/**
 * Helper class to check the network connectivity status
 */
class NetManager @Inject constructor(var applicationContext: Context) {

    val isConnectedToInternet: Boolean?
        get() {
            val connectionManager = applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val networkInfo = connectionManager.activeNetworkInfo
            return networkInfo != null && networkInfo.isConnected
        }
}