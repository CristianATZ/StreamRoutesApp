package net.ivanvega.milocationymapascompose.network

import android.util.Log
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.WebSocket
import okhttp3.WebSocketListener
import okio.ByteString
import java.util.concurrent.TimeUnit

class WebSocket {
    // variable para manejar si esta conectado
    private val _isConnected = MutableStateFlow(false)
    val isConnected = _isConnected.asStateFlow()

    private val _messages = MutableStateFlow(emptyList<Pair<Boolean, String>>())
    val messages = _messages.asStateFlow()

    // variable para la peticion
    private val okHttpClient = OkHttpClient.Builder()
        .connectTimeout(10, TimeUnit.SECONDS)
        .readTimeout(10, TimeUnit.SECONDS)
        .writeTimeout(10, TimeUnit.SECONDS)
        .build()
    private var webSocket: WebSocket? = null

    private val webSocketListener = object : WebSocketListener() {
        override fun onOpen(webSocket: okhttp3.WebSocket, response: Response) {
            super.onOpen(webSocket, response)
            _isConnected.value = true
            webSocket.send("Cliente conectado")
            Log.d("SERVIDOR", "CONECTADO EXITOSAMENTE")
        }

        override fun onMessage(webSocket: WebSocket, text: String) {
            super.onMessage(webSocket, text)
            _messages.update {
                val list = it.toMutableList()
                list.add(false to text)
                list
            }
        }

        override fun onClosing(webSocket: okhttp3.WebSocket, code: Int, reason: String) {
            super.onClosing(webSocket, code, reason)
            Log.d("SERVIDOR", "CERARNDO EXITOSAMENTE")
        }

        override fun onClosed(webSocket: okhttp3.WebSocket, code: Int, reason: String) {
            super.onClosed(webSocket, code, reason)
            _isConnected.value = false
            Log.d("SERVIDOR", "CERRADO EXITOSAMENTE")
        }

        override fun onFailure(webSocket: okhttp3.WebSocket, t: Throwable, response: Response?) {
            super.onFailure(webSocket, t, response)
            _isConnected.value = false
            Log.e("SERVIDOR", "FALLIDO: ${t.message}", t)
            response?.let {
                Log.e("SERVIDOR", "RESPUESTA DE FALLA: ${response.message}")
            }
        }
    }

    fun connect() {
        if(_isConnected.value){
            Log.d("SERVIDOR", "Ya estas conectado")
        } else {
            val wsURL = "wss://free.blr2.piesocket.com/v3/1?api_key=2FVm4sDGxlAj7bgZfljY0uPOLlITYbgvsFfLHMwq&notify_self=1"

            val request = Request.Builder()
                .url(wsURL)
                .build()

            webSocket = okHttpClient.newWebSocket(request, webSocketListener)
            Log.d("SERVIDOR", "INTENTANDO CONECTAR A: $wsURL")
        }
    }

    fun disconnect() {
        if(!_isConnected.value) {
            Log.d("SERVIDOR", "Estas desconectado")
        } else {
            webSocket?.close(1000, "Me fui")
            Log.d("SERVIDOR", "Desconectado desde cliente")
        }
    }

    fun send(text: String) {
        if (_isConnected.value) {
            webSocket?.send(text)
        }
    }
}