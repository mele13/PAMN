package com.example.sirius.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.sirius.model.Animal
import kotlinx.coroutines.flow.Flow

@Dao
interface AnimalDao {
    @Insert
    suspend fun insertAnimal(animal: Animal)

    @Update
    suspend fun updateAnimal(animal: Animal)

    @Delete
    suspend fun deleteAnimal(animal: Animal)

    @Query("SELECT * from Animal ORDER BY time_shelter DESC")
    fun getAllAnimals(): Flow<List<Animal>>

    @Query("SELECT * FROM Animal WHERE name = :name ORDER BY time_shelter DESC")
    fun getAnimalByName(name: String): Flow<List<Animal>>

    @Query("SELECT * FROM Animal WHERE time_shelter = :timeShelter ORDER BY time_shelter DESC")
    fun getAnimalByTimeShelter(timeShelter: String): Flow<List<Animal>>

    @Query("SELECT * FROM Animal WHERE type_animal = :typeAnimal ORDER BY time_shelter DESC")
    fun getAnimalByTypeAnimal(typeAnimal: String): Flow<List<Animal>>

    @Query("SELECT * FROM Animal WHERE breed = :breed ORDER BY time_shelter DESC")
    fun getAnimalByBreed(breed: String): Flow<List<Animal>>
}