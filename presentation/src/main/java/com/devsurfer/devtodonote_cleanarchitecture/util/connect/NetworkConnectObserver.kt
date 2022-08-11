package com.devsurfer.devtodonote_cleanarchitecture.util.connect

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

/**
 * Created by Jaehyeon on 2022/08/11.
 */
@ExperimentalCoroutinesApi
class NetworkConnectObserver(
    private val context: Context
): ConnectObserver {

    private val connectManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    override fun observer(): Flow<ConnectObserver.Status> {
        return callbackFlow {
            val callback = object : ConnectivityManager.NetworkCallback() {
                override fun onAvailable(network: Network) {
                    super.onAvailable(network)
                    launch { send(ConnectObserver.Status.Available) }
                }

                override fun onLosing(network: Network, maxMsToLive: Int) {
                    super.onLosing(network, maxMsToLive)
                    launch { send(ConnectObserver.Status.Losing) }
                }

                override fun onLost(network: Network) {
                    super.onLost(network)
                    launch { send(ConnectObserver.Status.Lost) }
                }

                override fun onUnavailable() {
                    super.onUnavailable()
                    launch { send(ConnectObserver.Status.Unavailable) }
                }
            }

            connectManager.registerDefaultNetworkCallback(callback)
            awaitClose {
                connectManager.unregisterNetworkCallback(callback)
            }
        }.distinctUntilChanged()
    }
}