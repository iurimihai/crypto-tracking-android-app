package ro.pub.cs.systems.eim.cryptocurrencytrackingapp.ui.coin_description

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ro.pub.cs.systems.eim.cryptocurrencytrackingapp.R
import ro.pub.cs.systems.eim.cryptocurrencytrackingapp.databinding.FragmentCoinDescriptionBinding
import ro.pub.cs.systems.eim.cryptocurrencytrackingapp.utils.Constants

class CoinDescriptionFragment : Fragment() {

    private var _binding: FragmentCoinDescriptionBinding? = null
    private lateinit var coinId: String

    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            coinId = it.getString(Constants.COIN_ID).toString()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCoinDescriptionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.tvCoinNameFg.text = coinId
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}