package com.example.animequote.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.animequote.databinding.FragmentHomeBinding
import com.example.animequote.domain.quote.entity.AnimeTitle
import com.example.animequote.ui.adapter.AnimeTitleAdapter
import com.example.animequote.ui.viewmodel.AvailableAnimeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private val availableAnimeViewModel: AvailableAnimeViewModel by viewModels()
    private val animeTitleAdapter: AnimeTitleAdapter by lazy { AnimeTitleAdapter() }
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.animeTitleRecyclerView.apply {
            adapter = animeTitleAdapter
        }
        availableAnimeViewModel.loadAvailableAnime()
        availableAnimeViewModel.availableAnime.observe(viewLifecycleOwner, onNewAnimeTitle)
    }

    private val onNewAnimeTitle = Observer<List<AnimeTitle>>{
        animeTitleAdapter.submitList(it)
    }
}