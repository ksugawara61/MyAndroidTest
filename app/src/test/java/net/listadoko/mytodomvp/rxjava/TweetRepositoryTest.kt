package net.listadoko.mytodomvp.rxjava

import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before

@RunWith(RobolectricTestRunner::class)
class TweetRepositoryTest {
    lateinit var repository: TweetRepository

    @Before
    fun setUp() {
        repository = TweetRepository()
    }

    @Test
    fun recents_returnsTweets() {
        val list = repository.recents()
            .test()
            .await()
            .values()[0]

        assertThat(list)
            .extracting("tweet", String::class.java)
            .containsExactly("hello", "from", "world")
    }
}