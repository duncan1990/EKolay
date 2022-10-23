package com.ahmety.mkolay.ui.canteen

import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.ahmety.mkolay.R
import com.ahmety.mkolay.databinding.FragmentCanteenBinding


class CanteenFragment : Fragment() {
    private val canteenViewModel: CanteenViewModel by viewModels()
    private var _binding: FragmentCanteenBinding? = null
    private val binding get() = _binding!!

    // on below line we are creating
    // a variable for bitmap
    lateinit var bitmap: Bitmap

    // on below line we are creating
    // a variable for qr encoder.
    //lateinit var qrEncoder: QRGEncoder

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCanteenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setStatusBarColor()
        setupClickListener()
    }

    private fun setStatusBarColor() {
        val window = requireActivity().window
        window.statusBarColor = this.resources.getColor(R.color.hibiscus)
    }

    private fun setupClickListener() {
        binding.apply {
            viewBackBtnArea.setOnClickListener{
                findNavController().popBackStack()
            }
        }
    }

}