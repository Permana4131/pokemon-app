package com.permana.pokemonapp.data.remote.dto

data class PokemonDto(
    val name: String,
    val abilities: List<AbilityWrapper>
)

data class AbilityWrapper(
    val ability: Ability
)

data class Ability(
    val name: String
)

data class PokemonListResponse(
    val results: List<PokemonNameDto>
)

data class PokemonNameDto(
    val name: String
)
