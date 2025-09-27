package br.com.safecorp.data.api

class MockAssessmentApi : AssessmentApi {
    override suspend fun submitAssessment(request: AssessmentRequest): AssessmentResponse {
        // Simulate network delay
        kotlinx.coroutines.delay(1000)
        
        val totalScore = request.answers.sum()
        val suggestion = when {
            totalScore <= 10 -> "Consider discussing your work situation with a supervisor or HR. You might benefit from additional support or resources."
            totalScore <= 15 -> "Your responses indicate some areas of concern. Consider implementing stress management techniques and seeking support when needed."
            totalScore <= 20 -> "You're managing well overall, but there might be some areas where additional support could be beneficial."
            else -> "Your responses indicate good overall well-being. Continue maintaining healthy work habits and supporting your colleagues."
        }
        
        return AssessmentResponse(
            score = totalScore,
            suggestion = suggestion
        )
    }
} 