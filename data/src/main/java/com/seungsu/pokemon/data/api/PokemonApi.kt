package com.seungsu.pokemon.data.api

import com.seungsu.pokemon.data.model.PokemonNamesResponse
import com.seungsu.pokemon.data.model.PokemonResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

internal interface PokemonApi {

    @GET("v2/pokemon/{id}")
    suspend fun getPokemonInfoById(
        @Path("id") id: Int
    ): PokemonResponse

    @GET("v2/pokemon/{name}")
    suspend fun getPokemonInfoByName(
        @Path("name") name: String
    ): PokemonResponse

    @GET("v2/pokemon/")
    suspend fun getPokemonNames(
        @Query("offset") offset: Int,
        @Query("limit") pageSize: Int
    ): PokemonNamesResponse
}
