@file:Suppress("PackageDirectoryMismatch", "ClassName")

package com.knowledgespike.questiontest

import com.knowledgespike.question.User
import org.amshove.kluent.*
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class UserTest {

    private val user: User = User(1, "Kevin")

    @Test
    fun `should be able to increase reputation`() {
        user.changeReputation(10)

        user.reputation.shouldEqual(10)
    }

    @Test
    fun `should be able to decrease reputation`() {
        user.changeReputation(10)
        user.changeReputation(-5)

        user.reputation.`should equal`(5)
    }

    @Nested
    inner class `post should be able to` {

        private val editReputationLimit = 2000

        @Test
        fun `edit if reputation is greater than 2000`() {
            user.changeReputation(editReputationLimit+1)
            user.canEditPost().shouldBeTrue()
        }

        @Test
        fun `edit if reputation is equal to 2000`() {
            user.changeReputation(editReputationLimit)
            user.canEditPost().shouldBeFalse()
        }

        @Test
        fun `edit if reputation is less than 2000`() {
            user.changeReputation(editReputationLimit-1)
            user.canEditPost().shouldBeFalse()
        }
    }

    @Nested
    inner class comment {

        private val commentReputationLimit = 50

        @Test
        fun `should be able to add if reputation is greater than 50`() {
            user.changeReputation(commentReputationLimit+1)
            user.canComment().shouldBeTrue()
        }

        @Test
        fun `should not be able to add if reputation is equal to 50`() {
            user.changeReputation(commentReputationLimit)
            user.canComment().shouldBeFalse()
        }

        @Test
        fun `should not be able to add if reputation is less than 50`() {
            user.changeReputation(commentReputationLimit-1)
            user.canComment().shouldBeFalse()
        }
    }

}