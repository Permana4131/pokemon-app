package com.permana.pokemonapp.data.repository

import com.permana.pokemonapp.data.local.UserDao
import com.permana.pokemonapp.data.local.entity.UserEntity
import com.permana.pokemonapp.domain.repository.AuthRepository

class AuthRepositoryImpl(
    private val userDao: UserDao
) : AuthRepository {

    override suspend fun register(username: String, password: String): Boolean {
        val user = userDao.getUserByUsername(username)
        return if (user == null) {
            userDao.insert(UserEntity(username, password))
            true
        } else {
            false
        }
    }

    override suspend fun login(username: String, password: String): Boolean {
        val user = userDao.getUserByUsername(username)
        return user?.password == password
    }
}
