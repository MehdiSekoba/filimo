package com.mehdisekoba.filimo.utils.network

import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

class NetworkChecker @Inject constructor(
    private val manager: ConnectivityManager,
    private val request: NetworkRequest
) : ConnectivityManager.NetworkCallback() {
    private val isNetworkAvailable = MutableStateFlow(false)
    private var capabilities: NetworkCapabilities? = null

    fun checkNetwork(): MutableStateFlow<Boolean> {
        // Register
        manager.registerNetworkCallback(request, this)
        // Active network
        val activeNetwork = manager.activeNetwork
        if (activeNetwork == null) {
            isNetworkAvailable.value = false
            return isNetworkAvailable
        }
        // Capabilities
        capabilities = manager.getNetworkCapabilities(activeNetwork)
        if (capabilities == null) {
            isNetworkAvailable.value = false
            return isNetworkAvailable
        }
        isNetworkAvailable.value =
            when {
                capabilities!!.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                capabilities!!.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                else -> false
            }
        return isNetworkAvailable
    }

    override fun onAvailable(network: Network) {
        isNetworkAvailable.value = true
    }

    override fun onLost(network: Network) {
        isNetworkAvailable.value = false
    }
}