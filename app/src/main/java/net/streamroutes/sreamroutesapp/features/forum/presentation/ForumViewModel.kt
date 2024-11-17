package net.streamroutes.sreamroutesapp.features.forum.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import net.streamroutes.sreamroutesapp.core.data.repository.ForumRepository
import net.streamroutes.sreamroutesapp.core.data.repository.PostWithInfo
import net.streamroutes.sreamroutesapp.core.data.repository.UserRepository
import net.streamroutes.sreamroutesapp.core.domain.model.Post
import javax.inject.Inject

@HiltViewModel
class ForumViewModel @Inject constructor(
    private val forumRepository: ForumRepository
) : ViewModel() {
    // Variable usada para guardar todos los posts de la base de datos
    private val _posts = MutableStateFlow<List<PostWithInfo>?>(null)
    val posts: StateFlow<List<PostWithInfo>?> = _posts

    init {
        getAllPosts()
    }

    /**
     * Método usado para obtener todos los posts de todos los usuarios
     */
    fun getAllPosts(){
        viewModelScope.launch {
            _posts.value = forumRepository.getAllPosts()
        }
    }
}