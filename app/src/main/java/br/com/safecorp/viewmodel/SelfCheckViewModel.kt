package br.com.safecorp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import br.com.safecorp.data.repository.SelfCheckRepository
import br.com.safecorp.data.model.SelfCheck
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SelfCheckViewModel(private val repository: SelfCheckRepository) : ViewModel() {
    private val _selfChecks = MutableStateFlow<List<SelfCheck>>(emptyList())
    val selfChecks: StateFlow<List<SelfCheck>> = _selfChecks.asStateFlow()

    private val _message = MutableStateFlow<String?>(null)
    val message: StateFlow<String?> = _message.asStateFlow()

    init {
        viewModelScope.launch {
            repository.allSelfChecks.collect { checks ->
                _selfChecks.value = checks
            }
        }
    }

    fun addSelfCheck(mood: String, note: String?) {
        viewModelScope.launch {
            try {
                val responseMessage = repository.addSelfCheck(mood, note)
                _message.value = responseMessage
            } catch (e: Exception) {
                _message.value = "Falha ao salvar o check-in. Por favor, tente novamente."
            }
        }
    }

    fun clearMessage() {
        _message.value = null
    }

    class Factory(private val repository: SelfCheckRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(SelfCheckViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return SelfCheckViewModel(repository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
} 