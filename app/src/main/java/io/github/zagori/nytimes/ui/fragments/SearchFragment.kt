package io.github.zagori.nytimes.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import io.github.zagori.nytimes.R
import io.github.zagori.nytimes.databinding.FragmentSearchBinding
import io.github.zagori.nytimes.models.ListType
import io.github.zagori.nytimes.viewmodels.ArticlesViewModel

class SearchFragment : Fragment() {

    private lateinit var binding: FragmentSearchBinding
    private val viewModel: ArticlesViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(inflater, container, false)

        // Set the navigation action
        binding.toolbar.setNavigationOnClickListener { requireActivity().onBackPressed() }

        // Enable search button only if search input has text
        binding.searchInput.doOnTextChanged { text, _, _, _ ->
            binding.searchBtn.isEnabled = !text.isNullOrBlank()
        }

        // set up click listener on search button
        binding.searchBtn.setOnClickListener {
            viewModel.query = binding.searchInput.text.toString()

            // reset page to 1
            viewModel.page = 1

            // trigger the network call
            viewModel.loadAndSaveSearched(viewModel.query)

            // then redirect to view article list
            val bundle = bundleOf(ArticlesFragment.ARG_KEY_TYPE to ListType.Search.name)
            Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)
                .navigate(R.id.view_searched_action, bundle)
        }
        return binding.root
    }
}