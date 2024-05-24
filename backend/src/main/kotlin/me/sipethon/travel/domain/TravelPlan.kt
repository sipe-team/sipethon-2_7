package me.sipethon.travel.domain

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import jakarta.persistence.AttributeConverter
import jakarta.persistence.Column
import jakarta.persistence.Convert
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne

@Entity
class TravelPlan(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Convert(converter = JsonConverter::class)
    @Column(columnDefinition = "TEXT")
    var plan: Map<String, Any> = emptyMap(),

    @Column(name = "user_id")
    val userId: Long = 0,

    @Column(name = "is_bookmarked")
    var isBookmarked: Boolean = false
)

class JsonConverter : AttributeConverter<Map<String, Any>, String> {

    private val objectMapper = jacksonObjectMapper()

    override fun convertToDatabaseColumn(attribute: Map<String, Any>?): String {
        return objectMapper.writeValueAsString(attribute)
    }

    override fun convertToEntityAttribute(dbData: String?): Map<String, Any> {
        return objectMapper.readValue(dbData ?: "")
    }
}
