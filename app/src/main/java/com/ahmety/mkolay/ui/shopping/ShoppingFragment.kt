package com.ahmety.mkolay.ui.shopping

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ahmety.mkolay.base.BaseFragment
import com.ahmety.mkolay.R
import com.ahmety.mkolay.databinding.FragmentShoppingBinding
import com.ahmety.mkolay.model.enum.StatusTrack
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class ShoppingFragment : BaseFragment<FragmentShoppingBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentShoppingBinding =
        FragmentShoppingBinding::inflate

    private var db = FirebaseDatabase.getInstance().reference

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setStatusBarColor(requireContext(), R.color.hibiscus)
        setupClickListener()
        db.addValueEventListener(getData) // listen data
    }

    private fun setupClickListener() {
        binding.apply {
            viewBackBtnArea.setOnClickListener {
                navigateBack()
            }
        }
    }

    private fun goToNext() {
        safeNavigate(R.id.action_shoppingFragment_to_successShopping)
    }

    private var getData = object : ValueEventListener {
        override fun onDataChange(snapshot: DataSnapshot) {
            if (snapshot.child("status").value == StatusTrack.Success.type) {
                goToNext()
            }
        }

        override fun onCancelled(error: DatabaseError) {
            FirebaseCrashlytics.getInstance().log("QR code Cancelled")
        }
    }

}