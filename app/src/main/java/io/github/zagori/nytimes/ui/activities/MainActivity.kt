package io.github.zagori.nytimes.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import io.github.zagori.nytimes.databinding.ActivityMainBinding
import io.github.zagori.nytimes.viewmodels.ArticlesViewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: ArticlesViewModel by viewModels()
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        viewModel.preLoadMostPopular()
    }
}