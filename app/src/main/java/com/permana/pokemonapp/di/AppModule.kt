package com.permana.pokemonapp.di

import PokemonRepositoryImpl
import android.app.Application
import androidx.room.Room
import com.permana.pokemonapp.data.local.PokemonDao
import com.permana.pokemonapp.data.local.PokemonDatabase
import com.permana.pokemonapp.data.local.UserDao
import com.permana.pokemonapp.data.remote.PokeApiService
import com.permana.pokemonapp.data.repository.AuthRepositoryImpl
import com.permana.pokemonapp.domain.repository.AuthRepository
import com.permana.pokemonapp.domain.repository.PokemonRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideApi(): PokeApiService = Retrofit.Builder()
        .baseUrl("https://pokeapi.co/api/v2/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(PokeApiService::class.java)

    @Provides
    @Singleton
    fun provideDatabase(app: Application): PokemonDatabase = Room.databaseBuilder(
        app,
        PokemonDatabase::class.java,
        "pokemon_db"
    ).build()

    @Provides
    fun provideDao(db: PokemonDatabase): PokemonDao = db.pokemonDao()

    @Provides
    fun provideUserDao(pokemonDatabase: PokemonDatabase): UserDao =
        pokemonDatabase.userDao()

    @Provides
    @Singleton
    fun provideRepository(
        api: PokeApiService,
        dao: PokemonDao
    ): PokemonRepository = PokemonRepositoryImpl(api, dao)

    @Provides
    @Singleton
    fun provideAuthRepository(
        userDao: UserDao
    ): AuthRepository = AuthRepositoryImpl(userDao)
}
