package com.github.tuusuario.kmpshared.data.model

import com.github.tuusuario.kmpshared.domain.model.Post
import kotlinx.serialization.Serializable

@Serializable
internal data class PostDto(
    val id: Int,
    val userId: Int,
    val title: String,
    val body: String
) {
    fun toDomain(): Post = Post(
        id = id,
        userId = userId,
        title = title,
        body = body
    )
}
