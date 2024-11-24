package net.streamroutes.sreamroutesapp.core.domain.model

/**
 * Modelo usado para vincular un post con la información de su usuario
 */
data class PostWithInfo(
    val user: User,
    val post: Post
)