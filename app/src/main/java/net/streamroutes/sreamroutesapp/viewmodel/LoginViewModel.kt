package net.streamroutes.sreamroutesapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import net.streamroutes.sreamroutesapp.data.AppContainer
import net.streamroutes.sreamroutesapp.data.RemoteContainer

class LoginViewModel() : ViewModel() {


    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as RemoteContainer)
                val remoteApplicaction = application.container.remoteRepository
                val alumnosAplicationDB = application.container.offlineRepository
                //val workerAplication = application.container.workerRepository
                LoginViewModel()
            }
        }
    }
}

data class LoginState(
    val phone : String,
    val pass : String
)