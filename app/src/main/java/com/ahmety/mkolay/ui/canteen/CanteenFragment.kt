package com.ahmety.mkolay.ui.canteen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.ahmety.mkolay.R
import com.ahmety.mkolay.databinding.FragmentCanteenBinding
import com.google.zxing.BarcodeFormat
import com.journeyapps.barcodescanner.BarcodeEncoder

class CanteenFragment : Fragment() {
    private val canteenViewModel: CanteenViewModel by viewModels()
    private var _binding: FragmentCanteenBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCanteenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setStatusBarColor()
        setupClickListener()
        generateQRCode()
    }

    private fun setStatusBarColor() {
        val window = requireActivity().window
        window.statusBarColor = ContextCompat.getColor(requireContext(), R.color.hibiscus)
    }

    private fun setupClickListener() {
        binding.apply {
            viewBackBtnArea.setOnClickListener {
                findNavController().popBackStack()
            }

            consShoppingHistory.setOnClickListener {
                findNavController().apply {
                    currentDestination?.getAction(R.id.action_canteenFragment_to_shoppingHistoryFragment)?.run {
                        navigate(R.id.action_canteenFragment_to_shoppingHistoryFragment)
                    }
                }
            }

            consAddNewCreditCard.setOnClickListener {
                generateQRCode()
            }

            imgQrcode.setOnClickListener {
                goToNext()
            }

        }
    }

    private fun generateQRCode() {
        val barcodeEncoder = BarcodeEncoder()
        val bitmap = barcodeEncoder.encodeBitmap(canteenViewModel.getRandomString(8), BarcodeFormat.QR_CODE, 512, 512)
        binding.imgQrcode.setImageBitmap(bitmap)
    }

    private fun goToNext() {
        findNavController().apply {
            currentDestination?.getAction(R.id.action_canteenFragment_to_shoppingFragment)?.run {
                navigate(R.id.action_canteenFragment_to_shoppingFragment)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}