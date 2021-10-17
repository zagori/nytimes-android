package io.github.zagori.nytimes.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.github.zagori.nytimes.databinding.FragmentArticlesBinding

class ArticlesFragment : Fragment() {

    private lateinit var binding: FragmentArticlesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentArticlesBinding.inflate(inflater, container, false)

        // Set the navigation action
        binding.toolbar.setNavigationOnClickListener { requireActivity().onBackPressed() }

        // Set the toolbar title using data passed in the bundle object, if there's any
        arguments?.getString(ARG_KEY_TITLE).let { title -> binding.toolbar.title = title}

        return binding.root
    }

    companion object {
        const val ARG_KEY_TITLE = "toolbar_title"
    }
}