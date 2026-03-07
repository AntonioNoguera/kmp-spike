package com.github.tuusuario.kmpshared

import com.github.tuusuario.kmpshared.domain.model.Post
import com.github.tuusuario.kmpshared.domain.repository.PostRepository

class FakePostRepository : PostRepository {

    var postsToReturn: Result<List<Post>> = Result.success(emptyList())
    var postToReturn: Result<Post> = Result.failure(Exception("Not found"))

    override suspend fun getPosts(): Result<List<Post>> = postsToReturn

    override suspend fun getPostById(id: Int): Result<Post> = postToReturn
}
