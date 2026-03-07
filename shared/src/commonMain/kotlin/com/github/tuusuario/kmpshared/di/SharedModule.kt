package com.github.tuusuario.kmpshared.di

import com.github.tuusuario.kmpshared.data.remote.PostApi
import com.github.tuusuario.kmpshared.data.repository.PostRepositoryImpl
import com.github.tuusuario.kmpshared.domain.repository.PostRepository
import com.github.tuusuario.kmpshared.domain.usecase.GetPostByIdUseCase
import com.github.tuusuario.kmpshared.domain.usecase.GetPostsUseCase
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class SharedModule {

    private val httpClient: HttpClient by lazy { createHttpClient() }

    private val postApi: PostApi by lazy { PostApi(httpClient) }

    private val postRepository: PostRepository by lazy { PostRepositoryImpl(postApi) }

    fun getPostsUseCase(): GetPostsUseCase = GetPostsUseCase(postRepository)

    fun getPostByIdUseCase(): GetPostByIdUseCase = GetPostByIdUseCase(postRepository)
}

internal expect fun createPlatformHttpClient(): HttpClient

internal fun createHttpClient(): HttpClient {
    return createPlatformHttpClient().config {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
                isLenient = true
            })
        }
    }
}
