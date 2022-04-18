package ro.pub.cs.systems.eim.cryptocurrencytrackingapp.presentation.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.lifecycle.whenStarted
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import ro.pub.cs.systems.eim.cryptocurrencytrackingapp.R
import ro.pub.cs.systems.eim.cryptocurrencytrackingapp.data.repository.CoinRepository
import ro.pub.cs.systems.eim.cryptocurrencytrackingapp.domain.models.Coin
import ro.pub.cs.systems.eim.cryptocurrencytrackingapp.domain.use_cases.GetCoinsListUseCase
import ro.pub.cs.systems.eim.cryptocurrencytrackingapp.domain.use_cases.UpdatePriceUseCase
import ro.pub.cs.systems.eim.cryptocurrencytrackingapp.presentation.view_models.CoinsListViewModel

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textView = findViewById<TextView>(R.id.tvId)
        val textView2 = findViewById<TextView>(R.id.tvId2)
        val coin = Coin("eth-ethereum", "Ethereum", "ETH")
        val coin2 = Coin("btc-bitcoin", "Bitcoin", "BTC")

        val priceUS = UpdatePriceUseCase(CoinRepository)
        val coinsUS = GetCoinsListUseCase(CoinRepository)

//        CoroutineScope(Dispatchers.Main).launch {
//            val price = priceUS(coin.symbol, "USDT")
//            price.collect {
//                textView.text = it
//                Log.d("Coin price", it)
//            }
//        }

        val vm = CoinsListViewModel(coinsUS, priceUS)

//        val
        CoroutineScope(Dispatchers.Default).launch {
            lifecycle.whenStarted {
                val coinPrice = vm.getUpdatedPrice(coin, "USDT")
                coinPrice.collect { textView.text = it }
            }
        }

        CoroutineScope(Dispatchers.Default).launch {
            lifecycle.whenStarted {
                val coinPrice = vm.getUpdatedPrice(coin2, "USDT")
                coinPrice.collect { textView2.text = it }
            }
        }

//        vm.fetchCoinPrice(coin, "USDT")

//        vm.getCoinPriceUpdate(coin, "USDT").observe()

//        CoroutineScope(Dispatchers.IO).launch { )
//            Log.d("Get all coins", coinsUS().toString())
//        }

//        vm.fetchCoinPrice(vm.coins.value!![1], "USDT", textView)
    }
}