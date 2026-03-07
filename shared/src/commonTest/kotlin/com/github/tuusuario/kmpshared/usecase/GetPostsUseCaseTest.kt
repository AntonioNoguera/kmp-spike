package com.github.tuusuario.kmpshared.usecase

import com.github.tuusuario.kmpshared.FakePostRepository
import com.github.tuusuario.kmpshared.domain.model.Post
import com.github.tuusuario.kmpshared.domain.usecase.GetPostsUseCase
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class GetPostsUseCaseTest {

    private val fakeRepository = FakePostRepository()
    private val useCase = GetPostsUseCase(fakeRepository)

    @Test
    fun `invoke returns success with posts when repository succeeds`() = runTest {
        val expectedPosts = listOf(
            Post(id = 1, userId = 1, title = "Title 1", body = "Body 1"),
            Post(id = 2, userId = 1, title = "Title 2", body = "Body 2")
        )
        fakeRepository.postsToReturn = Result.success(expectedPosts)

        val result = useCase()

        assertTrue(result.isSuccess)
        assertEquals(expectedPosts, result.getOrNull())
    }

    @Test
    fun `invoke returns success with empty list when no posts`() = runTest {
        fakeRepository.postsToReturn = Result.success(emptyList())

        val result = useCase()

        assertTrue(result.isSuccess)
        assertEquals(emptyList(), result.getOrNull())
    }

    @Test
    fun `invoke returns failure when repository fails`() = runTest {
        val expectedException = Exception("Network error")
        fakeRepository.postsToReturn = Result.failure(expectedException)

        val result = useCase()

        assertTrue(result.isFailure)
        assertEquals("Network error", result.exceptionOrNull()?.message)
    }
}
