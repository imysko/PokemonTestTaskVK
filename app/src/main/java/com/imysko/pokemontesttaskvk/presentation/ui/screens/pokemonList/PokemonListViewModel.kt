package com.imysko.pokemontesttaskvk.presentation.ui.screens.pokemonList

import com.imysko.pokemontesttaskvk.domain.usecase.GetPokemonListUseCase
import com.imysko.pokemontesttaskvk.presentation.ui.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class PokemonListViewModel @Inject constructor(
    private val getPokemonListUseCase: GetPokemonListUseCase,
) : BaseViewModel() {

    private val _uiState = MutableStateFlow<PokemonListUiState>(PokemonListUiState.OnLoading)
    val uiState: StateFlow<PokemonListUiState>
        get() = _uiState.asStateFlow()

    init {
        getPokemonList()
    }

    fun getPokemonList() {
        _uiState.tryEmit(PokemonListUiState.OnLoading)

        call(
            useCaseCall = {
                getPokemonListUseCase()
            },
            onSuccess = { pokemonList ->
                if (pokemonList.any()) {
                    _uiState.tryEmit(
                        PokemonListUiState.ShowPokemonList(
                            pokemonList = pokemonList,
                        )
                    )
                } else {
                    _uiState.tryEmit(PokemonListUiState.NotFound)
                }
            },
            onError = {
                _uiState.tryEmit(PokemonListUiState.OnError)
            },
            onNetworkUnavailable = {
                _uiState.tryEmit(PokemonListUiState.NoInternetConnection)
            },
        )
    }
}
