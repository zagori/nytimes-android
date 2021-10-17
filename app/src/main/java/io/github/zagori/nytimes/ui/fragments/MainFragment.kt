package io.github.zagori.nytimes.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import io.github.zagori.nytimes.R
import io.github.zagori.nytimes.databinding.FragmentMainBinding
import io.github.zagori.nytimes.models.ListType

class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding
    private val navController by lazy {
        Navigation.findNavController(
            requireActivity(),
            R.id.nav_host_fragment
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        setupActions()
        return binding.root
    }

    private fun setupActions() {

        // Navigate to most viewed
        binding.itemMostViewed.setOnClickListener {
            val bundle = bundleOf(ArticlesFragment.ARG_KEY_TYPE to ListType.Viewed.name)

            navController.navigate(R.id.view_most_viewed_action, bundle)
        }

        // Navigate to most shared
        binding.itemMostShared.setOnClickListener {
            val bundle = bundleOf(ArticlesFragment.ARG_KEY_TYPE to ListType.Shared.name)

            navController.navigate(R.id.view_most_shared_action, bundle)
        }

        // Navigate to most emailed
        binding.itemMostEmailed.setOnClickListener {
            val bundle = bundleOf(ArticlesFragment.ARG_KEY_TYPE to ListType.Emailed.name)

            navController.navigate(R.id.view_most_emailed_action, bundle)
        }

        // Navigate to search fragment
        binding.itemSearch.setOnClickListener {

        }
    }
}