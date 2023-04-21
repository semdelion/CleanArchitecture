package com.semdelion.di
import android.content.Context
import com.semdelion.data.repositories.UserRepository
import com.semdelion.data.storages.IUserStorage
import com.semdelion.data.storages.SharedPrefUserStorage
import com.semdelion.domain.repositories.IUserRepository
import com.semdelion.domain.usecases.GetUser
import com.semdelion.domain.usecases.SaveUser
import dagger.Module
import dagger.Provides

@Module
class DomainModule {
    @Provides
    fun provideGetUser(repository: IUserRepository): GetUser {
        return GetUser(repository = repository)
    }
    @Provides
    fun provideSaveUser(repository: IUserRepository): SaveUser {
        return SaveUser(repository = repository)
    }
}