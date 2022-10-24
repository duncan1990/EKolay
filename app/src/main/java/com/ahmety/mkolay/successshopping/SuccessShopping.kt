package com.ahmety.mkolay.successshopping

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.ahmety.mkolay.R
import com.ahmety.mkolay.databinding.FragmentSuccessShoppingBinding
import com.ahmety.mkolay.model.Order
import com.ahmety.mkolay.successshopping.adapter.SuccessShoppingAdapter

class SuccessShopping : Fragment() {
    private var _binding: FragmentSuccessShoppingBinding? = null
    private val binding get() = _binding!!
    private var adapter: SuccessShoppingAdapter? = null
    private val url = "https://migros-dali-storage-prod.global.ssl.fastly.net/sanalmarket/product/07010426/07010426-ebdfad-1650x1650.jpg"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSuccessShoppingBinding.inflate(inflater, container, false)
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
        window.statusBarColor = ContextCompat.getColor(requireContext(), R.color.dark_hibiscus)
    }

    private fun setupClickListener() {
        binding.apply {
            btnReturnMainPage.setOnClickListener {
                findNavController().apply {
                    currentDestination?.getAction(R.id.action_successShopping_to_mainFragment)?.run {
                        navigate(R.id.action_successShopping_to_mainFragment)
                    }
                }
            }
        }
    }

    private fun setupAdapter() {
        binding.apply {
            adapter = SuccessShoppingAdapter()
            recyclerViewOrder.adapter = adapter
            adapter?.submitList(
                mutableListOf(
                    Order(
                        id = "1", picUrl = url, itemName = "Eti Negro", itemPrice = 5.95, itemAmount = "100 gr",
                        itemQuantatiy = "16 adet"
                    ),
                    Order(
                        id = "2", picUrl = url, itemName = "Eti Negro", itemPrice = 5.95, itemAmount = "100 gr",
                        itemQuantatiy = "16 adet"
                    ),
                    Order(
                        id = "3", picUrl = url, itemName = "Eti Negro", itemPrice = 5.95, itemAmount = "100 gr",
                        itemQuantatiy = "16 adet"
                    ),
                    Order(
                        id = "4", picUrl = url, itemName = "Eti Negro", itemPrice = 5.95, itemAmount = "100 gr",
                        itemQuantatiy = "16 adet"
                    ),
                    Order(
                        id = "5", picUrl = url, itemName = "Eti Negro", itemPrice = 5.95, itemAmount = "100 gr",
                        itemQuantatiy = "16 adet"
                    ),
                    Order(
                        id = "6", picUrl = url, itemName = "Eti Negro", itemPrice = 5.95, itemAmount = "100 gr",
                        itemQuantatiy = "16 adet"
                    )
                )
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        adapter = null
    }

}