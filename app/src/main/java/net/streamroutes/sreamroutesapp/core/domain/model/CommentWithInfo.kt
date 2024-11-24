package net.streamroutes.sreamroutesapp.core.domain.model

/**
 * Modelo usado para vincular un usuario con un comentario de un post
 */
data class CommentWithInfo(
    val user: User,
    val comment: PostComment
)