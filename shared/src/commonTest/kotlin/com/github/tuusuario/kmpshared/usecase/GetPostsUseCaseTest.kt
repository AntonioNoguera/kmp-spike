package com.github.tuusuario.kmpshared.usecase

import com.github.tuusuario.kmpshared.FakePostRepository
import com.github.tuusuario.kmpshared.domain.model.Post
import com.github.tuusuario.kmpshared.domain.usecase.GetPostsUseCase
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class GetPostsUseCaseTest {

    private val fakeRepository = FakePostRepository()
    private val useCase = GetPostsUseCase(fakeRepository)

    @Test
    fun `invoke returns posts when repository succeeds`() = runTest {
        val expectedPosts = listOf(
            Post(id = 1, userId = 1, title = "Title 1", body = "Body 1"),
            Post(id = 2, userId = 1, title = "Title 2", body = "Body 2")
        )
        fakeRepository.postsToReturn = expectedPosts

        val result = useCase()

        assertEquals(expectedPosts, result.toList())
    }

    @Test
    fun `invoke returns empty list when no posts`() = runTest {
        fakeRepository.postsToReturn = emptyList()

        val result = useCase()

        assertEquals(emptyList(), result.toList())
    }

    @Test
    fun `invoke throws exception when repository fails`() = runTest {
        fakeRepository.errorToThrow = Exception("Network error")

        val exception = assertFailsWith<Exception> {
            useCase()
        }

        assertEquals("Network error", exception.message)
    }
}
