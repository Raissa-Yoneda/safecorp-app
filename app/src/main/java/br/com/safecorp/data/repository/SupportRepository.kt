package br.com.safecorp.data.repository

import br.com.safecorp.data.api.SupportApi
import br.com.safecorp.data.model.SupportResource
import br.com.safecorp.network.RetrofitClient
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SupportRepository(private val supportApi: SupportApi) {

    fun getSupportResources(): Flow<List<SupportResource>> = flow {
        try {
            val resources = supportApi.getSupportResources("Bearer ${RetrofitClient.JWT_TOKEN}")
            emit(resources)
        } catch (e: Exception) {
            emit(emptyList())
        }
    }
}
