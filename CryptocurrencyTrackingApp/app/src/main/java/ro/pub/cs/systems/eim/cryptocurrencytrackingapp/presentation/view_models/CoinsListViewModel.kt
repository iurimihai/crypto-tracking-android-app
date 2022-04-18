package ro.pub.cs.systems.eim.cryptocurrencytrackingapp.presentation.view_models

import android.content.Context
import android.view.View
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ro.pub.cs.systems.eim.cryptocurrencytrackingapp.domain.models.Coin
import ro.pub.cs.systems.eim.cryptocurrencytrackingapp.domain.use_cases.GetCoinsListUseCase
import ro.pub.cs.systems.eim.cryptocurrencytrackingapp.domain.use_cases.UpdatePriceUseCase

class CoinsListViewModel(
    private val getCoinsListUseCase: GetCoinsListUseCase,
    private val updatePriceUseCase: UpdatePriceUseCase
    ) : ViewModel() {

    private val _coins: MutableLiveData<List<Coin>> by lazy { MutableLiveData() }
    val coins get() = _coins

    init {
        fetchCoins()
    }

    private fun fetchCoins() = viewModelScope.launch {
        _coins.value = withContext(Dispatchers.IO) {
            getCoinsListUseCase()
        }
    }

    suspend fun getUpdatedPrice(coin: Coin, refCurrency: String) =
            updatePriceUseCase(coin.symbol, refCurrency)

//    fun fetchCoinPrice(coin: Coin, refCurrency: String) =
//        viewModelScope.launch(currentJob) {
//            val updatedPrice = updatePriceUseCase(coin.symbol, refCurrency)
//            updatedPrice.collect { coin.price = it }
//        }

//    suspend fun getCoinPriceUpdate(coin: Coin, refCurrency: String) =
//            updatePriceUseCase(coin.symbol, refCurrency).asLiveData()
}