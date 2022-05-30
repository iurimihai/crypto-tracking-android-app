package ro.pub.cs.systems.eim.cryptocurrencytrackingapp.ui.coin_description

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.github.mikephil.charting.data.CandleEntry
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import ro.pub.cs.systems.eim.cryptocurrencytrackingapp.databinding.FragmentCoinMarketDataBinding
import ro.pub.cs.systems.eim.cryptocurrencytrackingapp.utils.Constants
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

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
        viewModel.setMarketData(coinId!!)
        viewModel.setChartData(coinId!!, "2022-01-01T00:00:00Z", "1d")
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
                tableVolUSDDataTextView.text = it.data?.volume24h?.toString()
            }
        })
//        createChartEntries("2021-01-01T00:00:00Z", "1d")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    private fun createChartEntries(start: String, interval: String) {
        val entries: MutableList<Entry> = mutableListOf()
        viewModel.coinChartData.observe(viewLifecycleOwner, Observer { result ->
            // DEBUG
            for (i in 1..5) {
                entries.add(Entry(i.toFloat(), (i * i).toFloat()))
            }

//            result.data?.get(i)?.price?.toFloat() ?: 0.toFloat()
//            result.data?.map { entries.add(Entry(it., it.price.toFloat())) }
        })
        val chartDataSet = LineDataSet(entries, "Crypto chart")
        binding?.chart?.data = LineData(chartDataSet)
    }

//    private fun getPeriodInDays(start: String, end: String): Int {
//        val datePattern = DateTimeFormatter.ofPattern("yyyy-MM-ddTHH:mm:ssZ")
//        val startDate = LocalDateTime.parse(start.toCharArray())
//    }
}