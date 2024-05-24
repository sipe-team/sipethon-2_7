package me.sipethon.travel.application

import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient

@Service
class ImageSearchService(
    @Value("\${google-image-search.api.key}") private val apiKey: String,
) {

    private val webClient: WebClient = WebClient.builder()
        .baseUrl("https://google.serper.dev")
        .defaultHeader("X-API-KEY", apiKey)
        .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
        .build()


    fun search(queries: List<String>): Map<String, String> {
        val searchImages = this.searchImagesWithQueries(queries)

        return queries.zip(searchImages).toMap()
    }

    private fun searchImagesWithQueries(queries: List<String>): List<String> {
        val requestBody = buildRequestBody(queries)
        return try {
            val response = performSearchRequest(requestBody)
            extractImageUrls(response)
        } catch (e: Exception) {
            emptyList()
        }
    }

    private fun buildRequestBody(queries: List<String>): List<Map<String, String>> {
        return queries.map { mapOf("q" to it, "gl" to "kr", "hl" to "ko") }
    }

    private fun performSearchRequest(requestBody: List<Map<String, String>>): List<*>? {
        return webClient.post()
            .uri("/images")
            .bodyValue(requestBody)
            .retrieve()
            .bodyToMono(List::class.java)
            .block()
    }

    private fun extractImageUrls(response: List<*>?): List<String> {
        return response?.mapNotNull { extractFirstImageUrl(it!!) } ?: emptyList()
    }

    private fun extractFirstImageUrl(item: Any): String? {
        return try {
            val images = (item as Map<String, Any>)["images"] as List<*>
            val firstImage = images.random() as Map<String, Any>
            firstImage["imageUrl"] as String
        } catch (e: Exception) {
            null
        }
    }
}
