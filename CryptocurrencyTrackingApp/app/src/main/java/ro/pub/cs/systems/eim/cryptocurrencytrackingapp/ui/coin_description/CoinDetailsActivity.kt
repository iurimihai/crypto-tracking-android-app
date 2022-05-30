package ro.pub.cs.systems.eim.cryptocurrencytrackingapp.ui.coin_description

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import ro.pub.cs.systems.eim.cryptocurrencytrackingapp.R
import ro.pub.cs.systems.eim.cryptocurrencytrackingapp.databinding.ActivityCoinDetailsBinding
import ro.pub.cs.systems.eim.cryptocurrencytrackingapp.databinding.ActivityMainBinding
import ro.pub.cs.systems.eim.cryptocurrencytrackingapp.utils.Constants

class CoinDetailsActivity : AppCompatActivity() {
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityCoinDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        coinId = intent?.extras?.getString(Constants.COIN_ID)
//        coinId = intent?.extras?.getString(Constants.COIN_ID)


        val navHostFragment = supportFragmentManager
                .findFragmentById(R.id.coin_details_fragment_container) as NavHostFragment
        navController = navHostFragment.navController
        setArgsToNavController(navController)
        setupActionBarWithNavController(navController)

    }

    private fun setArgsToNavController(navController: NavController) {
        val currentBundle = intent?.extras

        val args = Bundle()
        args.putString(Constants.COIN_ID, currentBundle?.getString(Constants.COIN_ID))
//        args.putString(Constants.COIN_NAME, currentBundle?.getString(Constants.COIN_NAME))
//        args.putString(Constants.COIN_SYMBOL, currentBundle?.getString(Constants.COIN_SYMBOL))

        navController.setGraph(navController.graph, args)
    }
}