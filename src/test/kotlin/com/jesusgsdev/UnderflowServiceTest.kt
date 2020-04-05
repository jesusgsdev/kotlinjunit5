@file:Suppress("ClassName")

package com.knowledgespike.questiontest

import com.knowledgespike.question.*
import io.mockk.*
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.junit5.MockKExtension
import org.amshove.kluent.`should be`
import org.amshove.kluent.`should throw`
import org.amshove.kluent.invoking
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.extension.ExtendWith
import java.lang.Exception

class UnderflowServiceTest {

    @Nested
    inner class `Underflow Service` {
        val mockUserRepository = mockk<IUserRepository>(relaxUnitFun = true)
        val mockQuestionRepository = mockk<IQuestionRepository>()

        val questionId = 20
        val voterId = 10

        val service = UnderflowService(mockQuestionRepository, mockUserRepository)

        @Test
        fun `should be able to voteUp question`() {
            val user = User(1, "Kevin")
            user.changeReputation(3000)

            val question = Question(1, user, "title", "body")
            question.vote(VoteEnum.Up)
            question.vote(VoteEnum.Up)
            every { mockQuestionRepository.findQuestion(questionId) } returns question
            every { mockQuestionRepository.update(question) } just Runs
            every { mockUserRepository.findUser(voterId) } returns user
            every { mockUserRepository.findUser(question.userId) } returns user
            every { mockUserRepository.update(user) } just Runs

            val votes = service.voteUpQuestion(questionId, voterId)

            votes `should be` 3
        }

        @Test
        fun `should also be able to voteUp question`() {
            val user = User(1, "Kevin")
            user.changeReputation(3000)

            val question = Question(1, user, "title", "body")
            question.vote(VoteEnum.Up)
            question.vote(VoteEnum.Up)
            every { mockQuestionRepository.findQuestion(questionId) } returns question
            every { mockQuestionRepository.update(question) } just Runs
            every { mockUserRepository.findUser(voterId) } returns user
            every { mockUserRepository.findUser(question.userId) } returns user

            val votes = service.voteUpQuestion(questionId, voterId)

            votes `should be` 3
        }

        @Test
        fun `should throw an exception if the question Id is invalid`() {
            val user = User(1, "Kevin")
            val question = Question(1, user, "title", "body")

            every { mockQuestionRepository.findQuestion(questionId) } throws Exception()

            invoking { service.voteUpQuestion(questionId, voterId) } `should throw` ServiceException::class
        }

        @Test
        fun `should throw an exception if the voter Id is invalid`() {
            val user = User(1, "Kevin")
            val question = Question(1, user, "title", "body")

            every { mockQuestionRepository.findQuestion(questionId) } returns question
            every { mockQuestionRepository.update(question) } just Runs
            every { mockUserRepository.findUser(voterId) } throws Exception()
            every { mockUserRepository.findUser(question.userId) } returns user

            invoking { service.voteUpQuestion(questionId, voterId) } `should throw` ServiceException::class
        }
    }

