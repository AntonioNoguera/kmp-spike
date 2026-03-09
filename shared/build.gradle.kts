import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.plugin.mpp.apple.XCFramework

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.kotlinSerialization)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.skie)
    `maven-publish`
}

group = "com.github.AntonioNoguera"
version = System.getenv("VERSION") ?: "1.0.0-SNAPSHOT"

kotlin {
    androidTarget {
        compilations.all {
            compileTaskProvider.configure {
                compilerOptions {
                    jvmTarget.set(JvmTarget.JVM_17)
                }
            }
        }
        publishLibraryVariants("release")
    }

    val xcframeworkName = "KMPShared"
    val xcframework = XCFramework(xcframeworkName)

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = xcframeworkName
            isStatic = true
            xcframework.add(this)
        }
    }

    sourceSets {
        commonMain.dependencies {
            implementation(libs.ktor.client.core)
            implementation(libs.ktor.client.content.negotiation)
            implementation(libs.ktor.serialization.kotlinx.json)
            implementation(libs.kotlinx.coroutines.core)
            implementation(libs.kotlinx.serialization.json)
        }

        commonTest.dependencies {
            implementation(kotlin("test"))
            implementation(libs.kotlinx.coroutines.test)
        }

        androidMain.dependencies {
            implementation(libs.ktor.client.okhttp)
        }

        iosMain.dependencies {
            implementation(libs.ktor.client.darwin)
        }
    }
}

android {
    namespace = "com.github.tuusuario.kmpshared"
    compileSdk = 34

    defaultConfig {
        minSdk = 24
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

publishing {
    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/AntonioNoguera/kmp-spike")
            credentials {
                username = System.getenv("GITHUB_ACTOR") ?: ""
                password = System.getenv("PUBLISH_TOKEN") ?: ""
            }
        }
    }
}

afterEvaluate {
    publishing {
        publications {
            // Configurar artifactId para Android
            named<MavenPublication>("androidRelease") {
                groupId = "com.github.AntonioNoguera"
                artifactId = "kmp-spike"
            }
        }
    }

    // Deshabilitar publicaciones iOS y KMP metadata a GitHub Packages
    tasks.matching {
        it.name.startsWith("publishIos") && it.name.endsWith("ToGitHubPackagesRepository")
    }.configureEach {
        enabled = false
    }
    tasks.matching {
        it.name == "publishKotlinMultiplatformPublicationToGitHubPackagesRepository"
    }.configureEach {
        enabled = false
    }
}
