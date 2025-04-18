package com.permana.pokemonapp.feature.home

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.permana.pokemonapp.feature.pokemon.PokemonFragment
import com.permana.pokemonapp.feature.profile.ProfileFragment

class HomePagerAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> PokemonFragment()
            1 -> ProfileFragment()
            else -> throw IllegalArgumentException("Invalid tab position")
        }
    }
}
