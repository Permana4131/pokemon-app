package com.permana.pokemonapp.domain.repository

interface AuthRepository {
    suspend fun register(username: String, password: String): Boolean
    suspend fun login(username: String, password: String): Boolean
}
