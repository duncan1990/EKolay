package com.ahmety.mkolay.ui.canteen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.ahmety.mkolay.base.BaseFragment
import com.ahmety.mkolay.R
import com.ahmety.mkolay.databinding.FragmentCanteenBinding
import com.ahmety.mkolay.model.enum.StatusTrack
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.zxing.BarcodeFormat
import com.journeyapps.barcodescanner.BarcodeEncoder

class CanteenFragment : BaseFragment<FragmentCanteenBinding>() {
    private val canteenViewModel: CanteenViewModel by viewModels()
    private var db = FirebaseDatabase.getInstance().reference

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentCanteenBinding =
        FragmentCanteenBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setStatusBarColor(requireContext(), R.color.hibiscus)
        setupClickListener()
        generateQRCode()
        db.addValueEventListener(getData)
    }

    private fun setupClickListener() {
        binding.apply {
            viewBackBtnArea.setOnClickListener {
                navigateBack()
            }

            consShoppingHistory.setOnClickListener {
                safeNavigate(R.id.action_canteenFragment_to_shoppingHistoryFragment)
            }

            consAddNewCreditCard.setOnClickListener {
                val action = CanteenFragmentDirections.actionCanteenFragmentToAddNewCreditCardFragment(
                    "https://api-test.moneypay.com.tr:8743/#/list?userToken=73786f6d6b6e4f636f6c6e726c757371"
                )
                safeNavigate(action)
            }
        }
    }

    private fun generateQRCode() {
        val barcodeEncoder = BarcodeEncoder()
        val bitmap = barcodeEncoder.encodeBitmap(canteenViewModel.getRandomString(8), BarcodeFormat.QR_CODE, 512, 512)
        binding.imgQrcode.setImageBitmap(bitmap)
    }

    private fun goToNext() {
        safeNavigate(R.id.action_canteenFragment_to_shoppingFragment)
    }

    private var getData = object : ValueEventListener {
        override fun onDataChange(snapshot: DataSnapshot) {
            when (snapshot.child("status").value) {
                StatusTrack.GoNext.type -> goToNext()
                StatusTrack.ReGenerateQR.type -> generateQRCode()
                StatusTrack.Crash.type -> {
                    FirebaseCrashlytics.getInstance().setCustomKey("error", "QR code Error")
                    FirebaseCrashlytics.getInstance().log("QR code Error")
                    val exception = Exception("QR code Error")
                    FirebaseCrashlytics.getInstance().recordException(exception)
                }
            }
        }

        override fun onCancelled(error: DatabaseError) {
            FirebaseCrashlytics.getInstance().recordException(error.toException())
        }
    }
}