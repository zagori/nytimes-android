package io.github.zagori.nytimes.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import io.github.zagori.nytimes.R
import io.github.zagori.nytimes.databinding.FragmentArticlesBinding
import io.github.zagori.nytimes.models.ListType
import io.github.zagori.nytimes.models.State
import io.github.zagori.nytimes.ui.adapters.ArticlesAdapter
import io.github.zagori.nytimes.viewmodels.ArticlesViewModel

class ArticlesFragment : Fragment() {

    private lateinit var binding: FragmentArticlesBinding
    private val viewModel: ArticlesViewModel by activityViewModels()
    private val articlesAdapter by lazy { ArticlesAdapter() }
    private val listType by lazy { arguments?.getString(ARG_KEY_TYPE) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentArticlesBinding.inflate(inflater, container, false)

        // Set the navigation action
        binding.toolbar.setNavigationOnClickListener { requireActivity().onBackPressed() }

        // Set up the recyclerview adapter
        binding.recyclerView.adapter = articlesAdapter

        // Set the toolbar title and set up the observers
        when (listType) {
            ListType.Viewed.name -> {
                binding.toolbar.title = getString(R.string.fragment_articles_title_viewed)
                setupPopularObserver()
                viewModel.getLocalMostViewed(listType)
            }
            ListType.Shared.name -> {
                binding.toolbar.title = getString(R.string.fragment_articles_title_shared)
                setupPopularObserver()
                viewModel.getLocalMostViewed(listType)
            }
            ListType.Emailed.name -> {
                binding.toolbar.title = getString(R.string.fragment_articles_title_emailed)
                setupPopularObserver()
                viewModel.getLocalMostViewed(listType)
            }
        }

        return binding.root
    }

    private fun setupPopularObserver() = viewModel.articlesLiveData
        .observe(viewLifecycleOwner) { state ->
            when (state) {
                is State.Loading -> {
                    Log.d(this::class.java.name, "+++> Popular: Loading...")
                }
                is State.Success -> articlesAdapter.articles = state.data

                is State.Error -> {
                    Log.e(this::class.java.name, "+++> Popular: Error - ${state.getMessage()}")
                }
            }
        }

    companion object {
        const val ARG_KEY_TYPE = "article_list_type"
    }
}