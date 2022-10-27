package com.ahmety.mkolay.ui.creditcard

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.ahmety.mkolay.base.BaseFragment
import com.ahmety.mkolay.databinding.FragmentAddCreditCardWebviewBinding

class AddNewCreditCardFragment: BaseFragment<FragmentAddCreditCardWebviewBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentAddCreditCardWebviewBinding =
        FragmentAddCreditCardWebviewBinding::inflate

    private val args: AddNewCreditCardFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setWebViewUrl()
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun setWebViewUrl() {
        binding.apply {
            webView.settings.javaScriptEnabled = true
            webView.loadUrl(args.webUrl)
        }
    }

}