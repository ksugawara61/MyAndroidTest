package net.listadoko.mytodomvp.rxjava

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import org.assertj.core.api.Assertions.assertThat
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import net.listadoko.mytodomvp.async.TweetFetcher
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class TweetFetcherTest {
    lateinit var repository: TweetRepository
    lateinit var tweetFetcher: TweetFetcher

    @Before
    fun setUp() {
        repository = mock(name = "StubTweetRepository")
        val tweets = listOf(
            Tweet("hello", 1),
            Tweet("from", 2),
            Tweet("world", 3)
        )
        whenever(repository.recents()).thenReturn(Single.just(tweets))

        tweetFetcher = TweetFetcher(
            Schedulers.trampoline(),
            Schedulers.trampoline(),
            repository
        )
    }

    @Test
    fun recents_returnsTweets() {
        tweetFetcher.recents(
            onSuccess = {
                assertThat(it)
                    .extracting("tweet", String::class.java)
                    .containsExactly("hello", "from", "world")
            },
            onError = {}
        )
    }
}