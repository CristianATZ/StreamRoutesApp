package net.streamroutes.sreamroutesapp.features.settings.presentation.apparence

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import net.streamroutes.sreamroutesapp.features.settings.data.ApparenceRepositoryImp
import javax.inject.Inject

@HiltViewModel
class ApparenceViewModel @Inject constructor(
    private val apparenceRepository: ApparenceRepositoryImp
) : ViewModel() {
    val theme: StateFlow<Boolean> = apparenceRepository.themeMode
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000L),
            initialValue = false
        )

    val dynaminc: StateFlow<Boolean> = apparenceRepository.dynamicTheme
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000L),
            initialValue = false
        )

    fun changeTheme(theme: Boolean) {
        viewModelScope.launch {
            apparenceRepository.changeTheme(theme)
        }
    }

    fun enableDynamictheme(dynamic: Boolean) {
        viewModelScope.launch {
            apparenceRepository.enableDynamicTheme(dynamic)
        }
    }
}