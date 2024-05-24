package me.sipethon.travel.domain

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import jakarta.persistence.AttributeConverter
import jakarta.persistence.Column
import jakarta.persistence.Convert
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import java.time.LocalDateTime

@Entity
class TravelPlan(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Convert(converter = JsonConverter::class)
    @Column(columnDefinition = "TEXT")
    var plan: Plan,

    @Column(name = "user_id")
    val userId: Long,

    @Column(name = "thumbnail", columnDefinition = "TEXT")
    val thumbnail: String,

    @Column(name = "is_bookmarked")
    var isBookmarked: Boolean = false,

    @Column(name = "location")
    val location: String,

    @Column(name = "duration")
    val duration: Int,

    @Column(name = "people")
    val people: Int,

    @Column(name = "budget")
    val budget: Int?,

    @Column(name = "group_type")
    @Enumerated(EnumType.STRING)
    val groupType: GroupType?,

    @Column(name = "created_at")
    val createdAt: LocalDateTime = LocalDateTime.now()
)

class JsonConverter : AttributeConverter<Plan, String> {
    private val objectMapper = jacksonObjectMapper()

    override fun convertToDatabaseColumn(attribute: Plan): String {
        return objectMapper.writeValueAsString(attribute)
    }

    override fun convertToEntityAttribute(dbData: String?): Plan {
        return objectMapper.readValue(dbData ?: "")
    }
}
