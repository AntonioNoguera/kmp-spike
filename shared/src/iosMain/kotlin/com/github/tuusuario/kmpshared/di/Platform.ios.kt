package com.github.tuusuario.kmpshared.di

import io.ktor.client.HttpClient
import io.ktor.client.engine.darwin.Darwin

internal actual fun createPlatformHttpClient(): HttpClient {
    return HttpClient(Darwin)
}
