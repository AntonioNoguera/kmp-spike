package com.github.tuusuario.kmpshared

import com.github.tuusuario.kmpshared.domain.model.Post
import com.github.tuusuario.kmpshared.domain.repository.PostRepository

class FakePostRepository : PostRepository {

    var postsToReturn: List<Post> = emptyList()
    var postToReturn: Post? = null
    var errorToThrow: Exception? = null

    override suspend fun getPosts(): List<Post> {
        errorToThrow?.let { throw it }
        return postsToReturn
    }

    override suspend fun getPostById(id: Int): Post {
        errorToThrow?.let { throw it }
        return postToReturn ?: throw Exception("Post not found")
    }
}
