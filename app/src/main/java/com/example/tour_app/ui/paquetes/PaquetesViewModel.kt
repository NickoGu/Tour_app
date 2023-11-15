package com.example.tour_app.ui.paquetes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PaquetesViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Fragment de paquetes"
    }
    val text: LiveData<String> = _text
}