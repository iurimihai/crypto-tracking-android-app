package ro.pub.cs.systems.eim.cryptocurrencytrackingapp.ui.coin_description

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import ro.pub.cs.systems.eim.cryptocurrencytrackingapp.databinding.CoinDescriptionBinding
import ro.pub.cs.systems.eim.cryptocurrencytrackingapp.utils.Constants

class CoinDescriptionActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = CoinDescriptionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val coinId = intent?.extras?.getString(Constants.COIN_ID).toString()

        binding.tvDescriptionTest.text = coinId

//        val recyclerView = binding.recyclerView
//        recyclerView.layoutManager = LinearLayoutManager(this)

//        recyclerView.addItemDecoration(
//            DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
//        )

    }
}