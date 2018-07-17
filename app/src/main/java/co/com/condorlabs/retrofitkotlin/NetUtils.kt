package co.com.condorlabs.retrofitkotlin

import com.google.gson.Gson
import io.reactivex.Completable
import io.reactivex.Observable
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

/**
 * @author Oscar Gallon on 7/17/18.
 */

data class Message(val userId: Int,
                   val id: Int,
                   val title: String,
                   val body: String)

object NetUtils {

    val retrofit = Retrofit.Builder()
            .baseUrl("https://my-json-server.typicode.com/typicode/demo/")
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(OkHttpClient())
            .build()

}

interface MessageRoute {

    @GET("posts")
    fun getMessages(): Observable<List<Message>>

    @POST("posts")
    fun createMessage(@Body message: Message): Observable<Message>

    @DELETE("posts/{id}")
    fun deleteMessage(@Path("id") id: Int): Completable
}
