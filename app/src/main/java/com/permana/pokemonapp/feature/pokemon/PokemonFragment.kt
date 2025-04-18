package com.permana.pokemonapp.feature.pokemon

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.permana.pokemonapp.databinding.FragmentPokemonBinding
import com.permana.pokemonapp.feature.detail.PokemonDetailActivity
import com.permana.pokemonapp.feature.home.HomeViewModel
import com.permana.pokemonapp.feature.pokemon.adapter.PokemonAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PokemonFragment : Fragment() {

    private var _binding: FragmentPokemonBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HomeViewModel by viewModels()
    private val adapter by lazy {
        PokemonAdapter(
            onItemClick = { selectedPokemon ->
                val intent = Intent(requireContext(), PokemonDetailActivity::class.java).apply {
                    putExtra("pokemon_name", selectedPokemon.name)
                    putExtra("pokemon_abilities", selectedPokemon.abilities)
                }
                startActivity(intent)
            }
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPokemonBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        observePokemon()
        viewModel.refreshPokemons()
    }


    private fun setupRecyclerView() {
        binding.rvPokemon.layoutManager = LinearLayoutManager(requireContext())
        binding.rvPokemon.adapter = adapter
    }

    private fun observePokemon() {
        lifecycleScope.launch {
            viewModel.pokemonListState.collectLatest { list ->
                if (list.isEmpty()) {
                    binding.loadingIndicator.visibility = View.VISIBLE
                } else {
                    binding.loadingIndicator.visibility = View.GONE
                    adapter.submitList(list)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}