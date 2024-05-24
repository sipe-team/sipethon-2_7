package me.sipethon.travel.application

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
    fun generateTravelPlan(prompt: String): Map<String, Any> {
        val requestBody = mapOf(
            "model" to "gpt-3.5-turbo",
            "messages" to listOf(
                mapOf("role" to "system", "content" to "You are a helpful assistant."),
                mapOf("role" to "user", "content" to prompt)
            )
        )
        val response = webClient.post()
            .uri("/chat/completions")
            .bodyValue(requestBody)
            .retrieve()
            .bodyToMono(Map::class.java)
            .block()

        val choices = response?.get("choices") as List<Map<String, Any>>
        val assistantMessage = choices.first { (it["message"] as Map<String, Any>)["role"] == "assistant" }
        // val content = (assistantMessage["message"] as Map<String, Any>)["content"] as String
        return assistantMessage
        // {"index":0,"message":{"role":"assistant","content":"코타키나발루 여행 일정을 아래와 같이 제안해드릴게요.\n\n**1일차**\n- **점심**: 로컬 음식점에서 맛있는 말레이시아 요리를 즐기면서 현지 문화 체험\n- **오후**: 시장이나 쇼핑몰에서 지역 특산품이나 기념품 구매\n- **저녁**: 유명 맛집에서 해산물 요리를 맛보기\n\n**2일차**\n- **아침**: 호텔에서 아침식사 후 코타 키나발루 도심 탐방\n- **점심**: 페난 휴양지에서 해변가 조식\n- **오후**: 코타키나발루 시티 모스크 방문\n- **저녁**: 스카이 배리에 있는 레스토랑에서 석양 감상하며 식사\n\n**3일차**\n- **아침**: 쿤다산 산기슭에서의 아침 산책\n- **점심**: 숲속 레스토랑에서 자연 속에서의 식사\n- **오후**: 코타키나발루 동물원 방문\n- **저녁**: 도시에서 지역 특산품을 이용한 요리\n\n이러한 여행 일정으로 코타키나발루를 즐길 수 있을 것입니다. 각 활동별 예산을 고려하여 여행을 계획하시면 좋을 것 같습니다."},"logprobs":null,"finish_reason":"stop"}
    }
}
