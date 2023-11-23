package com.example.sirius.model

import android.annotation.SuppressLint
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

@Entity(tableName = "Animal")
data class Animal (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int,
    @NonNull
    @ColumnInfo(name = "name")
    val nameAnimal: String,
    @NonNull
    @ColumnInfo(name = "birth_date")
    val birthDate: String,
    @NonNull
    @ColumnInfo(name = "sex")
    val sexAnimal: String,
    @NonNull
    @ColumnInfo(name = "waiting_adoption")
    val waitingAdoption: Int, // 0 -> preAdoption | 1 -> adoption
    @NonNull
    @ColumnInfo(name = "foster_care")
    val fosterCare: Int, // 0 -> no foster care | 1 -> in foster care
    @NonNull
    @ColumnInfo(name = "short_info")
    val shortInfoAnimal: String,
    @NonNull
    @ColumnInfo(name = "long_info")
    val longInfoAnimal: String,
    @NonNull
    @ColumnInfo(name = "breed")
    val breedAnimal: String,
    @NonNull
    @ColumnInfo(name = "type_animal")
    // Enumerado
    val typeAnimal: TypeAnimal,
    @NonNull
    @ColumnInfo(name = "entry_date")
    val entryDate: String,
    @NonNull
    @ColumnInfo(name = "photo_animal")
    val photoAnimal: String
) {
    @SuppressLint("NewApi")
    fun getDaysSinceShelter(): Long {
        val dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val shelterDate = LocalDate.parse(entryDate.toString(), dateFormatter)
        val currentDate = LocalDate.now()
        return ChronoUnit.DAYS.between(shelterDate, currentDate)
    }

    @SuppressLint("NewApi")
    fun getDaysSinceShelter2(): Long {
        val dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val shelterDate = LocalDate.parse(birthDate.toString(), dateFormatter)
        val currentDate = LocalDate.now()
        return ChronoUnit.DAYS.between(shelterDate, currentDate)
    }
}