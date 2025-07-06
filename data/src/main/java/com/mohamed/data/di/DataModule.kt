package com.mohamed.data.di

import com.mohamed.data.repositories.AlertRepoImp
import com.mohamed.domain.repositories.AlertRepo
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {
    @Binds
    @Singleton
    abstract fun bindAlertRepo(alertRepoImp: AlertRepoImp): AlertRepo
}