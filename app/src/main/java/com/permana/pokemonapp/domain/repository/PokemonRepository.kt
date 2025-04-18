package com.permana.pokemonapp.domain.repository

import com.permana.pokemonapp.domain.model.Pokemon
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {
    suspend fun getPokemonList(): Flow<List<Pokemon>>
}