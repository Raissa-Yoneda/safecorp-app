package br.com.safecorp.data.repository

import br.com.safecorp.data.api.SelfCheckApi
import br.com.safecorp.data.api.SelfCheckRequest
import br.com.safecorp.data.dao.SelfCheckDao
import br.com.safecorp.data.model.SelfCheck
import kotlinx.coroutines.flow.Flow
import java.util.Date

class SelfCheckRepository(
    private val selfCheckDao: SelfCheckDao,
    private val selfCheckApi: SelfCheckApi
) {
    val allSelfChecks: Flow<List<SelfCheck>> = selfCheckDao.getAllSelfChecks()

    suspend fun addSelfCheck(mood: String, note: String?): String {
        val selfCheck = SelfCheck(
            date = Date(),
            mood = mood,
            note = note
        )
        
        try {
            // Tenta submeter para a API
            val response = selfCheckApi.submitSelfCheck(SelfCheckRequest(mood, note))
            if (response.success) {
                selfCheckDao.insertSelfCheck(selfCheck)
                return "Check-in salvo com sucesso!"
            } else {
                // If API fails but returns a response, save locally anyway
                selfCheckDao.insertSelfCheck(selfCheck)
                return "Check-in salvo localmente. ${response.message}"
            }
        } catch (e: Exception) {
            // Se a chamada para a API falhar, ele salva localmente
            selfCheckDao.insertSelfCheck(selfCheck)
            return "Check-in salvo localmente. Não foi possível sincronizar com o servidor."
        }
    }
} 