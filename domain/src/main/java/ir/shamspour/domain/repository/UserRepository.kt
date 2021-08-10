package ir.shamspour.domain.repository

import ir.shamspour.domain.model.*
import ir.shamspour.domain.util.Result

interface UserRepository {
    suspend fun getPosts(): Result<List<Post>>

}