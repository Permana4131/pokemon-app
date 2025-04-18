package com.permana.pokemonapp.domain.usecase

import com.permana.pokemonapp.domain.repository.AuthRepository
import javax.inject.Inject

class RegisterUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(username: String, password: String): Boolean {
        return repository.register(username, password)
    }
}
