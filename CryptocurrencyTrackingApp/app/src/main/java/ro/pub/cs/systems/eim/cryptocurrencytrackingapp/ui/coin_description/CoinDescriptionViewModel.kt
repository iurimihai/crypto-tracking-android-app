package ro.pub.cs.systems.eim.cryptocurrencytrackingapp.ui.coin_description

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ro.pub.cs.systems.eim.cryptocurrencytrackingapp.domain.models.CoinDescription
import ro.pub.cs.systems.eim.cryptocurrencytrackingapp.domain.use_cases.GetCoinDescriptionUseCase

class CoinDescriptionViewModel(
    private val getCoinDescriptionUseCase: GetCoinDescriptionUseCase
) : ViewModel() {
    private val coinDescription: MutableLiveData<CoinDescription> by lazy { MutableLiveData() }

    fun getCoinDescription(): LiveData<CoinDescription> = coinDescription

    fun fetchCoinPrice(coinId: String) = viewModelScope.launch {
        coinDescription.value = getCoinDescriptionUseCase(coinId)
    }
}