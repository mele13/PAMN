package com.example.sirius

import android.app.Application
import com.example.sirius.model.AnimalDatabase

class AnimalApplication: Application() {
    val database: AnimalDatabase by lazy { AnimalDatabase.getDatabase(this) }
}