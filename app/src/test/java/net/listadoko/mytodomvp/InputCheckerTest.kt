package net.listadoko.mytodomvp

import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.assertThat
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
        assertThat(actual, `is`(false))
    }

    @Test
    fun isValid_givenAlphaNumeric_returnsTrue() {
        val actual = target.isValid("abc123")
        assertThat(actual, `is`(true))
    }

//    @Ignore("this is skip sample")
    @Test(expected = IllegalArgumentException::class)
    fun isValid_givenNull_throwIllegalArgumentException() {
        target.isValid(null)
    }
}