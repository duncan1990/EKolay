package com.ahmety.mkolay.shoppinghistory.detail.pdf

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.ahmety.mkolay.base.BaseFragment
import com.ahmety.mkolay.databinding.FragmentAddCreditCardWebviewBinding
import com.ahmety.mkolay.databinding.FragmentPdfWebviewBinding

class PdfFragment : BaseFragment<FragmentPdfWebviewBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentPdfWebviewBinding =
        FragmentPdfWebviewBinding::inflate

    private val args: PdfFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setWebViewPdfUrl()
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun setWebViewPdfUrl() {
        binding.apply {
            webView.settings.javaScriptEnabled = true
            webView.settings.setSupportZoom(true)
            webView.loadUrl("https://drive.google.com/viewerng/viewer?embedded=true&url=${args.pdfUrl}")
        }
    }

}