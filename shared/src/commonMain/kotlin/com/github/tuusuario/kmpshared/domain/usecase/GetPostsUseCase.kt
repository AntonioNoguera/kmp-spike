package com.github.tuusuario.kmpshared.domain.usecase

import com.github.tuusuario.kmpshared.domain.model.Post
import com.github.tuusuario.kmpshared.domain.repository.PostRepository

class GetPostsUseCase(private val repository: PostRepository) {
    suspend operator fun invoke(): Array<Post> {
        return repository.getPosts().toTypedArray()
    }
}
