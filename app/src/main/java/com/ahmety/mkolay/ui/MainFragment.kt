package com.ahmety.mkolay.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.ahmety.mkolay.databinding.FragmentMainBinding
import dagger.hilt.android.AndroidEntryPoint

class MainFragment : Fragment() {
    private val homeViewModel: HomeViewModel by viewModels()
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupClickListener()
    }

    private fun setupClickListener() {
        /*binding.button.setOnClickListener {
            throw RuntimeException("Test Crash") // Force a crash
        }*/

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}