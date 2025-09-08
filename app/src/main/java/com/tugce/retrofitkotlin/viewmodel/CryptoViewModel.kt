package com.tugce.retrofitkotlin.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.tugce.retrofitkotlin.model.Crypto
import com.tugce.retrofitkotlin.network.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class CryptoViewModel(app: Application) : AndroidViewModel(app) {

    private val _cryptos = MutableLiveData<List<Crypto>>()
    val cryptos: LiveData<List<Crypto>> = _cryptos

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error

    init {
        fetchFromInternet()
    }

    fun fetchFromInternet() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val list = RetrofitInstance.api.getCryptos()
                _cryptos.postValue(list)
                _error.postValue(null)
            } catch (e: Exception) {
                _cryptos.postValue(emptyList())
                _error.postValue("Veri alınamadı: ${e.localizedMessage}")
            }
        }
    }
}