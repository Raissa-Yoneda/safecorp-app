package br.com.safecorp.data.repository

import br.com.safecorp.data.api.AssessmentApi
import br.com.safecorp.data.api.AssessmentRequest
import br.com.safecorp.data.dao.AssessmentDao
import br.com.safecorp.data.model.Assessment
import kotlinx.coroutines.flow.Flow
import java.util.Date

class AssessmentRepository(
    private val assessmentDao: AssessmentDao,
    private val assessmentApi: AssessmentApi
) {
    val allAssessments: Flow<List<Assessment>> = assessmentDao.getAllAssessments()

    suspend fun submitAssessment(answers: List<Int>): Assessment {
        val response = assessmentApi.submitAssessment(AssessmentRequest(answers))
        val assessment = Assessment(
            date = Date(),
            score = response.score,
            suggestion = response.suggestion
        )
        assessmentDao.insertAssessment(assessment)
        return assessment
    }
} 