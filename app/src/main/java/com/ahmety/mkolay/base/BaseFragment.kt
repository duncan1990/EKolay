package com.ahmety.mkolay.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.IdRes
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding

abstract class BaseFragment <Binding : ViewBinding> : Fragment() {

    private var _binding: Binding? = null
    val binding get() = _binding!!

    abstract val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> Binding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = bindingInflater(inflater, container, false)
        return binding.root
    }

    fun safeNavigate(@IdRes resId: Int) {
        findNavController().apply {
            currentDestination?.getAction(resId)?.run {
                navigate(resId)
            }
        }
    }

    fun safeNavigate(routeAction: NavDirections) {
        findNavController().apply {
            currentDestination?.getAction(routeAction.actionId)?.run {
                navigate(routeAction)
            }
        }
    }

    fun safeNavigate(@IdRes resId: Int, args: Bundle?) {
        findNavController().apply {
            currentDestination?.getAction(resId)?.run {
                navigate(resId, args)
            }
        }
    }

    fun navigateBack() {
        findNavController().popBackStack()
    }

    fun setStatusBarColor(context: Context, color: Int) {
        val window = requireActivity().window
        window.statusBarColor = ContextCompat.getColor(context, color)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}