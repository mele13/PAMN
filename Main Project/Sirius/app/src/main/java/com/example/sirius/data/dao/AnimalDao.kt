package com.example.sirius.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.sirius.model.Animal
import com.example.sirius.model.TypeAnimal
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
    fun getAnimalByTypeAnimal(typeAnimal: TypeAnimal): Flow<List<Animal>>

    @Query("SELECT * FROM Animal WHERE breed = :breed ORDER BY time_shelter DESC")
    fun getAnimalByBreed(breed: String): Flow<List<Animal>>

    @Query("SELECT * FROM Animal WHERE age = :age ORDER BY age ASC")
    fun getAnimalsByAgeASC(age: Int): Flow<List<Animal>>

    @Query("SELECT DISTINCT age FROM Animal")
    fun getAge(): Flow<List<Int>>

    @Query("SELECT * FROM Animal WHERE age = :age ORDER BY age DESC")
    fun getAnimalsByAgeDesc(age: Int): Flow<List<Animal>>

    //Ordenarnos???
    @Query("SELECT * FROM Animal WHERE breed = :breed")
    fun getAnimalsByBreed(breed: String): Flow<List<Animal>>

    @Query("SELECT DISTINCT breed FROM Animal")
    fun getBreed(): Flow<List<String>>

    @Query("SELECT * FROM Animal WHERE type_animal = :typeAnimal")
    fun getAnimalsByTypeAnimal(typeAnimal: TypeAnimal): Flow<List<Animal>>

    @Query("SELECT DISTINCT type_animal FROM Animal")
    fun getTypeAnimal(): Flow<List<String>>

    @Query("SELECT * FROM Animal WHERE waiting_adoption = :isWaitingAdoption ORDER BY time_shelter DESC")
    fun getAnimalsForAdoption(isWaitingAdoption: Int): Flow<List<Animal>>

    @Query("SELECT * FROM Animal WHERE foster_care = :isInFosterCare ORDER BY time_shelter DESC")
    fun getAnimalsInFosterCare(isInFosterCare: Int): Flow<List<Animal>>

}