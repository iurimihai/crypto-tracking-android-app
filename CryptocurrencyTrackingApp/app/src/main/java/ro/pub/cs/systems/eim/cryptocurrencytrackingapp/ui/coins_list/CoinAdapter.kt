package ro.pub.cs.systems.eim.cryptocurrencytrackingapp.ui.coins_list

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import ro.pub.cs.systems.eim.cryptocurrencytrackingapp.databinding.CoinViewBinding
import ro.pub.cs.systems.eim.cryptocurrencytrackingapp.domain.models.Coin
import ro.pub.cs.systems.eim.cryptocurrencytrackingapp.ui.coin_description.CoinDetailsActivity
import ro.pub.cs.systems.eim.cryptocurrencytrackingapp.utils.Constants


class CoinAdapter: RecyclerView.Adapter<CoinAdapter.CoinViewHolder>() {

    lateinit var viewModel: CoinsListViewModel
    lateinit var lifecycleOwner: LifecycleOwner
    lateinit var coinsUiState: CoinsListState

    // DEBUG
    lateinit var coins: List<Coin>


    //    private var coins: List<Coin> = mutableListOf()

    // TODO: if favorites filter -> cache data locally in sqlite
    // TODO: if new item is added -> update cache locally and update Firebase DB
    // TODO: Firebase DB will be used only first time for fetching favourites
    // TODO: all from above should be performed automatically in data layer/use case
    fun setData(coins: List<Coin>) {
//        lifecycleOwner.lifecycleScope.launch {
//            repeat(10) {
//                viewModel.uiState.collect { uiState ->
//                    coins = uiState.coins
//                }
//            }
//        }
//        while (viewModel.uiState.value.coins.isEmpty()) {
//            coinsUiState = viewModel.uiState.value
//        }
//        coinsUiState = uiState
//        notifyDataSetChanged()
        this.coins = coins
    }

    fun updatePrice(coin: Coin, tvCoinPrice: TextView) {
        val price = viewModel.getUpdatedPrice(coin)
        CoroutineScope(Dispatchers.Main).launch {
//            delay(1000L)
            price.collect { tvCoinPrice.text = it }
        }

    }

    fun priceUpdate(price: Flow<String>) {
        CoroutineScope(Dispatchers.Main).launch {
            price.collect {

            }
        }
    }

    inner class CoinViewHolder(private val coinViewBinding: CoinViewBinding):
            RecyclerView.ViewHolder(coinViewBinding.root) {

        fun bind(coin: Coin) {
            coinViewBinding.apply {
                tvCoinName.text = getNameFormat(coin)
                updatePrice(coin, tvCoinPrice)

                root.setOnClickListener { view ->
//                    val action = CoinsListFragmentDirections
//                                    .actionCoinsListFragmentToCoinDescriptionFragment(coin.coinId)
//                    root.findNavController().navigate(action)

                    Intent(view.context, CoinDetailsActivity::class.java).also {
                        it.putExtra(Constants.COIN_ID, coin.coinId)
//                        it.putExtra(Constants.COIN_NAME, coin.name)
//                        it.putExtra(Constants.COIN_SYMBOL, coin.symbol)
                        Log.d("CoinAdapter", "onclicklistener")
                        view.context.startActivity(it)
                    }
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