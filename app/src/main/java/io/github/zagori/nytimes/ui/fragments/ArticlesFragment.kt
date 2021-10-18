package io.github.zagori.nytimes.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
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
                viewModel.getLocalMostPopular(listType)
            }
            ListType.Shared.name -> {
                binding.toolbar.title = getString(R.string.fragment_articles_title_shared)
                setupPopularObserver()
                viewModel.getLocalMostPopular(listType)
            }
            ListType.Emailed.name -> {
                binding.toolbar.title = getString(R.string.fragment_articles_title_emailed)
                setupPopularObserver()
                viewModel.getLocalMostPopular(listType)
            }
            ListType.Search.name -> {
                binding.toolbar.title =
                    getString(R.string.fragment_articles_title_search, viewModel.query)
                setupSearchObserver()
                addScrollListener()
                viewModel.getLocalSearch()
            }
        }

        return binding.root
    }

    private fun setupPopularObserver() {
        viewModel.articlesLiveData
            .observe(viewLifecycleOwner) { state ->
                if (state is State.Success) articlesAdapter.articles = state.data
            }

        viewModel.articlesRefreshedLiveData.observe(viewLifecycleOwner) { state ->
            when(state){
                is State.Loading -> showLoadingIndicator(true)
                is State.Success -> showLoadingIndicator(false)
                is State.Error -> {
                    showError(state.getMessage())
                    showLoadingIndicator(false)
                }
            }
        }
    }

    private fun setupSearchObserver() {
        viewModel.docsLiveData
            .observe(viewLifecycleOwner) { state ->
                if (state is State.Success) articlesAdapter.docs = state.data
            }

        viewModel.docsRefreshedLiveData.observe(viewLifecycleOwner) { state ->
            when(state){
                is State.Loading -> showLoadingIndicator(true)
                is State.Success -> showLoadingIndicator(false)
                is State.Error -> {
                    showError(state.getMessage())
                    showLoadingIndicator(false)
                }
            }
        }
    }

    /**
     * Add scroll Listener to detect when recycler-view reaches bottom.
     * This listener to be set up only for Search listType
     */
    private fun addScrollListener() =
        binding.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

                // Trigger load more when recycler reaches bottom. direction 1 refers to scrolling down
                if (!binding.recyclerView.canScrollVertically(1)
                    && newState == RecyclerView.SCROLL_STATE_IDLE) {
                    viewModel.loadAndSaveSearched(viewModel.query)
                }
            }
        })

    private fun showLoadingIndicator(show: Boolean) {
        binding.progressIndicator.isVisible = show
    }

    private fun showError(message: String) {
        Snackbar.make(binding.root, message, Snackbar.LENGTH_SHORT).show()
    }

    companion object {
        const val ARG_KEY_TYPE = "article_list_type"
    }
}