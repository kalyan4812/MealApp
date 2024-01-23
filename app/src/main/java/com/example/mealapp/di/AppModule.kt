package com.example.mealapp.di


import com.example.mealapp.models.MealApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
class AppModule {

    @Singleton
    @Provides
    fun getApiSource(retrofit: Retrofit): MealApi = retrofit.create(MealApi::class.java)

}