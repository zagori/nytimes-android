package io.github.zagori.nytimes.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import io.github.zagori.nytimes.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}