package com.github.tuusuario.kmpshared.data.remote

import com.github.tuusuario.kmpshared.data.model.PostDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

internal class PostApi(private val client: HttpClient) {

    private companion object {
        const val BASE_URL = "https://jsonplaceholder.typicode.com"
    }

    suspend fun getPosts(): List<PostDto> {
        return client.get("$BASE_URL/posts").body()
    }

    suspend fun getPostById(id: Int): PostDto {
        return client.get("$BASE_URL/posts/$id").body()
    }
}
