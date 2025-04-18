import com.permana.pokemonapp.data.local.PokemonDao
import com.permana.pokemonapp.data.local.entity.PokemonEntity
import com.permana.pokemonapp.data.remote.PokeApiService
import com.permana.pokemonapp.domain.model.Pokemon
import com.permana.pokemonapp.domain.repository.PokemonRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class PokemonRepositoryImpl(
    private val api: PokeApiService,
    private val dao: PokemonDao
) : PokemonRepository {

    override suspend fun getPokemonList(): Flow<List<Pokemon>> = flow {
        try {
            val cachedPokemons = dao.getAll().map { Pokemon(it.name, it.abilities) }
            if (cachedPokemons.isNotEmpty()) {
                emit(cachedPokemons)
            } else {
                val result = api.getPokemonList().results
                val pokemons = result.map { nameDto ->
                    val detail = api.getPokemonDetail(nameDto.name)
                    val abilities = detail.abilities.joinToString(", ") { it.ability.name }
                    val entity = PokemonEntity(name = detail.name, abilities = abilities)
                    dao.insert(entity)
                    Pokemon(name = detail.name, abilities = abilities)
                }
                emit(pokemons)
            }
        } catch (e: Exception) {
            val cachedPokemons = dao.getAll().map { Pokemon(it.name, it.abilities) }
            emit(cachedPokemons)
        }
    }
}
