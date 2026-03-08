package com.github.tuusuario.kmpshared.usecase

import com.github.tuusuario.kmpshared.FakePostRepository
import com.github.tuusuario.kmpshared.domain.model.Post
import com.github.tuusuario.kmpshared.domain.usecase.GetPostByIdUseCase
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class GetPostByIdUseCaseTest {

    private val fakeRepository = FakePostRepository()
    private val useCase = GetPostByIdUseCase(fakeRepository)

    @Test
    fun `invoke returns post when found`() = runTest {
        val expectedPost = Post(id = 1, userId = 1, title = "Title", body = "Body")
        fakeRepository.postToReturn = expectedPost

        val result = useCase(1)

        assertEquals(expectedPost, result)
    }

    @Test
    fun `invoke throws exception when post not found`() = runTest {
        fakeRepository.postToReturn = null

        val exception = assertFailsWith<Exception> {
            useCase(999)
        }

        assertEquals("Post not found", exception.message)
    }
}
