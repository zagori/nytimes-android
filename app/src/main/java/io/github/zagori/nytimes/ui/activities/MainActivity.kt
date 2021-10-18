package io.github.zagori.nytimes.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.google.android.material.snackbar.Snackbar
import io.github.zagori.nytimes.databinding.ActivityMainBinding
import io.github.zagori.nytimes.models.State
import io.github.zagori.nytimes.viewmodels.ArticlesViewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: ArticlesViewModel by viewModels()
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        viewModel.articlesRefreshedLiveData.observe(this) {
            if (it is State.Error) showError(it.getMessage())
        }

        viewModel.docsRefreshedLiveData.observe(this) {
            if (it is State.Error) showError(it.getMessage())
        }

        viewModel.preLoadMostPopular()
    }

    private fun showError(message: String) {
        Snackbar.make(binding.root, message, Snackbar.LENGTH_SHORT).show()
    }
}