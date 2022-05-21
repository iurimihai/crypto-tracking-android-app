package ro.pub.cs.systems.eim.cryptocurrencytrackingapp.ui.coins_list

import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ro.pub.cs.systems.eim.cryptocurrencytrackingapp.domain.models.Coin
import ro.pub.cs.systems.eim.cryptocurrencytrackingapp.domain.use_cases.GetCoinsListUseCase
import ro.pub.cs.systems.eim.cryptocurrencytrackingapp.domain.use_cases.UpdatePriceUseCase
import ro.pub.cs.systems.eim.cryptocurrencytrackingapp.utils.Constants

class CoinsListViewModel(
    private val getCoinsListUseCase: GetCoinsListUseCase,
    private val updatePriceUseCase: UpdatePriceUseCase
    ) : ViewModel() {

    private val _coins: MutableLiveData<List<Coin>> by lazy { MutableLiveData() }
    val coins: LiveData<List<Coin>>
        get() = _coins

//    private val _coinPrice: MutableLiveData<String> by lazy { MutableLiveData() }
//    var coinPrice: LiveData<String>
//        get() = _coinPrice

    init {
        fetchCoins()
    }

    private fun fetchCoins() = viewModelScope.launch {
        _coins.value = withContext(Dispatchers.IO) {
            getCoinsListUseCase()
        }
    }

    fun getUpdatedPrice(coin: Coin, refCurrency: String) =
        updatePriceUseCase(coin.symbol, refCurrency)


    suspend fun fetchCoinPrice(coin: Coin, refCurrency: String) =
        updatePriceUseCase(coin.symbol, refCurrency)

//    suspend fun getCoinPriceUpdate(coin: Coin, refCurrency: String) =
//            updatePriceUseCase(coin.symbol, refCurrency).asLiveData()
}