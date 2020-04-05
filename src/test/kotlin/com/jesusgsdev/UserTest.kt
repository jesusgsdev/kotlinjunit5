@file:Suppress("PackageDirectoryMismatch", "ClassName")

package com.knowledgespike.questiontest

import assertk.assertions.isEqualTo
import assertk.assertions.isFalse
import assertk.assertions.isTrue
import com.knowledgespike.question.User
import org.amshove.kluent.`should be equal to`
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldBeFalse
import org.amshove.kluent.shouldBeTrue
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class UserTest {

    private val user: User = User(1, "Kevin")

    @Test
    fun `should be able to increase reputation`() {
        user.changeReputation(10)

        user.reputation.shouldBeEqualTo<Int>(10)
    }

    @Test
    fun `should be able to increase reputation with AsserJ`() {
        user.changeReputation(10)

        Assertions.assertThat(user.reputation).isEqualTo(10)
    }

    @Test
    fun `should be able to increase reputation with AsserK`() {
        user.changeReputation(10)

        assertk.assertThat(user.reputation).isEqualTo(10)
    }

    @Test
    fun `should be able to decrease reputation`() {
        user.changeReputation(10)
        user.changeReputation(-5)

        user.reputation.`should be equal to`(5)
    }

    @Nested
    inner class `post should be able to` {

        private val editReputationLimit = 2000

        @Test
        fun `edit if reputation is greater than 2000`() {
            user.changeReputation(editReputationLimit + 1)
            user.canEditPost().shouldBeTrue()
        }

        @Test
        fun `edit if reputation is equal to 2000`() {
            user.changeReputation(editReputationLimit)
            user.canEditPost().shouldBeFalse()
        }

        @Test
        fun `edit if reputation is less than 2000`() {
            user.changeReputation(editReputationLimit - 1)
            user.canEditPost().shouldBeFalse()
        }
    }

    @Nested
    inner class `post should be able to (in asserk)` {

        private val editReputationLimit = 2000

        @Test
        fun `edit if reputation is greater than 2000`() {
            user.changeReputation(editReputationLimit + 1)
            assertk.assertThat(user.canEditPost()).isTrue()
        }

        @Test
        fun `edit if reputation is equal to 2000`() {
            user.changeReputation(editReputationLimit)
            assertk.assertThat(user.canEditPost()).isFalse()
        }

        @Test
        fun `edit if reputation is less than 2000`() {
            user.changeReputation(editReputationLimit - 1)
            assertk.assertThat(user.canEditPost()).isFalse()
        }
    }

    @Nested
    inner class comment {

        private val commentReputationLimit = 50

        @Test
        fun `should be able to add if reputation is greater than 50`() {
            user.changeReputation(commentReputationLimit + 1)
            user.canComment().shouldBeTrue()
        }

        @Test
        fun `should not be able to add if reputation is equal to 50`() {
            user.changeReputation(commentReputationLimit)
            user.canComment().shouldBeFalse()
        }

        @Test
        fun `should not be able to add if reputation is less than 50`() {
            user.changeReputation(commentReputationLimit - 1)
            user.canComment().shouldBeFalse()
        }
    }

}