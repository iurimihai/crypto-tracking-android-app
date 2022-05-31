package ro.pub.cs.systems.eim.cryptocurrencytrackingapp.ui.coins_list

import android.os.Bundle
import android.os.SystemClock.sleep
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ro.pub.cs.systems.eim.cryptocurrencytrackingapp.R
import ro.pub.cs.systems.eim.cryptocurrencytrackingapp.data.repository.CoinRepository
import ro.pub.cs.systems.eim.cryptocurrencytrackingapp.databinding.FragmentCoinDescriptionBinding
import ro.pub.cs.systems.eim.cryptocurrencytrackingapp.databinding.FragmentCoinsListBinding
import ro.pub.cs.systems.eim.cryptocurrencytrackingapp.domain.use_cases.GetCoinDescriptionUseCase
import ro.pub.cs.systems.eim.cryptocurrencytrackingapp.domain.use_cases.GetCoinsListUseCase
import ro.pub.cs.systems.eim.cryptocurrencytrackingapp.domain.use_cases.UpdatePriceUseCase
import ro.pub.cs.systems.eim.cryptocurrencytrackingapp.utils.Constants

class CoinsListFragment : Fragment() {

    private var _binding: FragmentCoinsListBinding? = null
    private lateinit var recyclerView: RecyclerView
    private lateinit var coinAdapter: CoinAdapter
    private lateinit var viewModel: CoinsListViewModel
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
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@CoinsListFragment.requireContext())
            coinAdapter = CoinAdapter()
            adapter = coinAdapter
        }

        viewModel = ViewModelProvider(requireActivity())[CoinsListViewModel::class.java]

        // TODO: remove viewModel
        // TODO: use paging3 for price update
        viewModel.coins.observe(viewLifecycleOwner, Observer {
            coinAdapter.setData(it.data ?: emptyList())
            coinAdapter.notifyDataSetChanged()
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}