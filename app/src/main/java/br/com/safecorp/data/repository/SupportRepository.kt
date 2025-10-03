package br.com.safecorp.data.repository

import br.com.safecorp.data.api.SupportApi
import br.com.safecorp.data.model.SupportResource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SupportRepository(private val supportApi: SupportApi) {
    fun getSupportResources(): Flow<List<SupportResource>> = flow {
        try {
            val resources = supportApi.getSupportResources()
            emit(resources)
        } catch (e: Exception) {
            emit(emptyList())
        }
    }
}
