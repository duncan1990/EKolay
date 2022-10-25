package com.ahmety.mkolay.shoppinghistory

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.ahmety.mkolay.R
import com.ahmety.mkolay.databinding.FragmentShoppingHistoryBinding
import com.ahmety.mkolay.model.ShoppingHistory
import com.ahmety.mkolay.shoppinghistory.adapter.ShoppingHistoryAdapter
import com.ahmety.mkolay.shoppinghistory.adapter.toString
import java.text.SimpleDateFormat
import java.util.*

class ShoppingHistoryFragment : Fragment() {

    private var _binding: FragmentShoppingHistoryBinding? = null
    private val binding get() = _binding!!
    private var adapter: ShoppingHistoryAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentShoppingHistoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setStatusBarColor()
        setupClickListener()
        setupAdapter()
    }

    private fun setStatusBarColor() {
        val window = requireActivity().window
        window.statusBarColor = ContextCompat.getColor(requireContext(), R.color.tradewind)
    }

    private fun setupClickListener() {
        binding.apply {
            viewBackBtnArea.setOnClickListener {
                findNavController().popBackStack()
            }
        }
    }

    private fun setupAdapter() {
        val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val date = formatter.parse("2021-10-25")
        binding.apply {
            adapter = ShoppingHistoryAdapter(::onClickItem)
            recyclerViewHistoryShopping.adapter = adapter
            adapter?.submitList(
                mutableListOf(
                    date?.let {
                        ShoppingHistory(
                            id = "1", marketName = "ATAŞEHİR MMM MİGROS", date = it, price = 54.24
                        )
                    },
                    date?.let {
                        ShoppingHistory(
                            id = "2", marketName = "ŞİŞLİ HÜRRİYET M MİGROS", date = it, price = 32.25,
                        )
                    },
                    date?.let {
                        ShoppingHistory(
                            id = "3", marketName = "NİŞANTAŞI MM MİGROS", date = it, price = 63.29,
                        )
                    }
                )
            )
        }
    }

    private fun onClickItem(item: ShoppingHistory) {
        val dateString = item.date.toString("dd MMMM yyyy")
        dateString?.let {
            val action = ShoppingHistoryFragmentDirections.actionShoppingHistoryFragmentToShoppingHistoryDetailFragment(
                item.marketName, dateString
            )
            findNavController().apply {
                currentDestination?.getAction(action.actionId)?.run {
                    navigate(action)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        adapter = null
    }

}