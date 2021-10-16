package io.github.zagori.nytimes.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import io.github.zagori.nytimes.R
import io.github.zagori.nytimes.viewmodels.ArticlesViewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: ArticlesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.getRemoteMostViewed()
    }
}