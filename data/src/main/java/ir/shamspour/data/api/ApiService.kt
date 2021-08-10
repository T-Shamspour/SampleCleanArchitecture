package ir.shamspour.data.api

import ir.shamspour.data.model.PostRemote
import kotlinx.coroutines.Deferred
import retrofit2.http.GET


interface ApiService {
    @GET("posts")
    fun fetchPostsAsync(): Deferred<List<PostRemote>>

}
