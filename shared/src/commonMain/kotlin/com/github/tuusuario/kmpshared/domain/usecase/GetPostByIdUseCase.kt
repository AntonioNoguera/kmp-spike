package com.github.tuusuario.kmpshared.domain.usecase

import com.github.tuusuario.kmpshared.domain.model.Post
import com.github.tuusuario.kmpshared.domain.repository.PostRepository

class GetPostByIdUseCase(private val repository: PostRepository) {
    suspend operator fun invoke(id: Int): Post {
        return repository.getPostById(id)
    }
}
