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
    @ColumnInfo(name = "state")
    val stateAnimal: String,
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
    //Enumerado
    val typeAnimal: String,
    @NonNull
    @ColumnInfo(name = "time_shelter")
    val timeShelter: Int,
    //@NonNull
    //@ColumnInfo(name = "medical_record")
    //val medicalRecordAnimal: String,
    //@NonNull
    //@ColumnInfo(name = "image")
    //val imageAnimal: String,
)