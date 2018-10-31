import io.reactivex.rxkotlin.toFlowable
import io.reactivex.schedulers.Schedulers

fun main(args: Array<String>) {
    var postFix = 0
    listOf("Dika", "Budi", "Aji", "James").toFlowable()
            .doOnNext { println("Item $it was created on thread ${Thread.currentThread().name}") }
            .observeOn(Schedulers.newThread())
            .map { "$it-${postFix++}" }
            .doOnNext { println("Map operator was done on thread ${Thread.currentThread().name}") }
            .subscribeOn(Schedulers.newThread())
            .blockingSubscribe { println("Item $it was received on thread ${Thread.currentThread().name}") }
}