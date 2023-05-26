package com.arturomarmolejo.amiiboappamcompose.di

import com.arturomarmolejo.amiiboappamcompose.rest.AmiiboRepository
import com.arturomarmolejo.amiiboappamcompose.rest.AmiiboRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun providesAmiiboRepositoryImpl(amiiboRepositoryImpl: AmiiboRepositoryImpl):
            AmiiboRepository
}