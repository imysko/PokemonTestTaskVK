package com.imysko.pokemontesttaskvk.presentation.ui.screens.pokemonDetail

import android.media.MediaPlayer
import com.imysko.pokemontesttaskvk.domain.usecase.GetPokemonByIdUseCase
import com.imysko.pokemontesttaskvk.presentation.ui.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class PokemonDetailViewModel @Inject constructor(
    private val getPokemonByIdUseCase: GetPokemonByIdUseCase,
    val mediaPlayer: MediaPlayer,
) : BaseViewModel() {

    private val _uiState = MutableStateFlow<PokemonDetailUiState>(PokemonDetailUiState.OnLoading)
    val uiState: StateFlow<PokemonDetailUiState>
        get() = _uiState.asStateFlow()

    fun getPokemon(id: Int) {
        _uiState.tryEmit(PokemonDetailUiState.OnLoading)

        call(
            useCaseCall = {
                getPokemonByIdUseCase(id)
            },
            onSuccess = { pokemon ->
                _uiState.tryEmit(
                    PokemonDetailUiState.ShowPokemonDetail(
                        pokemon = pokemon,
                    )
                )
            },
            onError = {
                _uiState.tryEmit(PokemonDetailUiState.OnError)
            },
            onNetworkUnavailable = {
                _uiState.tryEmit(PokemonDetailUiState.NoInternetConnection)
            },
        )
    }
}
