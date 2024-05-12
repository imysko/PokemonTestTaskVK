package com.imysko.pokemontesttaskvk.presentation.ui.screens.pokemonList

import com.imysko.pokemontesttaskvk.domain.entities.Pokemon
import com.imysko.pokemontesttaskvk.domain.usecase.GetPokemonListUseCase
import com.imysko.pokemontesttaskvk.domain.usecase.LoadPokemonNamesListUseCase
import com.imysko.pokemontesttaskvk.presentation.mappers.mapToUiModel
import com.imysko.pokemontesttaskvk.presentation.ui.commom.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class PokemonListViewModel @Inject constructor(
    private val loadPokemonNamesListUseCase: LoadPokemonNamesListUseCase,
    private val getPokemonListUseCase: GetPokemonListUseCase,
) : BaseViewModel() {

    private val _uiState =
        MutableStateFlow<PokemonListScreenUiState>(PokemonListScreenUiState.OnLoading)
    val uiState: StateFlow<PokemonListScreenUiState>
        get() = _uiState.asStateFlow()

    private val _currentPage = MutableStateFlow(0)
    private val _canLoadMore = MutableStateFlow(true)

    init {
        getPokemonList()
        loadPokemonNamesList()
    }

    fun reloadPokemonNamesList() {
        _currentPage.update { 0 }
        _uiState.update { PokemonListScreenUiState.OnLoading }

        loadPokemonNamesList()
    }

    fun loadPokemonNamesList() {
        if (!_canLoadMore.value) {
            return
        }

        changePokemonListLoadingState()

        call(
            useCaseCall = {
                _canLoadMore.update { false }

                loadPokemonNamesListUseCase(
                    offset = _currentPage.value * PAGE_SIZE,
                    limit = PAGE_SIZE,
                )
            },
            onSuccess = {
                _currentPage.update { it + 1 }
                changePokemonListLoadingState()
            },
            onNullResult = {
                if (_uiState.value !is PokemonListScreenUiState.ShowPokemonList) {
                    _uiState.update { PokemonListScreenUiState.NotFound }
                }
            },
            onError = {
                _uiState.update { PokemonListScreenUiState.OnError }
            },
            onNetworkUnavailable = {
                _uiState.update { PokemonListScreenUiState.NoInternetConnection }
            },
            onFinally = {
                _canLoadMore.update { true }
            }
        )
    }

    private fun getPokemonList() {
        call(
            useCaseCall = {
                getPokemonListUseCase()
            },
            onCollect = { pokemonList ->
                if (pokemonList.isNotEmpty()) {
                    updatePokemonListState(
                        pokemonList = pokemonList,
                    )
                }
            },
            onError = {
                _uiState.update { PokemonListScreenUiState.OnError }
            },
        )
    }

    private fun updatePokemonListState(pokemonList: List<Pokemon>) {
        _uiState.update {
            PokemonListScreenUiState.ShowPokemonList(
                pokemonList = pokemonList.map { pokemon ->
                    pokemon.mapToUiModel()
                },
                isLoadingMore = false,
            )
        }
    }

    private fun changePokemonListLoadingState() {
        (_uiState.value as? PokemonListScreenUiState.ShowPokemonList)?.let { state ->
            _uiState.update {
                state.copy(isLoadingMore = !state.isLoadingMore)
            }
        } ?: {
            _uiState.update { PokemonListScreenUiState.OnLoading }
        }
    }
}
