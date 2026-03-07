package com.github.tuusuario.kmpshared.usecase

import com.github.tuusuario.kmpshared.FakePostRepository
import com.github.tuusuario.kmpshared.domain.model.Post
import com.github.tuusuario.kmpshared.domain.usecase.GetPostByIdUseCase
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class GetPostByIdUseCaseTest {

    private val fakeRepository = FakePostRepository()
    private val useCase = GetPostByIdUseCase(fakeRepository)

    @Test
    fun `invoke returns success with post when found`() = runTest {
        val expectedPost = Post(id = 1, userId = 1, title = "Title", body = "Body")
        fakeRepository.postToReturn = Result.success(expectedPost)

        val result = useCase(1)

        assertTrue(result.isSuccess)
        assertEquals(expectedPost, result.getOrNull())
    }

    @Test
    fun `invoke returns failure when post not found`() = runTest {
        fakeRepository.postToReturn = Result.failure(Exception("Not found"))

        val result = useCase(999)

        assertTrue(result.isFailure)
        assertEquals("Not found", result.exceptionOrNull()?.message)
    }
}
