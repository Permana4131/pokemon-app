package com.permana.pokemonapp.domain.usecase

import com.permana.pokemonapp.domain.model.Pokemon
import com.permana.pokemonapp.domain.repository.PokemonRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPokemonsUseCase @Inject constructor (private val pokemonRepository: PokemonRepository) {

    suspend fun getPokemonList(): Flow<List<Pokemon>> {
        return pokemonRepository.getPokemonList()
    }
}
