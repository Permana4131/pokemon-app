package com.permana.pokemonapp.feature.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.permana.pokemonapp.databinding.ActivityPokemonDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PokemonDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPokemonDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPokemonDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val name = intent.getStringExtra("pokemon_name") ?: return
        val abilities = intent.getStringExtra("pokemon_abilities") ?: return

        binding.tvPokemonName.text = name
        binding.tvPokemonAbilities.text = abilities
    }
}
