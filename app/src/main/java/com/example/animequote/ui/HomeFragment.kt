package com.example.animequote.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.example.animequote.R
import com.example.animequote.databinding.FragmentHomeBinding
import com.example.animequote.ui.viewmodel.AvailableAnimeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private val availableAnimeViewModel: AvailableAnimeViewModel by viewModels()
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
        availableAnimeViewModel.loadAvailableAnime()
        view.findViewById<RecyclerView>(R.id.anime_title_recycler_view).apply {
            println("TOTO " + availableAnimeViewModel.availableAnime)
        }
    }
}