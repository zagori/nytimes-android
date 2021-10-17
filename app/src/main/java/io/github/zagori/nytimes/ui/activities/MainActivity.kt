package io.github.zagori.nytimes.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import io.github.zagori.nytimes.databinding.ActivityMainBinding
import io.github.zagori.nytimes.models.State
import io.github.zagori.nytimes.viewmodels.ArticlesViewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: ArticlesViewModel by viewModels()
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        viewModel.articlesRefreshedLiveData.observe(this){
            when(it){
                is State.Loading -> Log.d(this::class.java.name, "+++> ooo: loading")
                is State.Success -> Log.d(this::class.java.name, "+++> ooo: success - ${it.data}")
                is State.Error -> Log.e(this::class.java.name, "+++> ooo: error - ${it.getMessage()}")
            }
        }

        viewModel.preLoadMostPopular()
    }
}