package ro.pub.cs.systems.eim.cryptocurrencytrackingapp.ui.coins_list

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import ro.pub.cs.systems.eim.cryptocurrencytrackingapp.data.repository.CoinRepository
import ro.pub.cs.systems.eim.cryptocurrencytrackingapp.databinding.CoinViewBinding
import ro.pub.cs.systems.eim.cryptocurrencytrackingapp.domain.models.Coin
import ro.pub.cs.systems.eim.cryptocurrencytrackingapp.domain.use_cases.GetCoinsListUseCase


class CoinAdapter(
    private val viewModel: CoinsListViewModel,
    private val lifecycleOwner: LifecycleOwner
): RecyclerView.Adapter<CoinAdapter.CoinViewHolder>() {
    private val coinsListUseCase = GetCoinsListUseCase(CoinRepository)
    private val updatePriceUseCase = GetCoinsListUseCase(CoinRepository)

    private var coins = mutableListOf<Coin>(
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

//    private var coins: List<Coin> = mutableListOf()

    // TODO: if favorites filter -> cache data locally in sqlite
    // TODO: if new item is added -> update cache locally and update Firebase DB
    fun setData() {
        viewModel.coins.observe(lifecycleOwner, Observer {
//            coins = it.slice(0..10)
//            delay(2000L)
        })
    }

    fun updatePrice(coin: Coin, tvCoinPrice: TextView) {
        val price = viewModel.getUpdatedPrice(coin, "USDT")
        CoroutineScope(Dispatchers.Main).launch {
            price.collect { tvCoinPrice.text = it }
        }

    }

    inner class CoinViewHolder(private val coinViewBinding: CoinViewBinding):
            RecyclerView.ViewHolder(coinViewBinding.root) {

        fun bind(coin: Coin) {
            coinViewBinding.apply {
                tvCoinName.text = getNameFormat(coin)
                updatePrice(coin, tvCoinPrice)

                root.setOnClickListener {
                    val action = CoinsListFragmentDirections
                                    .actionCoinsListFragmentToCoinDescriptionFragment(coin.coinId)
                    root.findNavController().navigate(action)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinViewHolder {
        val coinViewBinding = CoinViewBinding.inflate(LayoutInflater.from(parent.context),
                                                    parent,
                                        false)
        return CoinViewHolder(coinViewBinding)
    }

    override fun onBindViewHolder(holder: CoinViewHolder, position: Int) =
            holder.bind(coins[position])

    override fun getItemCount() = coins.size

    private fun getNameFormat(coin: Coin): String = "${coin.name} (${coin.symbol})"
}