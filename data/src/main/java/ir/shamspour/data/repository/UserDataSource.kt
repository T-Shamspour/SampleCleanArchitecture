package ir.shamspour.data.repository


import ir.shamspour.domain.model.*
import ir.shamspour.domain.util.Result

interface UserDataSource {
    suspend fun getPosts(): Result<List<Post>>

}