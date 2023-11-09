package com.example.sirius.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "News")
data class News (
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "id")
        val id: Int,
        @NonNull
        @ColumnInfo(name = "title")
        val titleNews: String,
        @NonNull
        @ColumnInfo(name = "short_info")
        val shortInfoNews: String,
        @NonNull
        @ColumnInfo(name = "long_info")
        val longInfoNews: String,
    // Date, until_date?,

    //@NonNull
    //@ColumnInfo(name = "medical_record")
    //val medicalRecordAnimal: String,
    //@NonNull
    //@ColumnInfo(name = "image")
    //val imageAnimal: String,
)