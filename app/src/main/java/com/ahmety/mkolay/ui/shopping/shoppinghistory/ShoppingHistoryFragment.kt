package com.ahmety.mkolay.ui.shopping.shoppinghistory

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ahmety.mkolay.R
import com.ahmety.mkolay.base.BaseFragment
import com.ahmety.mkolay.databinding.FragmentShoppingHistoryBinding
import com.ahmety.mkolay.extension.toString
import com.ahmety.mkolay.model.ShoppingHistory
import com.ahmety.mkolay.ui.shopping.shoppinghistory.adapter.ShoppingHistoryAdapter
import java.text.SimpleDateFormat
import java.util.*

class ShoppingHistoryFragment : BaseFragment<FragmentShoppingHistoryBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentShoppingHistoryBinding =
        FragmentShoppingHistoryBinding::inflate

    private var adapter: ShoppingHistoryAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setStatusBarColor(requireContext(), R.color.tradewind)
        setupClickListener()
        setupAdapter()
    }

    private fun setupClickListener() {
        binding.apply {
            viewBackBtnArea.setOnClickListener {
                navigateBack()
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
            safeNavigate(action)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        adapter = null
    }

}