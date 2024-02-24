package com.seungsu.pokemon.data.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.seungsu.pokemon.data.api.PokemonApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
internal object ApiModule {

    @Provides
    @Singleton
    fun providePokemonApi(): PokemonApi {
        val json = Json {
            isLenient = true
            coerceInputValues = true
            ignoreUnknownKeys = true
        }

        val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val okHttpBuilder = OkHttpClient.Builder()
            .connectTimeout(20, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .writeTimeout(20, TimeUnit.SECONDS)
            .addInterceptor(httpLoggingInterceptor)

        val retrofitBuilder = Retrofit.Builder()
            .client(okHttpBuilder.build())
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .baseUrl("https://pokeapi.co/api/")
            .build()

        return retrofitBuilder.create(PokemonApi::class.java)
    }
}
