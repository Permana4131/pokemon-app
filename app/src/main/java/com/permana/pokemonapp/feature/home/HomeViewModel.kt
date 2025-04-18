package com.permana.pokemonapp.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.permana.pokemonapp.domain.model.Pokemon
import com.permana.pokemonapp.domain.usecase.GetPokemonsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val getPokemonsUseCase: GetPokemonsUseCase) : ViewModel() {

    private val _pokemonListState = MutableStateFlow<List<Pokemon>>(emptyList())
    val pokemonListState: StateFlow<List<Pokemon>> get() = _pokemonListState

    private fun loadPokemons() {
        viewModelScope.launch {
            getPokemonsUseCase.getPokemonList().collect { pokemons ->
                _pokemonListState.value = pokemons
            }
        }
    }

    fun refreshPokemons() {
        viewModelScope.launch {
            getPokemonsUseCase.getPokemonList().collect { pokemons ->
                _pokemonListState.value = pokemons
            }
        }
    }
}