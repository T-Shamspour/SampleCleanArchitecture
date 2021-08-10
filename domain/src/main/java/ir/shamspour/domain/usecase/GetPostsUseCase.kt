package ir.shamspour.domain.usecase


import ir.shamspour.domain.model.*
import ir.shamspour.domain.repository.UserRepository
import javax.inject.Inject
import javax.inject.Singleton
import ir.shamspour.domain.util.Result


@Singleton
class GetPostsUseCase @Inject constructor(private val repository: UserRepository) {
    suspend fun execute(): Result<List<Post>> =
            repository.getPosts()
}