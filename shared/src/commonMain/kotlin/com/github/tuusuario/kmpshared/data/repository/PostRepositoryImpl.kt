package com.github.tuusuario.kmpshared.data.repository

import com.github.tuusuario.kmpshared.data.remote.PostApi
import com.github.tuusuario.kmpshared.domain.model.Post
import com.github.tuusuario.kmpshared.domain.repository.PostRepository

internal class PostRepositoryImpl(
    private val api: PostApi
) : PostRepository {

    override suspend fun getPosts(): Result<List<Post>> {
        return runCatching {
            api.getPosts().map { it.toDomain() }
        }
    }

    override suspend fun getPostById(id: Int): Result<Post> {
        return runCatching {
            api.getPostById(id).toDomain()
        }
    }
}
