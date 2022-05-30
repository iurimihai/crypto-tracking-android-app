package ro.pub.cs.systems.eim.cryptocurrencytrackingapp.ui.coins_list

import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import ro.pub.cs.systems.eim.cryptocurrencytrackingapp.domain.models.Coin
import ro.pub.cs.systems.eim.cryptocurrencytrackingapp.domain.use_cases.GetCoinsListUseCase
import ro.pub.cs.systems.eim.cryptocurrencytrackingapp.domain.use_cases.UpdatePriceUseCase
import ro.pub.cs.systems.eim.cryptocurrencytrackingapp.utils.Resource

// TODO: DI
class CoinsListViewModel : ViewModel() {

//    var coins: List<Coin> = mutableListOf()
//
//    val coins: List<Coin>
//        get() = _coins

    var getCoinsListUseCase = GetCoinsListUseCase(ro.pub.cs.systems.eim.cryptocurrencytrackingapp.data.repository.CoinRepository)
    var updatePriceUseCase = UpdatePriceUseCase(ro.pub.cs.systems.eim.cryptocurrencytrackingapp.data.repository.CoinRepository)

    // DEPRECATED
    private var _uiState = MutableStateFlow(CoinsListState())
    var uiState: StateFlow<CoinsListState> = _uiState.asStateFlow()

    private var fetchJob: Job? = null

    lateinit var coins: LiveData<Resource<List<Coin>>>


    init {
        fetchCoins()
    }

    private fun fetchCoins() {
        coins = getCoinsListUseCase().asLiveData(Dispatchers.Default)
    }


    // TODO: or fetchCoins(favorite: bool)
    fun fetchFavoriteCoins() {

    }

    private fun getCoins() {
        fetchJob?.cancel()
        fetchJob = viewModelScope.launch {
            getCoinsListUseCase().onEach { result ->
                when (result) {
                    is Resource.Success -> {
//                        _uiState.value = CoinsListState(coins = result.data?.subList(0, 10) ?: emptyList())
                        _uiState.update {
                            it.copy(coins = result.data ?: emptyList())
                        }
                    }
                    is Resource.Error -> {
//                        _uiState.value = CoinsListState(error = result.message
//                                ?: "Unexpected error")
                        _uiState.update {
                            it.copy(error = result.message ?: "Unexpected error")
                        }
                    }
                    is Resource.Loading -> {
//                        _uiState.value = CoinsListState(isLoading = true)
                        _uiState.update {
                            it.copy(isLoading = true)
                        }
                    }
                }
            }
        }
    }

    fun getFavoriteCoins() {

    }

    fun updateFavoriteCoins() {

    }
}