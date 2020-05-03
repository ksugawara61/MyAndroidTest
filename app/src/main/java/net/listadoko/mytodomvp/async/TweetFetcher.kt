package net.listadoko.mytodomvp.async

import io.reactivex.Scheduler
import io.reactivex.rxkotlin.subscribeBy
import net.listadoko.mytodomvp.rxjava.Tweet
import net.listadoko.mytodomvp.rxjava.TweetRepository

class TweetFetcher(val subscribeScheduler: Scheduler,
                   val observeScheduler: Scheduler,
                   val repository: TweetRepository) {

    fun recents(onSuccess: (List<Tweet>) -> Unit, onError: (Throwable) -> Unit) {
        repository.recents()
            .subscribeOn(subscribeScheduler)
            .observeOn(observeScheduler)
            .subscribeBy(
                onSuccess = onSuccess,
                onError = onError
            )
    }
}