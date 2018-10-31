import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.functions.BiFunction
import io.reactivex.rxkotlin.zipWith

fun main(args: Array<String>) {
    val db = FakeDb()
    // if we try with record not found it's will return nothing because we use `Maybe`
    db.getUserById(1)
            .subscribe { println(it)}

    db.getAllUser()
            .flatMapSingle{ db.getPointsForUserId(it.id) }
            .filter { it > 10 }
            .subscribe{ println(it) }

    db.getAllUser()
            .flatMapSingle { Single.zip(Single.just(it), db.getPointsForUserId(it.id),
                    BiFunction { user: User, points: Int -> "${user.name} has $points"  })
            }
            .subscribe { println(it) }

    // with RxKotlin
    db.getAllUser()
            .flatMapSingle { Single.just(it).zipWith(db.getPointsForUserId(it.id), {user: User, points: Int -> "${user.name} has $points"}) }
            .subscribe { println(it) }

}

fun getAllUsers(db: FakeDb) {
    //Get All Users name
    db.getUsers()
            .map { user -> user.value }
            .subscribe({ name -> println(name) })


    // Get all users with the key
    db.getUsers()
            .flatMap { user -> db.getPointsForUser(user.key).zipWith(Observable.just(user), {
                user, points -> "$user has $points points"
            })}
            .subscribe { println(it) }
}