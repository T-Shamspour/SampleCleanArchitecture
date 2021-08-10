package ir.shamspour.data.repository


import com.google.gson.Gson
import ir.shamspour.data.api.ApiService
import ir.shamspour.data.exceptions.NetworkExceptionHandler
import ir.shamspour.domain.model.*
import javax.inject.Inject
import javax.inject.Singleton
import ir.shamspour.domain.util.Result


@Singleton
class UserRemoteDataSource @Inject constructor(
    private val apiService: ApiService,
    private val apiExceptionHandler: NetworkExceptionHandler,
    private val gson: Gson
) : UserDataSource {


    override suspend fun getPosts(): Result<List<Post>> {
        return try {
            val result = apiService.fetchPostsAsync().await()
            Result.Success(result.map { it.toDomain() })
        } catch (e: java.lang.Exception) {
            Result.Error(apiExceptionHandler.traceErrorException(e))
        }
    }


}