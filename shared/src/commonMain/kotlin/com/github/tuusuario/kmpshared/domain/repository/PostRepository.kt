package com.github.tuusuario.kmpshared.domain.repository

import com.github.tuusuario.kmpshared.domain.model.Post

interface PostRepository {
    suspend fun getPosts(): List<Post>
    suspend fun getPostById(id: Int): Post
}
