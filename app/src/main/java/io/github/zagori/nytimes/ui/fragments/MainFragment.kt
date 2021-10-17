package io.github.zagori.nytimes.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.github.zagori.nytimes.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)

        binding.itemSearch.setOnClickListener {  }
        binding.itemMostViewed.setOnClickListener {  }
        binding.itemMostShared.setOnClickListener {  }
        binding.itemMostEmailed.setOnClickListener {  }

         return binding.root
    }
}