    @Nested
    inner class `With Annotations` {
        @RelaxedMockK
        lateinit var mockUserRepository: IUserRepository

        @MockK
        lateinit var mockQuestionRepository: IQuestionRepository

        init {
            MockKAnnotations.init(this)
        }

        val questionId = 20
        val voterId = 10

        val service = UnderflowService(mockQuestionRepository, mockUserRepository)

        @Test
        fun `should be able to voteUp question`() {
            val user = User(1, "Kevin")
            user.changeReputation(3000)

            val question = Question(1, user, "title", "body")
            question.vote(VoteEnum.Up)
            question.vote(VoteEnum.Up)
            every { mockQuestionRepository.findQuestion(questionId) } returns question
            every { mockQuestionRepository.update(question) } just Runs
            every { mockUserRepository.findUser(voterId) } returns user
            every { mockUserRepository.findUser(question.userId) } returns user
            every { mockUserRepository.update(user) } just Runs

            val votes = service.voteUpQuestion(questionId, voterId)

            votes `should be` 3
        }

        @Test
        fun `should also be able to voteUp question`() {
            val user = User(1, "Kevin")
            user.changeReputation(3000)

            val question = Question(1, user, "title", "body")
            question.vote(VoteEnum.Up)
            question.vote(VoteEnum.Up)
            every { mockQuestionRepository.findQuestion(questionId) } returns question
            every { mockQuestionRepository.update(question) } just Runs
            every { mockUserRepository.findUser(voterId) } returns user
            every { mockUserRepository.findUser(question.userId) } returns user

            val votes = service.voteUpQuestion(questionId, voterId)

            votes `should be` 3
        }

        @Test
        fun `should throw an exception if the question Id is invalid`() {
            val user = User(1, "Kevin")
            val question = Question(1, user, "title", "body")

            every { mockQuestionRepository.findQuestion(questionId) } throws Exception()

            invoking { service.voteUpQuestion(questionId, voterId) } `should throw` ServiceException::class
        }

        @Test
        fun `should throw an exception if the voter Id is invalid`() {
            val user = User(1, "Kevin")
            val question = Question(1, user, "title", "body")

            every { mockQuestionRepository.findQuestion(questionId) } returns question
            every { mockQuestionRepository.update(question) } just Runs
            every { mockUserRepository.findUser(voterId) } throws Exception()
            every { mockUserRepository.findUser(question.userId) } returns user

            invoking { service.voteUpQuestion(questionId, voterId) } `should throw` ServiceException::class
        }

    }


    @ExtendWith(MockKExtension::class)
    @Nested
    inner class JUnit5UnderflowServiceTest {
        @RelaxedMockK
        lateinit var mockUserRepository: IUserRepository

        @MockK
        lateinit var mockQuestionRepository: IQuestionRepository

        val questionId = 20
        val voterId = 10

        @Test
        fun `should be able to voteUp question`() {

            val service = UnderflowService(mockQuestionRepository, mockUserRepository)
            val user = User(1, "Kevin")
            user.changeReputation(3000)

            val question = Question(1, user, "title", "body")
            question.vote(VoteEnum.Up)
            question.vote(VoteEnum.Up)
            every { mockQuestionRepository.findQuestion(questionId) } returns question
            every { mockQuestionRepository.update(question) } just Runs
            every { mockUserRepository.findUser(voterId) } returns user
            every { mockUserRepository.findUser(question.userId) } returns user
            every { mockUserRepository.update(user) } just Runs

            val votes = service.voteUpQuestion(questionId, voterId)

            votes `should be` 3
        }

        @Test
        fun `should also be able to voteUp question`() {
            val service = UnderflowService(mockQuestionRepository, mockUserRepository)
            val user = User(1, "Kevin")
            user.changeReputation(3000)

            val question = Question(1, user, "title", "body")
            question.vote(VoteEnum.Up)
            question.vote(VoteEnum.Up)
            every { mockQuestionRepository.findQuestion(questionId) } returns question
            every { mockQuestionRepository.update(question) } just Runs
            every { mockUserRepository.findUser(voterId) } returns user
            every { mockUserRepository.findUser(question.userId) } returns user

            val votes = service.voteUpQuestion(questionId, voterId)

            votes `should be` 3
        }

        @Test
        fun `should throw an exception if the question Id is invalid`() {
            val service = UnderflowService(mockQuestionRepository, mockUserRepository)
            val user = User(1, "Kevin")
            val question = Question(1, user, "title", "body")

            every { mockQuestionRepository.findQuestion(questionId) } throws Exception()

            invoking { service.voteUpQuestion(questionId, voterId) } `should throw` ServiceException::class
        }

        @Test
        fun `should throw an exception if the voter Id is invalid`() {
            val service = UnderflowService(mockQuestionRepository, mockUserRepository)
            val user = User(1, "Kevin")
            val question = Question(1, user, "title", "body")

            every { mockQuestionRepository.findQuestion(questionId) } returns question
            every { mockQuestionRepository.update(question) } just Runs
            every { mockUserRepository.findUser(voterId) } throws Exception()
            every { mockUserRepository.findUser(question.userId) } returns user

            invoking { service.voteUpQuestion(questionId, voterId) } `should throw` ServiceException::class
        }
    }
}