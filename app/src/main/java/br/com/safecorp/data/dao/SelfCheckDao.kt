package br.com.safecorp.data.dao

import androidx.room.*
import br.com.safecorp.data.model.SelfCheck
import kotlinx.coroutines.flow.Flow

@Dao
interface SelfCheckDao {
    @Query("SELECT * FROM self_checks ORDER BY date DESC")
    fun getAllSelfChecks(): Flow<List<SelfCheck>>

    @Insert
    suspend fun insertSelfCheck(selfCheck: SelfCheck)

    @Delete
    suspend fun deleteSelfCheck(selfCheck: SelfCheck)
} 