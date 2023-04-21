package com.semdelion.di
import com.semdelion.domain.repositories.IUserRepository
import com.semdelion.domain.usecases.GetUser
import com.semdelion.domain.usecases.SaveUser
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
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