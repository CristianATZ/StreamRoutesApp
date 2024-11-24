package net.streamroutes.sreamroutesapp.features.forum.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import net.streamroutes.sreamroutesapp.core.data.repository.ForumRepository
import net.streamroutes.sreamroutesapp.core.data.repository.UserRepository
import net.streamroutes.sreamroutesapp.core.domain.model.CommentWithInfo
import net.streamroutes.sreamroutesapp.core.domain.model.Post
import net.streamroutes.sreamroutesapp.core.domain.model.PostComment
import net.streamroutes.sreamroutesapp.core.domain.model.PostWithInfo
import javax.inject.Inject

@HiltViewModel
class ForumViewModel @Inject constructor(
    private val forumRepository: ForumRepository,
    private val userRepository: UserRepository
) : ViewModel() {
    // Variable usada para guardar todos los posts de la base de datos
    private val _posts = MutableStateFlow<List<PostWithInfo>?>(null)
    val posts: StateFlow<List<PostWithInfo>?> = _posts

    // Variable usada para saber si se creo un post
    private val _createdPost = MutableStateFlow<Boolean?>(null)
    val createdPost: StateFlow<Boolean?> = _createdPost

    // Variable usada para saber si se creo un post
    private val _createdComment = MutableStateFlow<Boolean?>(null)
    val createdComment: StateFlow<Boolean?> = _createdComment

    // Variable usada para guardar el post seleccionado
    private val _selectedPost = MutableStateFlow<PostWithInfo?>(null)
    val selectedPost: StateFlow<PostWithInfo?> = _selectedPost

    // Variable usada para guardar los comentarios de un post
    private val _comments = MutableStateFlow<List<CommentWithInfo>?>(null)
    val comments: StateFlow<List<CommentWithInfo>?> = _comments

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

    /**
     * Método usado para crear un post
     */
    fun createPost(post: Post){
        viewModelScope.launch {
            val currentUser = userRepository.getCurrentUser()
            if(currentUser != null) {
                post.idUser = currentUser.uid
                _createdPost.value = forumRepository.createPost(post)
                if(_createdPost.value == true){
                    getAllPosts()
                } else {
                    _createdPost.value = false
                }
            } else {
                _createdPost.value = false
            }
        }
    }


    /**
     * Método usado para crear un comentario
     */
    fun createComment(comment: PostComment){
        viewModelScope.launch {
            val currentUser = userRepository.getCurrentUser()
            if(currentUser != null && selectedPost.value != null){
                comment.idUser = currentUser.uid
                comment.idPost = selectedPost.value!!.post.idPost
                _createdComment.value = forumRepository.createComment(comment)
            }
        }
    }


    /**
     * Método usado para seleccionar un post
     */
    fun selectPost(post: PostWithInfo){
        _selectedPost.value = post
    }


    /**
     * Método usado para traer los comentarios de un post
     */
    fun getCommentsByPost(){
        viewModelScope.launch {
            _comments.value = selectedPost.value?.post?.let { forumRepository.getCommentsByPost(it.idPost) }
        }
    }


    /**
     * Método usado para obtener todos los posts guardados localmentre
     * en el dispositivo
     */
    fun getAllPostsLocal(){
        viewModelScope.launch {
            _posts.value = forumRepository.getAllPostsLocal()
        }
    }


    /**
     * Método usado para saber guardar un post en Room
     */
    fun savePost(){
        viewModelScope.launch {
            if(_selectedPost.value != null){
                forumRepository.savePost(_selectedPost.value!!)
            }
        }
    }
}