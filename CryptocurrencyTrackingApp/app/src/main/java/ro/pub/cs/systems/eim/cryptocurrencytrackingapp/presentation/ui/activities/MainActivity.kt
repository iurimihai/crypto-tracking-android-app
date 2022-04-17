package ro.pub.cs.systems.eim.cryptocurrencytrackingapp.presentation.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import ro.pub.cs.systems.eim.cryptocurrencytrackingapp.R
import ro.pub.cs.systems.eim.cryptocurrencytrackingapp.data.repository.CoinRepository
import ro.pub.cs.systems.eim.cryptocurrencytrackingapp.domain.models.Coin
import ro.pub.cs.systems.eim.cryptocurrencytrackingapp.domain.use_cases.UpdatePriceUseCase

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textView = findViewById<TextView>(R.id.tvId)
        val coin = Coin("eth-ethereum", "Ethereum", "ETH")

        val priceUS = UpdatePriceUseCase(CoinRepository)

        CoroutineScope(Dispatchers.Main).launch {
            val price = priceUS(coin.symbol, "USDT")
            price.collect {
//                textView.text = it.toString()
                Log.d("Coin price", it)
            }
        }
    }
}