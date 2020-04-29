package net.listadoko.mytodomvp

import org.assertj.core.api.Assertions.*
import org.junit.After
import org.junit.Before
import org.junit.Ignore
import org.junit.Test

class InputCheckerTest {
    lateinit var target: InputChecker

    @Before
    fun setUp() {
        target = InputChecker()
    }

    @After
    fun tearDown() {}

    @Test
    fun isValid_givenLessThan3_returnsFalse() {
        val actual = target.isValid("ab")
        assertThat(actual).isFalse()
    }

    @Test
    fun isValid_givenAlphabetic_returnsTrue() {
        val actual = target.isValid("abc")
        assertThat(actual).isTrue()
    }

    @Test
    fun isValid_givenNumeric_returnsTrue() {
        val actual = target.isValid("123")
        assertThat(actual).isTrue()
    }

    @Test
    fun isValid_givenAlphaNumeric_returnsTrue() {
        val actual = target.isValid("abc123")
        assertThat(actual).isTrue()
    }

    @Test
    fun isValid_givenInvalidCharacter_returnsTrue() {
        val actual = target.isValid("abc@123")
        assertThat(actual).isFalse()
    }

//    @Ignore("this is skip sample")
    @Test
    fun isValid_givenNull_throwIllegalArgumentException() {
        assertThatExceptionOfType(IllegalArgumentException::class.java)
            .isThrownBy { target.isValid(null) }
            .withMessage("Cannot be null")
            .withNoCause()
    }
}