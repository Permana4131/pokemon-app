package com.permana.pokemonapp.feature.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.permana.pokemonapp.domain.usecase.LoginUseCase
import com.permana.pokemonapp.domain.usecase.RegisterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val registerUseCase: RegisterUseCase,
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    private val _registerResult = MutableStateFlow<Boolean?>(null)
    val registerResult: StateFlow<Boolean?> = _registerResult

    private val _authResult = MutableStateFlow<Pair<String, String>?>(null)
    val authResult: StateFlow<Pair<String, String>?> = _authResult

    fun register(username: String, password: String) {
        viewModelScope.launch {
            val success = registerUseCase(username, password)
            _registerResult.value = if (success) true else false
        }
    }

    fun login(username: String, password: String) {
        viewModelScope.launch {
            val success = loginUseCase(username, password)
            _authResult.value = if (success) {
                "login_success" to username
            } else {
                "invalid_credentials" to ""
            }
        }
    }
}
