package com.javlonrahimov1212.rickandmorty.ui.characters

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.javlonrahimov1212.rickandmorty.adapters.CharacterAdapter
import com.javlonrahimov1212.rickandmorty.adapters.OnItemClicked
import com.javlonrahimov1212.rickandmorty.adapters.OnReachedEnd
import com.javlonrahimov1212.rickandmorty.database.AppDatabase
import com.javlonrahimov1212.rickandmorty.databinding.FragmentCharactersBinding
import com.javlonrahimov1212.rickandmorty.network.ApiHelper
import com.javlonrahimov1212.rickandmorty.network.RetrofitBuilder
import org.koin.androidx.viewmodel.ext.android.viewModel


class CharactersFragment : Fragment() {

    private val viewModel: CharactersViewModel by viewModel()
    private lateinit var adapter: CharacterAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentCharactersBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        adapter = CharacterAdapter()

        binding.recycler.adapter = adapter

        adapter.onItemClicked = object : OnItemClicked {
            override fun onClick() {
                val browserIntent =
                    Intent(Intent.ACTION_VIEW, Uri.parse("https://rickandmortyapi.com/"))
                startActivity(browserIntent)
            }
        }

        adapter.onReachedEnd = object : OnReachedEnd {
            override fun onReachedEnd() {
                viewModel.fetchNextPages(++viewModel.currentPage)
            }
        }

        viewModel.isLoading.observe(viewLifecycleOwner, { isLoading ->
            if (isLoading) {
                binding.progressIndicator.visibility = View.VISIBLE
            } else {
                binding.progressIndicator.visibility = View.GONE
            }
        })

        viewModel.characters.observe(viewLifecycleOwner,
            { value ->
                if (value != null) {
                    adapter.setCharacters(value)
                }
            })

        return binding.root
    }
}