package com.example.sirius.viewmodel.navigation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.sirius.AnimalApplication
import com.example.sirius.model.Animal
import com.example.sirius.data.dao.AnimalDao
import com.example.sirius.model.TypeAnimal
import kotlinx.coroutines.flow.Flow

class AnimalViewModel(private val animalDao: AnimalDao) : ViewModel() {
    fun getAllAnimals(): Flow<List<Animal>> = animalDao.getAllAnimals()

    fun getAge(): Flow<List<Int>> = animalDao.getAge()
    fun getBreed(): Flow<List<String>> = animalDao.getBreed()
    fun getTypeAnimal(): Flow<List<String>> = animalDao.getTypeAnimal()

    fun getAnimalsByAgeASC(option: Int): Flow<List<Animal>> = animalDao.getAnimalsByAgeASC(option)
    fun getAnimalsByBreed(option: String): Flow<List<Animal>> = animalDao.getAnimalsByBreed(option)

    fun getAnimalsByTypeAnimal(option: String): Flow<List<Animal>> {
        val typeAnimal = TypeAnimal.valueOf(option)
        return animalDao.getAnimalsByTypeAnimal(typeAnimal)
    }

    fun getAnimalsInFosterCare(): Flow<List<Animal>> = animalDao.getAnimalsInFosterCare(1)
    fun getAnimalsForAdoption(): Flow<List<Animal>> = animalDao.getAnimalsForAdoption(1)
    fun getAnimalsInPreAdoption(): Flow<List<Animal>> = animalDao.getAnimalsForAdoption(0)


    companion object {
        val factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as AnimalApplication)
                AnimalViewModel(application.database.animalDao())
            }
        }
    }
}