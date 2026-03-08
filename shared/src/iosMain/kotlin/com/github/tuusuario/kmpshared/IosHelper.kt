package com.github.tuusuario.kmpshared

import com.github.tuusuario.kmpshared.di.SharedModule
import com.github.tuusuario.kmpshared.domain.model.Post
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

/**
 * iOS-friendly wrapper that handles coroutines and provides callback-based API
 */
class IosSharedModule {
    private val sharedModule = SharedModule()
    private val scope = MainScope()

    fun getPosts(
        onSuccess: (List<Post>) -> Unit,
        onError: (String) -> Unit
    ) {
        scope.launch {
            try {
                val posts = sharedModule.getPostsUseCase().invoke()
                onSuccess(posts)
            } catch (e: Exception) {
                onError(e.message ?: "Unknown error")
            }
        }
    }

    fun getPostById(
        id: Int,
        onSuccess: (Post) -> Unit,
        onError: (String) -> Unit
    ) {
        scope.launch {
            try {
                val post = sharedModule.getPostByIdUseCase().invoke(id)
                onSuccess(post)
            } catch (e: Exception) {
                onError(e.message ?: "Unknown error")
            }
        }
    }
}
