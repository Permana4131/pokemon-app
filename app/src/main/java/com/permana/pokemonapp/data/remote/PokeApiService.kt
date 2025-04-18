package com.permana.pokemonapp.data.remote

import com.permana.pokemonapp.data.remote.dto.PokemonDto
import com.permana.pokemonapp.data.remote.dto.PokemonListResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface PokeApiService {
    @GET("pokemon?limit=10&offset=0")
    suspend fun getPokemonList(): PokemonListResponse

    @GET("pokemon/{name}")
    suspend fun getPokemonDetail(@Path("name") name: String): PokemonDto
}
