package com.esaudev.gamesgarden.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.esaudev.gamesgarden.R
import com.esaudev.gamesgarden.databinding.FragmentHomeBinding
import com.esaudev.gamesgarden.di.DataModule.FeaturesList
import com.esaudev.gamesgarden.ui.components.QuantityDialog
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding
        get() = _binding!!

    private val featuresAdapter = FeaturesAdapter()

    @Inject
    @FeaturesList
    lateinit var featuresList: List<String>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        return FragmentHomeBinding
            .inflate(inflater, container, false)
            .apply { _binding = this }
            .root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()

        featuresAdapter.setItemClickListener {
            findNavController().navigate(R.id.toInitialFragment)
        }

        binding.bShowDialog.setOnClickListener {
            QuantityDialog(
                onSubmitClickListener = { quantity ->
                    Toast.makeText(requireContext(), "Usted ingreso: $quantity", Toast.LENGTH_SHORT).show()
                }
            ).show(parentFragmentManager, "dialog")
        }
    }

    private fun init(){

        featuresAdapter.submitList(featuresList)

        binding.rvFeatures.apply {
            this.adapter = featuresAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }



}