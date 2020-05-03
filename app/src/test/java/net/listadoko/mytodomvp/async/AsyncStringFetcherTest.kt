package net.listadoko.mytodomvp.async

import com.nhaarman.mockitokotlin2.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import java.lang.RuntimeException
import java.util.concurrent.CountDownLatch

@RunWith(RobolectricTestRunner::class)
class AsyncStringFetcherTest {
    lateinit var fetcher: StringFetcher
    lateinit var asyncFetcher: AsyncStringFetcher
    lateinit var latch: CountDownLatch

    @Before
    fun setUp() {
        fetcher = spy()
        val executor = CurrentThreadExecutorService()
        asyncFetcher = AsyncStringFetcher(fetcher, executor)
        latch = CountDownLatch(1)
    }

    @Test
    fun fetchAsync_callbackedProperly() {
        asyncFetcher.fetchAsync(
            { value ->
                assertThat(value).isEqualTo("foo")
                verify(fetcher, times(1)).fetch()
                println("success")
                latch.countDown()
            },
            { _ -> }
        )

        println("fetchAsync invoked")
        latch.await()
    }

    @Test
    fun fetchAsync_callbackedRuntimeException() {
        doThrow(RuntimeException("ERROR")).whenever(fetcher).fetch()
        asyncFetcher.fetchAsync(
            { _ -> },
            { error ->
                assertThat(error)
                    .isInstanceOf(RuntimeException::class.java)
                    .hasMessageContaining("ERROR")
                verify(fetcher, times(1)).fetch()
                println("failure")
                latch.countDown()
            }
        )

        println("fetchAsync invoked")
        latch.await()
    }

    @Test
    fun fetchAsync_future_OK() {
        var actualValue: String? = null
        var actualError: Throwable? = null

        asyncFetcher.fetchAsync(
            { value -> actualValue = value },
            { error -> actualError = error }
        ).get()

        verify(fetcher, times(1)).fetch()
        assertThat(actualValue).isEqualTo("foo")
        assertThat(actualError).isNull()
    }

    @Test
    fun fetchAsync_future_NG() {
        doThrow(RuntimeException("ERROR")).whenever(fetcher).fetch()
        var actualValue: String? = null
        var actualError: Throwable? = null

        asyncFetcher.fetchAsync(
            { value -> actualValue = value },
            { error -> actualError = error }
        ).get()

        verify(fetcher, times(1)).fetch()
        assertThat(actualValue).isNull()
        assertThat(actualError)
            .isInstanceOf(RuntimeException::class.java)
            .hasMessageContaining("ERROR")
    }
}