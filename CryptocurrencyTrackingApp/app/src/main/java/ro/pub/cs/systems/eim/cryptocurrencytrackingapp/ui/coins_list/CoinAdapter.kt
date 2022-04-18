package ro.pub.cs.systems.eim.cryptocurrencytrackingapp.ui.coins_list

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import ro.pub.cs.systems.eim.cryptocurrencytrackingapp.R
import ro.pub.cs.systems.eim.cryptocurrencytrackingapp.data.repository.CoinRepository
import ro.pub.cs.systems.eim.cryptocurrencytrackingapp.domain.models.Coin
import ro.pub.cs.systems.eim.cryptocurrencytrackingapp.domain.use_cases.GetCoinsListUseCase
import ro.pub.cs.systems.eim.cryptocurrencytrackingapp.domain.use_cases.UpdatePriceUseCase
import ro.pub.cs.systems.eim.cryptocurrencytrackingapp.ui.coin_description.CoinDescriptionActivity
import ro.pub.cs.systems.eim.cryptocurrencytrackingapp.utils.Constants


class CoinAdapter(
    private val viewModel: CoinsListViewModel,
    private val lifecycleOwner: LifecycleOwner
): RecyclerView.Adapter<CoinAdapter.CoinViewHolder>() {
    private val coinsListUseCase = GetCoinsListUseCase(CoinRepository)
    private val updatePriceUseCase = GetCoinsListUseCase(CoinRepository)


    private lateinit var coins: List<Coin>

    fun setData() {
        CoroutineScope(Dispatchers.Main).launch {
            viewModel.coins.observe(lifecycleOwner, Observer {
                coins = it
            })
        }
    }

    inner class CoinViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val coinNameTv: TextView = itemView.findViewById(R.id.coin_name)
        val coinPriceTv: TextView = itemView.findViewById(R.id.coin_price)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinViewHolder {
        val layout = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.activity_main, parent, false)
        return CoinViewHolder(layout)
    }

    override fun onBindViewHolder(holder: CoinViewHolder, position: Int) {
        val coin = coins[position]
        val coinName = "${coin.name} (${coin.symbol})"

        holder.coinNameTv.text = coinName
        CoroutineScope(Dispatchers.Main).launch {
            viewModel.fetchCoinPrice(coin, Constants.DEFAULT_REF_CURRENCY).collect {
                holder.coinPriceTv.text = it
            }
        }

        holder.itemView.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, CoinDescriptionActivity::class.java)

            intent.putExtra(Constants.COIN_ID, coin.coinId)
            context.startActivity(intent)
        }
    }

    override fun getItemCount() = coins.size
}