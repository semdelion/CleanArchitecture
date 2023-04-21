package com.semdelion.di
import android.content.Context
import com.semdelion.data.repositories.UserRepository
import com.semdelion.data.storages.IUserStorage
import com.semdelion.data.storages.SharedPrefUserStorage
import com.semdelion.domain.repositories.IUserRepository
import dagger.Module
import dagger.Provides

@Module
class DataModule {
    @Provides
    fun provideUserRepository(userStorage: IUserStorage): IUserRepository {
        return UserRepository(userStorage = userStorage)
    }
    @Provides
    fun provideUserStorage(context: Context): IUserStorage {
        return SharedPrefUserStorage(context = context)
    }
}