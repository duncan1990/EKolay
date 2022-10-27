package com.ahmety.mkolay.successshopping

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.ahmety.mkolay.base.BaseFragment
import com.ahmety.mkolay.R
import com.ahmety.mkolay.databinding.FragmentSuccessShoppingBinding
import com.ahmety.mkolay.successshopping.adapter.SuccessShoppingAdapter

class SuccessShoppingFragment : BaseFragment<FragmentSuccessShoppingBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentSuccessShoppingBinding =
        FragmentSuccessShoppingBinding::inflate

    private val viewModel: SuccessShoppingViewModel by viewModels()
    private var adapter: SuccessShoppingAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setStatusBarColor(requireContext(), R.color.dark_hibiscus)
        setupClickListener()
        setupAdapter()
        observeUI()
    }

    private fun observeUI() {
        viewModel.liveText.observe(viewLifecycleOwner, Observer {
            binding.txtFee.text = getString(R.string.price_with_turkish_lira, it).replace(".0", ".00")
        })
    }

    private fun setupClickListener() {
        binding.apply {
            btnReturnMainPage.setOnClickListener {
                safeNavigate(R.id.action_successShopping_to_mainFragment)
            }
        }
    }

    private fun setupAdapter() {
        binding.apply {
            adapter = SuccessShoppingAdapter()
            recyclerViewOrder.adapter = adapter

            viewModel.order.observe(viewLifecycleOwner, Observer {
                adapter?.submitList(mutableListOf(it))
            })
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

        adapter = null
    }

}