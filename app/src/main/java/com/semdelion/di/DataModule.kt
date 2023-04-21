package com.semdelion.di
import android.content.Context
import com.semdelion.data.repositories.UserRepository
import com.semdelion.data.storages.IUserStorage
import com.semdelion.data.storages.SharedPrefUserStorage
import com.semdelion.domain.repositories.IUserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    @Singleton
    fun provideUserRepository(userStorage: IUserStorage): IUserRepository {
        return UserRepository(userStorage = userStorage)
    }

    @Provides
    @Singleton
    fun provideUserStorage(@ApplicationContext context: Context): IUserStorage {
        return SharedPrefUserStorage(context = context)
    }
}