package com.github.tuusuario.kmpshared.data.repository

import com.github.tuusuario.kmpshared.data.remote.PostApi
import com.github.tuusuario.kmpshared.domain.model.Post
import com.github.tuusuario.kmpshared.domain.repository.PostRepository

internal class PostRepositoryImpl(
    private val api: PostApi
) : PostRepository {

    override suspend fun getPosts(): List<Post> {
        return api.getPosts().map { it.toDomain() }
    }

    override suspend fun getPostById(id: Int): Post {
        return api.getPostById(id).toDomain()
    }
}
