import io.reactivex.*

class FakeDb: Db {
    val userMap = mutableMapOf(1 to User(1, "Peter"),
                               2 to User(2, "Laura"),
                               3 to User(3, "John"),
                               4 to User(4, "Carl"))
    val userPointsMap = mutableMapOf(1 to 40, 2 to 23, 3 to 0, 4 to 22)

    override fun getAllUser(): Flowable<User> = Flowable.fromIterable(userMap.values)

    override fun getUserById(id: Int): Maybe<User> {
        return getAllUser().filter { it.id == id }
                .firstElement()
    }

    override fun getPointsForUserId(id: Int): Single<Int> {
        return if(userPointsMap.containsKey(id))
            Single.just(userPointsMap[id])
        else
            Single.error{ UserNotFound("Id $id is not on the records") }
    }

    override fun addUse(user: User): Completable {
        return Completable.fromAction { userMap[user.id] = user }
    }
    fun getUsers(): Observable<Map.Entry<Int, String>> {
        val userMap = mutableMapOf(1 to "Peter",
                2 to "Laura",
                3 to "John",
                4 to "Carl")

        return Observable.fromIterable(userMap.entries)
    }

    fun getPointsForUser(userId: Int): Observable<Int> {
        return Observable.just(userPointsMap[userId])
    }



}

interface Db {
    fun getAllUser(): Flowable<User>
    fun getUserById(id: Int): Maybe<User> //Single
    fun getPointsForUserId(id: Int): Single<Int>
    fun addUse(user: User): Completable
}

data class User(val id: Int, val name: String )
class UserNotFound(message: String?): Exception(message)