package ro.pub.cs.systems.eim.cryptocurrencytrackingapp.ui.coins_list

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ro.pub.cs.systems.eim.cryptocurrencytrackingapp.R
import ro.pub.cs.systems.eim.cryptocurrencytrackingapp.data.repository.CoinRepository
import ro.pub.cs.systems.eim.cryptocurrencytrackingapp.databinding.ActivityMainBinding
import ro.pub.cs.systems.eim.cryptocurrencytrackingapp.domain.models.Coin
import ro.pub.cs.systems.eim.cryptocurrencytrackingapp.domain.use_cases.GetCoinsListUseCase
import ro.pub.cs.systems.eim.cryptocurrencytrackingapp.domain.use_cases.UpdatePriceUseCase

class MainActivity : AppCompatActivity() {
    private val coinsListUseCase = GetCoinsListUseCase(CoinRepository)
    private val updatePriceUseCase = UpdatePriceUseCase(CoinRepository)

    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        recyclerView = binding.rvCoinsList

        val adapter = CoinAdapter(
            CoinsListViewModel(
                GetCoinsListUseCase(CoinRepository),
                UpdatePriceUseCase(CoinRepository)
            ),
                this,
                this::onItemClicked
        )

        lifecycleScope.launchWhenResumed { adapter.setData() }

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun onItemClicked(coin: Coin) {
        Toast.makeText(this, coin.symbol, Toast.LENGTH_SHORT).show()
    }

}