package com.ahmety.mkolay

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.ahmety.mkolay.databinding.FragmentMainBinding

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

        setStatusBarColor()
        setupClickListener()
    }

    private fun setStatusBarColor() {
        val window = requireActivity().window
        //window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        //window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.statusBarColor = this.resources.getColor(R.color.zest)
    }

    private fun setupClickListener() {
        /*binding.button.setOnClickListener {
            throw RuntimeException("Test Crash") // Force a crash
        }*/
        binding.apply {
            viewBackBtnArea.setOnClickListener {
                requireActivity().finish()
            }

            consMkolayMarket.setOnClickListener {
                findNavController().apply {
                    currentDestination?.getAction(R.id.action_mainFragment_to_canteenFragment)?.run {
                        navigate(R.id.action_mainFragment_to_canteenFragment)
                    }
                }

                consMkolayCanteen.setOnClickListener {
                    findNavController().apply {
                        currentDestination?.getAction(R.id.action_mainFragment_to_canteenFragment)?.run {
                            navigate(R.id.action_mainFragment_to_canteenFragment)
                        }
                    }
                }
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}