package com.permana.pokemonapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.permana.pokemonapp.data.local.entity.PokemonEntity
import com.permana.pokemonapp.data.local.entity.UserEntity

@Database(entities = [PokemonEntity::class, UserEntity::class], version = 1)
abstract class PokemonDatabase : RoomDatabase() {
    abstract fun pokemonDao(): PokemonDao
    abstract fun userDao(): UserDao
}