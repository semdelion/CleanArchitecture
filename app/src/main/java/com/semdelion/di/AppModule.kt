package com.semdelion.di
import android.content.Context
import com.semdelion.domain.usecases.GetUser
import com.semdelion.domain.usecases.SaveUser
import com.semdelion.presentation.MainViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class AppModule(private val context: Context) {
    @Provides
    fun provideContext():Context {
        return context
    }
    @Provides
    fun provideMainViewModelFactory(getUserUseCase: GetUser, saveUserUseCase: SaveUser): MainViewModelFactory {
        return MainViewModelFactory(
            getUserUseCase = getUserUseCase,
            saveUserUseCase = saveUserUseCase)
    }
}