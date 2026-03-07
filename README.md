# KMP Shared Library

Módulo Kotlin Multiplatform que expone UseCases para consumir la API de JSONPlaceholder.

- **iOS**: Distribución via SPM (binary target + GitHub Releases)
- **Android**: Distribución via GitHub Packages (Maven)

## Arquitectura

El módulo expone solo la capa `domain`:
- `Post` - Modelo de dominio
- `GetPostsUseCase` - Obtener lista de posts
- `GetPostByIdUseCase` - Obtener un post por ID
- `SharedModule` - Factory para crear UseCases

La capa `data` es `internal` y no está expuesta a los consumidores.

## Consumir en iOS

### 1. Agregar dependencia via SPM

En Xcode: **File > Add Package Dependencies**

URL del repositorio:
```
https://github.com/AntonioNoguera/kmp-spike
```

Seleccionar la versión deseada.

### 2. Uso en código

```swift
import KMPShared

// En tu ViewModel nativo
class PostsViewModel: ObservableObject {
    private let getPosts = SharedModule().getPostsUseCase()

    @Published var posts: [Post] = []

    func loadPosts() {
        Task {
            let result = try await getPosts.invoke()
            // Manejar result...
        }
    }
}
```

## Consumir en Android

### 1. Configurar repositorio

En `settings.gradle.kts`:

```kotlin
dependencyResolutionManagement {
    repositories {
        maven {
            url = uri("https://maven.pkg.github.com/AntonioNoguera/kmp-spike")
            credentials {
                username = providers.gradleProperty("gpr.user").orNull
                    ?: System.getenv("GITHUB_ACTOR")
                password = providers.gradleProperty("gpr.token").orNull
                    ?: System.getenv("PUBLISH_TOKEN")
            }
        }
    }
}
```

### 2. Agregar dependencia

En `build.gradle.kts` del módulo:

```kotlin
dependencies {
    implementation("com.github.AntonioNoguera:kmp-spike:1.0.0")
}
```

### 3. Uso en código

```kotlin
import com.github.tuusuario.kmpshared.di.SharedModule

class PostsViewModel : ViewModel() {
    private val getPosts = SharedModule().getPostsUseCase()

    private val _posts = MutableStateFlow<List<Post>>(emptyList())
    val posts: StateFlow<List<Post>> = _posts.asStateFlow()

    fun loadPosts() {
        viewModelScope.launch {
            getPosts()
                .onSuccess { posts -> _posts.value = posts }
                .onFailure { error -> /* manejar error */ }
        }
    }
}
```

## Hacer un nuevo release

```bash
# 1. Asegurar que los tests pasan
./gradlew :shared:allTests

# 2. Crear y pushear tag
git tag v1.0.0
git push origin v1.0.0

# El workflow de GitHub Actions hace el resto:
# - Corre tests
# - Genera XCFramework
# - Actualiza Package.swift
# - Publica a GitHub Packages
# - Crea GitHub Release con el .xcframework.zip
```

## Desarrollo local

### Build
```bash
./gradlew :shared:build
```

### Tests
```bash
./gradlew :shared:allTests
```

### Build XCFramework local
```bash
./shared/scripts/build_xcframework.sh
```

## Estructura del proyecto

```
shared/
├── src/
│   ├── commonMain/kotlin/
│   │   ├── data/           # Internal - no expuesto
│   │   │   ├── model/
│   │   │   ├── remote/
│   │   │   └── repository/
│   │   ├── domain/         # Public API
│   │   │   ├── model/
│   │   │   ├── repository/
│   │   │   └── usecase/
│   │   └── di/
│   ├── commonTest/kotlin/
│   ├── androidMain/kotlin/
│   └── iosMain/kotlin/
├── build.gradle.kts
├── Package.swift
└── scripts/
    └── build_xcframework.sh
```

## Dependencias

- Ktor Client - Networking
- kotlinx.serialization - JSON parsing
- kotlinx.coroutines - Async
