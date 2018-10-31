import io.reactivex.Flowable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.rxkotlin.toFlowable

fun main(args: Array<String>) {
    listOf("Dika", "Budi", "Aji", "James").toFlowable()
            .doOnNext {
                if(it == "Carl")
                    Flowable.empty()
                else
                    Flowable.just(it)
            }
            //.doOnError { println("Error has occured") }
            .onErrorReturn { "Sample value" }
            .subscribeBy(

                    onNext = { println(it) },
                    onError = { println("We had an exception") },
                    onComplete = { println("Stream is complete") }
            )
}