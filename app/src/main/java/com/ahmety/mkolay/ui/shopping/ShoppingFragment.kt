package com.ahmety.mkolay.ui.shopping

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.ahmety.mkolay.R
import com.ahmety.mkolay.databinding.FragmentShoppingBinding

class ShoppingFragment: Fragment() {
    private val shoppingViewModel: ShoppingViewModel by viewModels()
    private var _binding: FragmentShoppingBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentShoppingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setStatusBarColor()
        setupClickListener()
        goToNext()
    }

    private fun setStatusBarColor() {
        val window = requireActivity().window
        window.statusBarColor = ContextCompat.getColor(requireContext(), R.color.hibiscus)
    }

    private fun setupClickListener() {
        binding.apply {
            viewBackBtnArea.setOnClickListener{
                findNavController().popBackStack()
            }

        }
    }

    private fun goToNext() {
        Handler().postDelayed({
                                  findNavController().apply {
                                      currentDestination?.getAction(R.id.action_shoppingFragment_to_successShopping)?.run {
                                          navigate(R.id.action_shoppingFragment_to_successShopping)
                                      }
                                  }
                              }, 4000)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}