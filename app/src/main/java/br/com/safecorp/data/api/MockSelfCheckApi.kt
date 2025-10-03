package br.com.safecorp.data.api

class MockSelfCheckApi : SelfCheckApi {
    override suspend fun submitSelfCheck(request: SelfCheckRequest): SelfCheckResponse {
        // Simula network delay
        kotlinx.coroutines.delay(1000)
        
        // Gera um random de success message baseada no mood
        val message = when (request.mood) {
            "😊" -> "Great to see you're feeling happy! Keep spreading that positivity!"
            "😐" -> "It's okay to feel neutral. Remember that emotions are temporary."
            "😢" -> "It's okay to feel sad. Would you like to talk about it?"
            "😡" -> "Feeling angry is natural. Try some deep breathing exercises."
            "😴" -> "Make sure you're getting enough rest and taking care of yourself."
            "😰" -> "Anxiety can be challenging. Remember to take things one step at a time."
            "😎" -> "You're rocking it! Keep that confidence going!"
            else -> "Thanks for sharing how you're feeling."
        }
        
        return SelfCheckResponse(
            success = true,
            message = message
        )
    }
} 