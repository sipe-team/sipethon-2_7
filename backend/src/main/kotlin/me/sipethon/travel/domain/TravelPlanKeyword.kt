package me.sipethon.travel.domain

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
class TravelPlanKeyword(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(name = "travel_plan_id")
    val travelPlanId: Long,

    @Column(name = "keyword")
    @Enumerated(EnumType.STRING)
    val keyword: Keyword
)
