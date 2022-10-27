package com.ahmety.mkolay.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ahmety.mkolay.base.BaseFragment
import com.ahmety.mkolay.R
import com.ahmety.mkolay.databinding.FragmentMainBinding

class MainFragment : BaseFragment<FragmentMainBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentMainBinding =
        FragmentMainBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setStatusBarColor(requireContext(), R.color.zest)
        setupClickListener()
    }

    private fun setupClickListener() {
        binding.apply {
            viewBackBtnArea.setOnClickListener {
                requireActivity().finish()
            }

            consMkolayMarket.setOnClickListener {
                safeNavigate(R.id.action_mainFragment_to_canteenFragment)
            }

            consMkolayCanteen.setOnClickListener {
                safeNavigate(R.id.action_mainFragment_to_canteenFragment)
            }
        }

    }

}