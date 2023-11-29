package com.example.sirius.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
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
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine
import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.lang.Exception

class UserViewModel(private val userDao: UserDao) : ViewModel() {
    var isAuthenticated by mutableStateOf(false)
//        private set

    suspend fun login(username: String, password: String): Boolean {
        return suspendCoroutine { continuation ->
            viewModelScope.launch {
                val user = userDao.getUserByCredentials(username, password)
                val success = user != null
                isAuthenticated = success
//                saveAuthenticationState(success)
                continuation.resume(success)
            }
        }
    }

//    private fun saveAuthenticationState(isAuthenticated: Boolean) {
//        val sharedPreferences = AnimalApplication.context.getSharedPreferences("auth_prefs", Context.MODE_PRIVATE)
//        sharedPreferences.edit().putBoolean("is_authenticated", isAuthenticated).apply()
//    }

    suspend fun registerUser(username: String, email: String, password: String): Boolean {
        println("mele2 ${userDao.getUserByUsername("mele2")}")
        return true
//        return try {
//            val newUser = User(
//                username = username,
//                email = email,
//                password = password,
//                role = "user"
//            )
//            viewModelScope.launch {
//                userDao.insertUser(newUser)
//                isAuthenticated = true
////                saveAuthenticationState(true)
//            }
//            val insertedUser = userDao.getUserByUsername(username)
//            println("inserted user $insertedUser")
//            true
//        } catch (e: Exception) {
//            e.printStackTrace()
//            false
//        }
    }

    suspend fun logout() {
        isAuthenticated = false
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
