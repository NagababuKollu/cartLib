package com.thenorthface.tnf.cartlib.dependencyInjection

import com.thenorthface.tnf.cartlib.api.CartApi
import com.thenorthface.tnf.cartlib.repository.DefaultMainRepository
import com.thenorthface.tnf.cartlib.repository.MainRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 *Created by Zaid Zakir
 */
private const val BASE_URL = "https://api.kanye.rest/"

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule{

    @Singleton
    @Provides
    fun provideKanyeWestApi():CartApi = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(CartApi::class.java)

    @Singleton
    @Provides
    fun provideRepository(cartApi: CartApi) : MainRepository = DefaultMainRepository(cartApi)

}