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

// TODO: error checks
class CoinsListViewModel(
    private val getCoinsListUseCase: GetCoinsListUseCase,
    private val updatePriceUseCase: UpdatePriceUseCase
    ) : ViewModel() {

    private val _coins = mutableListOf<Coin>(
            Coin("btc-bitcoin", "Bitcoin", "BTC"),
            Coin("eth-ethereum", "Ethereum", "ETH"),
            Coin("xrp-xrp", "XRP", "XRP"),
            Coin("ada-cardano", "Cardano", "ADA"),
            Coin("sol-solana", "Solana", "SOL"),
            Coin("btc-bitcoin", "Bitcoin", "BTC"),
            Coin("eth-ethereum", "Ethereum", "ETH"),
            Coin("xrp-xrp", "XRP", "XRP"),
            Coin("ada-cardano", "Cardano", "ADA"),
            Coin("sol-solana", "Solana", "SOL"),
            Coin("btc-bitcoin", "Bitcoin", "BTC"),
            Coin("eth-ethereum", "Ethereum", "ETH"),
            Coin("xrp-xrp", "XRP", "XRP"),
            Coin("ada-cardano", "Cardano", "ADA"),
            Coin("sol-solana", "Solana", "SOL")
    )

    val coins: MutableList<Coin>
        get() = _coins

//    private val _coinPrice: MutableLiveData<String> by lazy { MutableLiveData() }
//    var coinPrice: LiveData<String>
//        get() = _coinPrice

//    init {
//        fetchCoins()
//    }

//    private fun fetchCoins() = viewModelScope.launch {
//        _coins.value = withContext(Dispatchers.IO) {
//            getCoinsListUseCase()
//        }
//    }

    // TODO: or fetchCoins(favorite: bool)
    fun fetchFavoriteCoins() {

    }

    fun getUpdatedPrice(coin: Coin, refCurrency: String) =
        updatePriceUseCase(coin.symbol, refCurrency)

    fun getFavoriteCoins() {

    }

    fun updateFavoriteCoins() {

    }
}