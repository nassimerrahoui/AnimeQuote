package com.belt.animequote.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.animequote.R
import com.example.animequote.databinding.FragmentHomeBinding
import com.belt.animequote.domain.entity.AnimeTitle
import com.belt.animequote.ui.adapter.AnimeTitleAdapter
import com.belt.animequote.ui.decorator.AnimeTitleItemDecorator
import com.belt.animequote.ui.viewmodel.AvailableAnimeViewModel
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
            layoutManager = LinearLayoutManager(context)
            this.addItemDecoration(AnimeTitleItemDecorator(resources.getDimensionPixelSize(R.dimen.card_margin)))
        }
        availableAnimeViewModel.loadAvailableAnime()
        availableAnimeViewModel.availableAnime.observe(viewLifecycleOwner, onNewAnimeTitle)
    }

    private val onNewAnimeTitle = Observer<List<AnimeTitle>>{
        animeTitleAdapter.submitList(it)
    }
}