package br.com.safecorp.data.dao

import androidx.room.*
import br.com.safecorp.data.model.Assessment
import kotlinx.coroutines.flow.Flow

@Dao
interface AssessmentDao {
    @Query("SELECT * FROM assessments ORDER BY date DESC")
    fun getAllAssessments(): Flow<List<Assessment>>

    @Insert
    suspend fun insertAssessment(assessment: Assessment)

    @Delete
    suspend fun deleteAssessment(assessment: Assessment)
} 