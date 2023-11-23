package com.example.sirius.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel

class ContactsViewModel : ViewModel() {
    var backgroundColor by mutableStateOf(Color.White)
        private set

    fun changeBackgroundColor() {
        Log.d("ContactsViewModel", "changeBackgroundColor() called")
        backgroundColor = Color.Red
        Log.d("ContactsViewModel", "backgroundColor: $backgroundColor")
    }
}