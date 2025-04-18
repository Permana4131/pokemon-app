package com.permana.pokemonapp.domain.usecase

import com.permana.pokemonapp.domain.repository.AuthRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(username: String, password: String): Boolean {
        return repository.login(username, password)
    }
}
