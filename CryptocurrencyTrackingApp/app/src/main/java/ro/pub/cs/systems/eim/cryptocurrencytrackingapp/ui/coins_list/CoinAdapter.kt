package ro.pub.cs.systems.eim.cryptocurrencytrackingapp.ui.coins_list

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.TextView
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.w3c.dom.Text
import ro.pub.cs.systems.eim.cryptocurrencytrackingapp.R
import ro.pub.cs.systems.eim.cryptocurrencytrackingapp.data.repository.CoinRepository
import ro.pub.cs.systems.eim.cryptocurrencytrackingapp.databinding.CoinViewBinding
import ro.pub.cs.systems.eim.cryptocurrencytrackingapp.domain.models.Coin
import ro.pub.cs.systems.eim.cryptocurrencytrackingapp.domain.use_cases.GetCoinsListUseCase
import ro.pub.cs.systems.eim.cryptocurrencytrackingapp.domain.use_cases.UpdatePriceUseCase
import ro.pub.cs.systems.eim.cryptocurrencytrackingapp.ui.coin_description.CoinDescriptionActivity
import ro.pub.cs.systems.eim.cryptocurrencytrackingapp.utils.Constants


class CoinAdapter(
    private val viewModel: CoinsListViewModel,
    private val lifecycleOwner: LifecycleOwner,
    private val onItemClicked: (coin: Coin) -> Unit
): RecyclerView.Adapter<CoinAdapter.CoinViewHolder>() {
    private val coinsListUseCase = GetCoinsListUseCase(CoinRepository)
    private val updatePriceUseCase = GetCoinsListUseCase(CoinRepository)


    private var coins: List<Coin> = mutableListOf(
            Coin("btc-bitcoin", "Bitcoin", "BTC", "40000"),
            Coin("eth-ethereum", "Ethereum", "ETH", "2000"),
        Coin("eth-ethereum", "Ethereum", "ETH", "2000"),
        Coin("eth-ethereum", "Ethereum", "ETH"),
        Coin("eth-ethereum", "Ethereum", "ETH"),
        Coin("eth-ethereum", "Ethereum", "ETH"),
        Coin("eth-ethereum", "Ethereum", "ETH"),
        Coin("eth-ethereum", "Ethereum", "ETH"),
        Coin("eth-ethereum", "Ethereum", "ETH"),
        Coin("eth-ethereum", "Ethereum", "ETH"),
        Coin("eth-ethereum", "Ethereum", "ETH"),
        Coin("eth-ethereum", "Ethereum", "ETH")
    )

    fun setData() {
        CoroutineScope(Dispatchers.Main).launch {
            viewModel.coins.observe(lifecycleOwner, Observer {
                coins = it
            })
        }
    }

    fun updatePrice(coin: Coin) {
        var price = viewModel.getUpdatedPrice(coin, "USDT")
        price.observe(lifecycleOwner, Observer {
            // TODO: get updated price in IO and set it in Main/Default
//            withContext(Dispatchers.Main) {
//
//            }
        })
    }

    inner class CoinViewHolder(private val coinViewBinding: CoinViewBinding):
            RecyclerView.ViewHolder(coinViewBinding.root) {

        fun bind(coin: Coin) {
            coinViewBinding.apply {
                tvCoinName.text = getNameFormat(coin)
                tvCoinPrice.text = coin.price

                root.setOnClickListener {
                    val context = root.context
                    val intent = Intent(context, CoinDescriptionActivity::class.java)

                    intent.putExtra(Constants.COIN_ID, coin.coinId)
                    context.startActivity(intent)
//                onItemClicked(coin)
                }
            }
        }
    }

    private fun getNameFormat(coin: Coin): String = "${coin.name} (${coin.symbol})"

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinViewHolder {
        val coinViewBinding = CoinViewBinding.inflate(LayoutInflater.from(parent.context),
                                                    parent,
                                        false)
        return CoinViewHolder(coinViewBinding)
    }

    override fun onBindViewHolder(holder: CoinViewHolder, position: Int) =
            holder.bind(coins[position])

    override fun getItemCount() = coins.size
}