package com.example.sirius

import android.app.Application
import com.example.sirius.data.SiriusDatabase

class AnimalApplication: Application() {
    val database: SiriusDatabase by lazy { SiriusDatabase.getDatabase(this) }
}