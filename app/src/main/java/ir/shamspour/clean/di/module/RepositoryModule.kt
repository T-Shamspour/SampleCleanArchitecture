package ir.shamspour.clean.di.module

import ir.shamspour.data.repository.UserDataSource
import ir.shamspour.data.repository.UserRepositoryImpl
import ir.shamspour.domain.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ir.shamspour.data.repository.UserRemoteDataSource


@InstallIn(SingletonComponent::class)
@Module
class RepositoryModule {

    @Provides
    fun provideUserRepository(remoteDataSource: UserRemoteDataSource): UserRepository =
        UserRepositoryImpl(remoteDataSource)


}
