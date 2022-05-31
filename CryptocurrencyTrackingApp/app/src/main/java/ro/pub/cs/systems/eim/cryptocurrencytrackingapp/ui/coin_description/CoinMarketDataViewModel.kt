package ro.pub.cs.systems.eim.cryptocurrencytrackingapp.ui.coin_description

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import kotlinx.coroutines.Dispatchers
import ro.pub.cs.systems.eim.cryptocurrencytrackingapp.data.remote.dto.coin_chart.CoinChartData
import ro.pub.cs.systems.eim.cryptocurrencytrackingapp.data.repository.CoinRepository
import ro.pub.cs.systems.eim.cryptocurrencytrackingapp.domain.models.Coin
import ro.pub.cs.systems.eim.cryptocurrencytrackingapp.domain.models.CoinMarketData
import ro.pub.cs.systems.eim.cryptocurrencytrackingapp.domain.use_cases.GetChartDataUseCase
import ro.pub.cs.systems.eim.cryptocurrencytrackingapp.domain.use_cases.GetMarketDataUseCase
import ro.pub.cs.systems.eim.cryptocurrencytrackingapp.utils.Resource

class CoinMarketDataViewModel : ViewModel() {
    val getMarketDataUseCase = GetMarketDataUseCase(CoinRepository)
    val getChartDataUseCase = GetChartDataUseCase(CoinRepository)

    lateinit var coinMarketData: LiveData<Resource<CoinMarketData>>
    lateinit var coinChartData: LiveData<Resource<List<CoinChartData>>>

    fun setMarketData(coinId: String) {
        coinMarketData = getMarketDataUseCase(coinId).asLiveData(Dispatchers.Main)
    }

    fun setChartData(coinId: String, start: String, interval: String) {
        coinChartData = getChartDataUseCase(coinId, start, interval).asLiveData(Dispatchers.Main)
    }
}