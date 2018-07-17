package co.com.condorlabs.retrofitkotlin

import io.reactivex.Completable
import org.junit.Ignore
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    val messageRoute = NetUtils.retrofit.create(MessageRoute::class.java)

    @Test
    fun testObservable() {

        val observable = Completable.create {
            it.onComplete()

        }

        observable.subscribe({
            println("Subscription 1 numero")
        }, { error ->
            println("Subscription 1 error $error")
        })

    }

    @Test
    fun shouldGetMessageTest() {
        messageRoute.getMessages().subscribe { messages ->
            messages.forEach {
                println(it)
            }
        }
    }

    @Test
    fun shouldCreateMessage() {
        messageRoute.createMessage(Message(1, 101, "Hola Luis", "Luis buenos dias"))
                .subscribe({
                    println(it)
                }, {
                    it.printStackTrace()
                })
    }

    @Ignore
    @Test
    fun shouldDeteleAMessage() {
        messageRoute.deleteMessage(101).subscribe({
            print("Borrado")
        }, {
            it.printStackTrace()
        })
    }
}
