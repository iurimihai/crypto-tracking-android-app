package ro.pub.cs.systems.eim.cryptocurrencytrackingapp.ui.coin_description

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import ro.pub.cs.systems.eim.cryptocurrencytrackingapp.R
import ro.pub.cs.systems.eim.cryptocurrencytrackingapp.databinding.FragmentCoinMarketDataBinding
import ro.pub.cs.systems.eim.cryptocurrencytrackingapp.ui.coins_list.CoinsListViewModel
import ro.pub.cs.systems.eim.cryptocurrencytrackingapp.utils.Constants

class CoinMarketDataFragment : Fragment() {
    private var coinId: String? = null
    private var coinName: String? = null
    private var coinSymbol: String? = null

    private var binding: FragmentCoinMarketDataBinding? = null
    private lateinit var viewModel: CoinMarketDataViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            coinId = it.getString(Constants.COIN_ID)
//            coinName = it.getString(Constants.COIN_NAME)
//            coinSymbol = it.getString(Constants.COIN_SYMBOL)
        }

        viewModel = ViewModelProvider(requireActivity())[CoinMarketDataViewModel::class.java]
        viewModel.setData(coinId!!)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCoinMarketDataBinding.inflate(inflater, container, false)
        return binding?.root
    }

    // TODO: Here print data
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.coinMarketData.observe(viewLifecycleOwner, Observer {
            binding?.apply {
                tableNameDataTextView.text = it.data?.name
                tablePriceUSDDataTextView.text = it.data?.price?.toString()
                tableMktCapDataTextView.text = it.data?.marketCap?.toString()
                tableCirculatingSupplyDataTextView.text = it.data?.circulatingSupply?.toString()
                tableTotalSupplyDataTextView.text = it.data?.totalSupply?.toString()
                tableMaxSupplyDataTextView.text = it.data?.maxSupply?.toString()
                table1hrChangeDataTextView.text = it.data?.percentChange1h?.toString()
                table24hrChangeDataTextView.text = it.data?.percentChange24h?.toString()
                tableWeekChangeDataTextView.text = it.data?.percentChange7d?.toString()
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    private fun createChart() {

    }
}