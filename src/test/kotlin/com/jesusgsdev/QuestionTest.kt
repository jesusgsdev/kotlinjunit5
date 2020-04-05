@file:Suppress("ClassName")

package com.knowledgespike.questiontest

import com.knowledgespike.question.Answer
import com.knowledgespike.question.Question
import com.knowledgespike.question.QuestionException
import com.knowledgespike.question.User
import org.amshove.kluent.*
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.MethodSource

fun invoking(function: () -> Any?): () -> Any? {
    return function
}

class QuestionTest {

    @Nested
    inner class isValid {

        private val user = User(1, "Kevin")
        val question = Question(1, user, "title", "body")

        @Test
        fun `should be true if title and question are set`() {
            invoking { Question(1, user, "title", "question") } `should not throw` AnyException
        }

        @Test
        fun `should be false if title is empty`() {
            invoking { Question(1, user, "", "question") } `should throw` QuestionException::class
        }

        @Test
        fun `should be false if title is blank`() {
            invoking { Question(1, user, " ", "question") } `should throw` QuestionException::class
        }

        @Test
        fun `should be false if question is empty`() {
            invoking { Question(1, user, "title", "") } `should throw` QuestionException::class
        }

        @Test
        fun `should be false if question is blank`() {
            invoking { Question(1, user, "title", " ") } `should throw` QuestionException::class
        }

        @ParameterizedTest
        @CsvSource("'',question",
                "' ',question",
                "title,''",
                "title,' '")
        fun `should be false if either question or title is blank or empty`(title: String, question: String) {
            invoking { Question(1, user, title, question) } `should throw` QuestionException::class
        }

        @Nested
        @TestInstance(TestInstance.Lifecycle.PER_CLASS)
        inner class `isValid using methodSource` {

            @Suppress("unused")
            fun titlesAndQuestions() = listOf(
                    Arguments.of("", "question"),
                    Arguments.of(" ", "question"),
                    Arguments.of("title", ""),
                    Arguments.of("title", " ")
            )

            @ParameterizedTest
            @MethodSource("titlesAndQuestions")
            fun `should also be false if either question or title is blank or empty`(title: String, question: String) {
                invoking { Question(1, user, title, question) } `should throw` QuestionException::class
            }
        }

        @Nested
        @KotlinParameterizedTest
        inner class `isValid using kotlin annotation` {
            @Suppress("unused")
            fun titlesAndQuestions() = listOf(
                    Arguments.of("", "question"),
                    Arguments.of(" ", "question"),
                    Arguments.of("title", ""),
                    Arguments.of("title", " ")
            )

            @ParameterizedTest
            @MethodSource("titlesAndQuestions")
            fun `should also be false if either question or title is blank or empty`(title: String, question: String) {
                invoking { Question(1, user, title, question) } `should throw` QuestionException::class
            }
        }


        @Nested
        inner class voteUp {

            @Test
            fun `should increment vote if question is not closed`() {
                question.voteUp()
                question.votes.`should be equal to`(1)
            }

            @Test
            fun `should throw an exception if question is closed`() {
                question.close("")
                invoking { question.voteUp() } `should throw` QuestionException::class
            }
        }


        @Nested
        inner class voteDown {

            @Test
            fun `should decrement vote if question is not closed`() {
                question.voteDown()
                question.votes.`should be equal to`(-1)
            }

            @Test
            fun `should throw an exception if question is closed`() {
                question.close("")
                invoking { question.voteDown() } `should throw` QuestionException::class
            }
        }

        @Nested
        inner class close {
            @Test
            fun `should set a closedReason`() {
                question.close("Not a question")
                question.closedReason.`should be equal to`("Not a question")
            }
        }

        @Nested
        inner class addAnswer {

            @Test
            fun `should add an answer to the stored list`() {
                val answer = Answer(1, user, "body")
                question.addAnswer(answer)
                question.answers.count().`should be equal to`(1)
            }

            @Test
            fun `should set question as answered`() {
                val answer = Answer(1, user, "body")
                question.addAnswer(answer)
                question.answered.shouldBeTrue()
            }

            @Test
            fun `should not be able to add the same answer twice`() {
                val question = Question(1, user, "title", "question")
                var answer = Answer(1, user, "body")
                question.addAnswer(answer)
                answer = Answer(1, user, "body")
                invoking { question.addAnswer(answer) } `should throw` QuestionException::class
            }

        }

        @Nested
        inner class approveAnswer {

            @Test
            fun `should approve an answer in the answer list`() {
                val answer = Answer(1, user, "body")
                question.addAnswer(answer)
                question.approveAnswer(1)
                answer.approved.shouldBeTrue()
            }

            @Test
            fun `should thrown an exception if approving an answer not in the answer list`() {
                val answer = Answer(1, user, "body")
                question.addAnswer(answer)
                invoking { question.approveAnswer(2) } `should throw` QuestionException::class
            }

            @Test
            fun `should thrown an exception if approving an answer when an approved answer already exists`() {
                var answer = Answer(1, user, "body")
                question.addAnswer(answer)
                answer = Answer(2, user, "body")
                question.addAnswer(answer)
                question.approveAnswer(1)
                invoking { question.approveAnswer(2) } `should throw` QuestionException::class
            }
        }
    }

    @Nested
    inner class answers {
        private val user = User(1, "Kevin")
        val question = Question(1, user, "title", "body")

        @Test
        fun `should have no answers`() {
            question.answers.shouldBeEmpty()
        }


        @Test
        fun `should have an answer`() {
            var answer = Answer(1, user, "body")
            question.addAnswer(answer)
            question.answers.shouldNotBeEmpty()
        }

        @Test
        fun `should contain an answer`() {
            val answer1 = Answer(1, user, "body")
            val answer2 = Answer(2, user, "body")
            question.addAnswer(answer1)
            question.answers `should contain` answer1
        }

        @Test
        fun `should not contain an answer`() {
            val answer1 = Answer(1, user, "body")
            val answer2 = Answer(2, user, "body")
            question.addAnswer(answer1)
            question.answers `should not contain` answer2
        }

    }
}