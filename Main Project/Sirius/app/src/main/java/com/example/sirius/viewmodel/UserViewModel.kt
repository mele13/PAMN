package com.example.sirius.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.sirius.AnimalApplication
import com.example.sirius.data.dao.UserDao
import com.example.sirius.model.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class UserViewModel(private val userDao: UserDao) : ViewModel() {
    suspend fun login(username: String, password: String): Boolean {
        return suspendCoroutine { continuation ->
            viewModelScope.launch {
                val user = userDao.getUserByCredentials(username, password)
                val success = user != null
                continuation.resume(success)
            }
        }
    }

    fun insertUser(user: User) {
        viewModelScope.launch {
            userDao.insertUser(user)
        }
    }

    suspend fun getUserById(userId: Int): User? {
        return userDao.getUserById(userId)
    }

    suspend fun getUserByUsername(username: String): User? {
        return userDao.getUserByUsername(username)
    }

    suspend fun getUserByEmail(email: String): User? {
        return userDao.getUserByEmail(email)
    }

    suspend fun getUserByCredentials(username: String, password: String): User? {
        return userDao.getUserByCredentials(username, password)
    }

    suspend fun deleteAllUsers() {
        userDao.deleteAllUsers()
    }

    companion object {
        val factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as AnimalApplication)
                UserViewModel(application.database.userDao())
            }
        }
    }
}
