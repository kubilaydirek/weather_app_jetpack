package com.example.havadurumu.ui.scene.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.havadurumu.data.model.WeatherModel
import com.example.havadurumu.data.repositoryImp.WeatherRepositoryImp
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repo: WeatherRepositoryImp) : ViewModel() {
    private val _data = MutableStateFlow<WeatherModel?>(null)
    val data = _data.asStateFlow()

    init {
        getWeather()
    }

    private fun getWeather() {
        viewModelScope.launch {
            try {
                val result = repo.getWeather(land = "tr", city = "Ankara")
                if (result.code() == 200) {
                    result.body().let {
                        _data.value = it
                    }
                }
            } catch (e: Exception) {
                //Handle Error
            }
        }

    }


}