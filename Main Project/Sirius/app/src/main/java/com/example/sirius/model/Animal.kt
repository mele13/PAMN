package com.example.sirius.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Animal")
data class Animal (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int,
    @NonNull
    @ColumnInfo(name = "name")
    val nameAnimal: String,
    @NonNull
    @ColumnInfo(name = "age")
    val ageAnimal: Int,
    @NonNull
    @ColumnInfo(name = "sex")
    val sexAnimal: String,
    @NonNull
    @ColumnInfo(name = "waiting_adoption")
    val waitingAdoption: Int, // 0 -> preAdoption | 1 -> adoption
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
    val typeAnimal: TypeAnimal,
    @NonNull
    @ColumnInfo(name = "time_shelter")
    val timeShelter: Int,
    @NonNull
    @ColumnInfo(name = "foster_care")
    val fosterCare: Int, // 0 -> no foster care | 1 -> in foster care
    //@NonNull
    //@ColumnInfo(name = "medical_record")
    //val medicalRecordAnimal: String,
    //@NonNull
    //@ColumnInfo(name = "image")
    //val imageAnimal: String,
)