package com.ahmety.mkolay.ui.shopping.shoppinghistory.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.ahmety.mkolay.R
import com.ahmety.mkolay.base.BaseFragment
import com.ahmety.mkolay.databinding.FragmentShoppingHistoryDetailBinding
import com.ahmety.mkolay.model.Order
import com.ahmety.mkolay.ui.shopping.shoppinghistory.detail.adapter.ShoppingHistoryDetailAdapter

class ShoppingHistoryDetailFragment : BaseFragment<FragmentShoppingHistoryDetailBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentShoppingHistoryDetailBinding =
        FragmentShoppingHistoryDetailBinding::inflate

    private var adapter: ShoppingHistoryDetailAdapter? = null
    private val args: ShoppingHistoryDetailFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUI()
        setStatusBarColor(requireContext(), R.color.tradewind)
        setupClickListener()
        setupAdapter()
    }

    private fun setUI() {
        binding.apply {
            txtDate.text = args.date
            txtMarketName.text = args.marketName
        }
    }

    private fun setupClickListener() {
        binding.apply {
            viewBackBtnArea.setOnClickListener {
                navigateBack()
            }

            imgPdf.setOnClickListener {
                val action = ShoppingHistoryDetailFragmentDirections.actionShoppingHistoryDetailFragmentToPdfFragment(
                    "https://www.africau.edu/images/default/sample.pdf"
                )
                safeNavigate(action)
            }
        }
    }

    private fun setupAdapter() {
        binding.apply {
            adapter = ShoppingHistoryDetailAdapter()
            recyclerView.adapter = adapter
            adapter?.submitData(
                mutableListOf(
                    Order(
                        id = "1",
                        picUrl = "https://migros-dali-storage-prod.global.ssl.fastly.net/sanalmarket/product/05093202/05093202-422adc-1650x1650.jpg",
                        itemName = "Eti Lifalif Yulaf Ezmesi", itemAmount = "500 gr", itemPrice = "11.95",
                        itemQuantatiy = 2
                    ),
                    Order(
                        id = "2",
                        picUrl = "https://migros-dali-storage-prod.global.ssl.fastly.net/sanalmarket/product/07155947/07155947-5ebfb0-1650x1650.png",
                        itemName = "Züber %100 Fıstık Ezmesi", itemAmount = "315 gr",
                        itemPrice = "22.95", itemQuantatiy = 5
                    ),
                    Order(
                        id = "3",
                        picUrl = "https://migros-dali-storage-prod.global.ssl.fastly.net/sanalmarket/product/3252557/3252557-eaea57-1650x1650.jpg",
                        itemName = "Starbucks Caffe Latte Premium Kahve Karışımı",
                        itemAmount = "14 gr", itemPrice = "2.95", itemQuantatiy = 1
                    )
                )
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        adapter = null
    }
}