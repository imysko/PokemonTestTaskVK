package com.imysko.pokemontesttaskvk.presentation.ui.screens.pokemonDetail

import android.media.MediaPlayer
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import com.imysko.pokemontesttaskvk.domain.usecase.GetPokemonAbilityListUseCase
import com.imysko.pokemontesttaskvk.domain.usecase.GetPokemonByIdUseCase
import com.imysko.pokemontesttaskvk.domain.usecase.LoadAbilityListByNamesUseCase
import com.imysko.pokemontesttaskvk.presentation.entities.navigation.NavArguments
import com.imysko.pokemontesttaskvk.presentation.mappers.mapToUiModel
import com.imysko.pokemontesttaskvk.presentation.ui.commom.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class PokemonDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getPokemonByIdUseCase: GetPokemonByIdUseCase,
    private val loadAbilityListByNamesUseCase: LoadAbilityListByNamesUseCase,
    private val getPokemonAbilityListUseCase: GetPokemonAbilityListUseCase,
    private val mediaPlayer: MediaPlayer,
) : BaseViewModel() {

    private val _pokemonId: Int = checkNotNull(savedStateHandle[NavArguments.POKEMON_ID])
    private val _abilityNamesList = MutableStateFlow(emptyList<String>())

    private val _pokemonDetailUiState =
        MutableStateFlow<PokemonDetailUiState>(PokemonDetailUiState.OnLoading)
    val pokemonDetailUiState: StateFlow<PokemonDetailUiState>
        get() = _pokemonDetailUiState.asStateFlow()

    private val _abilityListUiState =
        MutableStateFlow<AbilityListUiState>(AbilityListUiState.OnLoading)
    val abilityListUiState: StateFlow<AbilityListUiState>
        get() = _abilityListUiState.asStateFlow()

    var isPlayingSound by mutableStateOf(false)
        private set

    init {
        getPokemon(_pokemonId)
    }

    fun onEvent(event: PokemonDetailScreenEvent) {
        when (event) {
            PokemonDetailScreenEvent.OnPlayButtonClick -> {
                playSound()
            }

            PokemonDetailScreenEvent.OnPauseButtonClick -> {
                pauseSound()
            }

            PokemonDetailScreenEvent.OnReloadAbilityList -> {
                loadPokemonAbilities()
            }
        }
    }

    private fun getPokemon(id: Int) {
        _pokemonDetailUiState.update { PokemonDetailUiState.OnLoading }

        call(
            useCaseCall = {
                getPokemonByIdUseCase(id)
            },
            onSuccess = { pokemon ->
                _pokemonDetailUiState.update {
                    PokemonDetailUiState.ShowPokemonDetail(pokemon.mapToUiModel())
                }

                _abilityNamesList.update { pokemon.abilities.map { it.abilityName } }

                loadPokemonAbilities()
                getAbilities(pokemon.id)
                loadSound(pokemon.crySoundUrl)
            },
            onNullResult = {
                _pokemonDetailUiState.update { PokemonDetailUiState.OnError }
            },
            onError = {
                _pokemonDetailUiState.update { PokemonDetailUiState.OnError }
            },
        )
    }

    private fun playSound() {
        mediaPlayer.start()
        isPlayingSound = true
    }

    private fun pauseSound() {
        mediaPlayer.pause()
        isPlayingSound = false
    }

    private fun loadPokemonAbilities() {
        _abilityListUiState.update { AbilityListUiState.OnLoading }

        call(
            useCaseCall = {
                loadAbilityListByNamesUseCase(_abilityNamesList.value)
            },
            onError = {
                _abilityListUiState.update { AbilityListUiState.OnError }
            },
            onNetworkUnavailable = {
                _abilityListUiState.update { AbilityListUiState.NoInternetConnection }
            }
        )
    }

    private fun getAbilities(id: Int) {
        call(
            useCaseCall = {
                getPokemonAbilityListUseCase(id)
            },
            onCollect = { pokemonAbilitiesList ->
                if (pokemonAbilitiesList.isNotEmpty()) {
                    _abilityListUiState.update {
                        AbilityListUiState.ShowAbilityList(
                            pokemonAbilitiesList.map { it.mapToUiModel() }
                        )
                    }
                }
            },
            onError = {
                _abilityListUiState.update { AbilityListUiState.OnError }
            },
        )
    }

    private fun loadSound(soundUrl: String) {
        mediaPlayer.reset()
        mediaPlayer.setDataSource(soundUrl)
        mediaPlayer.prepareAsync()

        mediaPlayer.setOnCompletionListener {
            isPlayingSound = false
        }
    }
}
