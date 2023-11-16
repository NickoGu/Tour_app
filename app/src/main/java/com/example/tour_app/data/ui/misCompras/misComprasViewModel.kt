package com.example.tour_app.ui.misCompras

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class misComprasViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Fragment de mis compras"
    }
    val text: LiveData<String> = _text
}