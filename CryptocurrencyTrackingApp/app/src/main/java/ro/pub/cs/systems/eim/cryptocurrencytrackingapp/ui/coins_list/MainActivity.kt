package ro.pub.cs.systems.eim.cryptocurrencytrackingapp.ui.coins_list

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ro.pub.cs.systems.eim.cryptocurrencytrackingapp.R
import ro.pub.cs.systems.eim.cryptocurrencytrackingapp.data.repository.CoinRepository
import ro.pub.cs.systems.eim.cryptocurrencytrackingapp.databinding.ActivityMainBinding
import ro.pub.cs.systems.eim.cryptocurrencytrackingapp.domain.use_cases.GetCoinsListUseCase
import ro.pub.cs.systems.eim.cryptocurrencytrackingapp.domain.use_cases.UpdatePriceUseCase

class MainActivity : AppCompatActivity() {
    private val coinsListUseCase = GetCoinsListUseCase(CoinRepository)
    private val updatePriceUseCase = GetCoinsListUseCase(CoinRepository)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)

        val recyclerView = binding.recyclerViewList
        val adapter = CoinAdapter(
            CoinsListViewModel(
                GetCoinsListUseCase(CoinRepository),
                UpdatePriceUseCase(CoinRepository)
            ),
            this
        )

        lifecycleScope.launchWhenResumed { adapter.setData() }
        recyclerView.adapter = adapter
    }


}