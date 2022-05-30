package ro.pub.cs.systems.eim.cryptocurrencytrackingapp.ui.coin_description

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import kotlinx.coroutines.Dispatchers
import ro.pub.cs.systems.eim.cryptocurrencytrackingapp.data.repository.CoinRepository
import ro.pub.cs.systems.eim.cryptocurrencytrackingapp.domain.models.CoinMarketData
import ro.pub.cs.systems.eim.cryptocurrencytrackingapp.domain.use_cases.GetMarketDataUseCase
import ro.pub.cs.systems.eim.cryptocurrencytrackingapp.utils.Resource

class CoinMarketDataViewModel : ViewModel() {
    val getMarketDataUseCase = GetMarketDataUseCase(CoinRepository)

    lateinit var coinMarketData: LiveData<Resource<CoinMarketData>>

    fun setData(coinId: String) {
        coinMarketData = getMarketDataUseCase(coinId).asLiveData(Dispatchers.Main)
    }
}