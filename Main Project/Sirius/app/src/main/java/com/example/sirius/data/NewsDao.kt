package com.example.sirius.data

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.Update
import com.example.sirius.model.News
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsDao {
    @Insert
    suspend fun insertNews(news: News)

    @Update
    suspend fun updateNews(news: News)

    @Delete
    suspend fun deleteNews(news: News)

//    @Query("SELECT * from News ORDER BY date DESC")
//    fun getNews(): Flow<List<News>>

//    @Query("SELECT * FROM News WHERE title = :title ORDER BY date DESC")
//    fun getNewsByTitle(title: String): Flow<List<News>>

//    @Query("SELECT * FROM News WHERE date = :date") // Aquí sería necesario la comparación de dates (<>)
//    fun getNewsByDate(date: Date): Flow<List<News>>
}
