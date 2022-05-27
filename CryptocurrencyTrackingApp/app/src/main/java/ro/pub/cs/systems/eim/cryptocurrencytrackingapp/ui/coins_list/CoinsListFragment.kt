package ro.pub.cs.systems.eim.cryptocurrencytrackingapp.ui.coins_list

import android.os.Bundle
import android.os.SystemClock.sleep
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ro.pub.cs.systems.eim.cryptocurrencytrackingapp.R
import ro.pub.cs.systems.eim.cryptocurrencytrackingapp.data.repository.CoinRepository
import ro.pub.cs.systems.eim.cryptocurrencytrackingapp.databinding.FragmentCoinDescriptionBinding
import ro.pub.cs.systems.eim.cryptocurrencytrackingapp.databinding.FragmentCoinsListBinding
import ro.pub.cs.systems.eim.cryptocurrencytrackingapp.domain.use_cases.GetCoinsListUseCase
import ro.pub.cs.systems.eim.cryptocurrencytrackingapp.domain.use_cases.UpdatePriceUseCase
import ro.pub.cs.systems.eim.cryptocurrencytrackingapp.utils.Constants

class CoinsListFragment : Fragment() {

    private var _binding: FragmentCoinsListBinding? = null
    private lateinit var recyclerView: RecyclerView
    private var favorite = false

    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCoinsListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerView = binding.rvCoinsListFg

        val adapter = CoinAdapter(
            CoinsListViewModel(
                GetCoinsListUseCase(CoinRepository),
                UpdatePriceUseCase(CoinRepository)
            ),
            this
        )

//        lifecycleScope.launchWhenResumed { adapter.setData() }
//
//        sleep(5000)

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this.requireContext())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}