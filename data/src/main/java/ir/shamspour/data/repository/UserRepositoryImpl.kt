package ir.shamspour.data.repository

import ir.shamspour.domain.model.*
import ir.shamspour.domain.repository.UserRepository
import ir.shamspour.domain.util.Result
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepositoryImpl @Inject constructor(private val dataSource: UserDataSource) :
    UserRepository {
    override suspend fun getPosts(): Result<List<Post>> =  dataSource.getPosts()

}