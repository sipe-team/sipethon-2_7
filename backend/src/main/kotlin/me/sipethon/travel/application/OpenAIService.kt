package me.sipethon.travel.application

import kotlinx.serialization.json.Json
import me.sipethon.travel.domain.Plan
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient


@Service
class OpenAIService(
    @Value("\${openai.api.key}") private val openaiApiKey: String,
) {

    private val webClient: WebClient = WebClient.builder()
        .baseUrl("https://api.openai.com/v1")
        .defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer $openaiApiKey")
        .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
        .build()

    /**
     * Generate travel plan
     * API : https://platform.openai.com/docs/guides/text-generation/chat-completions-api?lang=curl
     * If 429 Error : https://stackoverflow.com/questions/75041580/openai-api-giving-error-429-too-many-requests
     *
     * @param prompt message for request body to OpenAI
     * @return OpenAI Response
     */
    fun generateTravelPlan(prompt: String): Plan {
        val requestBody = mapOf(
            "model" to "gpt-4o",
            "messages" to listOf(
                mapOf("role" to "system", "content" to "You are a helpful assistant."),
                mapOf("role" to "user", "content" to prompt)
            )
        )
        return try {
            val response = webClient.post()
                .uri("/chat/completions")
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(Map::class.java)
                .block()

            val choices = response?.get("choices") as List<Map<String, Any>>
            val assistantMessage = choices.first { (it["message"] as Map<String, Any>)["role"] == "assistant" }
            val messageJson = (assistantMessage["message"] as Map<String, Any>)["content"] as String
            Json.decodeFromString<Plan>(messageJson)
        } catch (e: Exception) {
            Plan.dummy()
        }
    }
}
