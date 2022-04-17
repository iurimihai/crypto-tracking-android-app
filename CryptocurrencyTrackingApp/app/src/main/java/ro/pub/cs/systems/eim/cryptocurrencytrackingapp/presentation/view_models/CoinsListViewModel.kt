package ro.pub.cs.systems.eim.cryptocurrencytrackingapp.presentation.view_models

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import ro.pub.cs.systems.eim.cryptocurrencytrackingapp.domain.models.Coin
import ro.pub.cs.systems.eim.cryptocurrencytrackingapp.domain.use_cases.GetCoinsListUseCase
import ro.pub.cs.systems.eim.cryptocurrencytrackingapp.domain.use_cases.UpdatePriceUseCase

class CoinsListViewModel(
    private val getCoinsListUseCase: GetCoinsListUseCase,
    private val updatePriceUseCase: UpdatePriceUseCase
    ) : ViewModel() {
    private val currentJob = SupervisorJob()

    private val coins: MutableLiveData<List<Coin>> by lazy { MutableLiveData() }
    private val coinPrice: MutableLiveData<String> by lazy { MutableLiveData() }

    fun fetchCoins() = viewModelScope.launch {
        coins.value = getCoinsListUseCase()
    }

    fun fetchCoinPrice(coinSymbol: String, refCurrency: String) =
        viewModelScope.launch(Dispatchers.IO + currentJob) {
            val updatedPrice = updatePriceUseCase(coinSymbol, refCurrency)
            updatedPrice.collect { coinPrice.value = it }
        }
}