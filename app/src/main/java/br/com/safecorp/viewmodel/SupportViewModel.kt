package br.com.safecorp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import br.com.safecorp.data.api.SupportApi
import br.com.safecorp.data.model.SupportResource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SupportViewModel(
    private val supportApi: SupportApi
) : ViewModel() {
    private val _supportResources = MutableStateFlow<List<SupportResource>>(emptyList())
    val supportResources: StateFlow<List<SupportResource>> = _supportResources

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    init {
        loadSupportResources()
    }

    private fun loadSupportResources() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val resources = supportApi.getSupportResources()
                _supportResources.value = resources
            } catch (e: Exception) {
                // Lida com o erro
            } finally {
                _isLoading.value = false
            }
        }
    }

    class Factory(private val supportApi: SupportApi) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(SupportViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return SupportViewModel(supportApi) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
} 