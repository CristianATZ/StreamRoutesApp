package net.ivanvega.milocationymapascompose.network

import android.util.Log
import androidx.lifecycle.ViewModel

class WebSocketViewModel() : ViewModel(){

    private val ws = WebSocket()
    val socketStatus = ws.isConnected
    val messages = ws.messages

    fun sendText(text: String){
        ws.send(text)
    }

    fun connect(){
        ws.connect()
    }

    fun disconnect(){
        ws.disconnect()
    }
}