package com.github.tuusuario.kmpshared.di

import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp

internal actual fun createPlatformHttpClient(): HttpClient {
    return HttpClient(OkHttp)
}
