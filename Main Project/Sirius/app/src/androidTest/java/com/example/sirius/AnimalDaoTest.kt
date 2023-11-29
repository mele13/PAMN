package com.example.sirius
import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.sirius.data.SiriusDatabase
import com.example.sirius.data.dao.AnimalDao
import com.example.sirius.model.Animal
import com.example.sirius.model.TypeAnimal
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class AnimalDaoTest {
    private lateinit var animalDao: AnimalDao
    private lateinit var siriusDatabase: SiriusDatabase

    //name, birt_name(yyyy-mm-dd), sex, waiting_adoption, foster_care, short_info, long_info, breed, type_animal, entry_date, photo_animal
    private var animal1 = Animal(1, "Thor", "2023-11-27", "M", 0, 0, "a", "a","a", TypeAnimal.DOG, "2023-11-27", "res/drawable/goldenretriever1.jpg",  )
    private var animal2 = Animal(2, "Thor", "2023-11-27", "M", 0, 0, "a", "a","a", TypeAnimal.DOG, "2023-11-27", "res/drawable/goldenretriever1.jpg",  )

    @Before
    fun createDb() {
        val context: Context = ApplicationProvider.getApplicationContext()
        siriusDatabase = Room.inMemoryDatabaseBuilder(context, SiriusDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        animalDao = siriusDatabase.animalDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        siriusDatabase.close()
    }

    private suspend fun addOneItemToDb() {
        animalDao.insertAnimal(animal1)
    }

    private suspend fun addTwoItemsToDb() {
        animalDao.insertAnimal(animal1)
        animalDao.insertAnimal(animal2)
    }

    @Test
    @Throws(Exception::class)
    fun daoInsert_insertsItemIntoDB() = runBlocking {
        addOneItemToDb()
        val allItems = animalDao.getAllAnimals().first()
        assertEquals(allItems[0], animal1)
    }

    @Test
    @Throws(Exception::class)
    fun daoGetAllItems_returnsAllItemsFromDB() = runBlocking {
        addTwoItemsToDb()
        val allItems = animalDao.getAllAnimals().first()
        assertEquals(allItems[0], animal1)
        assertEquals(allItems[1], animal2)
    }
}