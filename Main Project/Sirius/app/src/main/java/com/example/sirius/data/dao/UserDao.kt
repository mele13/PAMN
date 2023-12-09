package com.example.sirius.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.sirius.model.Animal
import com.example.sirius.model.User
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Insert
    suspend fun insertUser(user: User)

    @Query("SELECT * FROM User WHERE id = :userId")
    suspend fun getUserById(userId: Int): User?

    @Query("SELECT * FROM User WHERE username = :username")
    suspend fun getUserByUsername(username: String): User?

    @Query("SELECT * FROM User WHERE email = :email")
    suspend fun getUserByEmail(email: String): User?

    @Query("SELECT * FROM User WHERE username = :username AND password = :password")
    suspend fun getUserByCredentials(username: String, password: String): User?

    @Query("DELETE FROM User")
    suspend fun deleteAllUsers()

    @Query("SELECT animal.* FROM Animal INNER JOIN LikedAnimal ON animal.id = LikedAnimal.animal_id WHERE LikedAnimal.user_id = :userId")
    fun getLikedAnimals(userId: Int): Flow<List<Animal>>
}
