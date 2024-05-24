package me.sipethon.travel.domain

import kotlinx.serialization.Serializable

@Serializable
data class Plan(
    val title: String,
    val thumbnail: String,
    val budget: Budget,
    val travelPlan: List<PlanDetail>
) {
    companion object {
        fun dummy() = Plan(
            title = "제목",
            thumbnail = "https://upload.wikimedia.org/wikipedia/commons/thumb/f/f9/Marina_Bay_Sands_in_the_evening_-_20101120.jpg/1200px-Marina_Bay_Sands_in_the_evening_-_20101120.jpg",
            budget = Budget(
                accommodationMain = "중저가 호텔추천",
                accommodationSub = "1박 10~20만원",
                transportation = "50만원~100만원"
            ),
            travelPlan = listOf(
                PlanDetail(
                    day = 1,
                    schedule = listOf(
                        Schedule(
                            title = "오전",
                            activities = listOf(
                                Activity(
                                    title = "마리나 베이 샌즈",
                                    category = "관광지",
                                    type = ActivityType.COMPLEX,
                                    imgUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/f/f9/Marina_Bay_Sands_in_the_evening_-_20101120.jpg/1200px-Marina_Bay_Sands_in_the_evening_-_20101120.jpg",
                                ),
                                Activity(
                                    title = "마리나 베이 샌즈 2",
                                    category = "관광지 2",
                                    type = ActivityType.COMPLEX,
                                    imgUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/f/f9/Marina_Bay_Sands_in_the_evening_-_20101120.jpg/1200px-Marina_Bay_Sands_in_the_evening_-_20101120.jpg",
                                )
                            )
                        ),
                        Schedule(
                            title = "이동",
                            activities = listOf(
                                Activity(
                                    title = "도보로 10분",
                                    category = null,
                                    type = ActivityType.TEXT,
                                    imgUrl = null
                                )
                            )
                        ),
                        Schedule(
                            title = "점심",
                            activities = listOf(
                                Activity(
                                    title = "식당명",
                                    category = "식당",
                                    type = ActivityType.COMPLEX,
                                    imgUrl = "https://gyeongju.go.kr/upload/content/thumb/20200529/4368708A9CC649CDB1EC5DD0C389804C.jpg",
                                )
                            )
                        ),
                        Schedule(
                            title = "이동",
                            activities = listOf(
                                Activity(
                                    title = "차로 10분",
                                    category = null,
                                    type = ActivityType.TEXT,
                                    imgUrl = null
                                )
                            )
                        ),
                        Schedule(
                            title = "카페",
                            activities = listOf(
                                Activity(
                                    title = "카페 1",
                                    category = "관광지",
                                    type = ActivityType.COMPLEX,
                                    imgUrl = "https://cdn.hankyung.com/photo/202209/01.31363897.1.jpg"
                                ),
                                Activity(
                                    title = "카페 2",
                                    category = "관광지",
                                    type = ActivityType.COMPLEX,
                                    imgUrl = "https://cdn.hankyung.com/photo/202209/01.31363897.1.jpg"
                                ),
                                Activity(
                                    title = "카페 3",
                                    category = "관광지",
                                    type = ActivityType.COMPLEX,
                                    imgUrl = "https://cdn.hankyung.com/photo/202209/01.31363897.1.jpg"
                                )
                            )
                        ),
                    )
                ),
                PlanDetail(
                    day = 2,
                    schedule = listOf(
                        Schedule(
                            title = "오전",
                            activities = listOf(
                                Activity(
                                    title = "마리나 베이 샌즈",
                                    category = "관광지",
                                    type = ActivityType.COMPLEX,
                                    imgUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/f/f9/Marina_Bay_Sands_in_the_evening_-_20101120.jpg/1200px-Marina_Bay_Sands_in_the_evening_-_20101120.jpg",
                                ),
                                Activity(
                                    title = "마리나 베이 샌즈 2",
                                    category = "관광지 2",
                                    type = ActivityType.COMPLEX,
                                    imgUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/f/f9/Marina_Bay_Sands_in_the_evening_-_20101120.jpg/1200px-Marina_Bay_Sands_in_the_evening_-_20101120.jpg",
                                )
                            )
                        ),
                        Schedule(
                            title = "이동",
                            activities = listOf(
                                Activity(
                                    title = "도보로 10분",
                                    category = null,
                                    type = ActivityType.TEXT,
                                    imgUrl = null
                                )
                            )
                        ),
                        Schedule(
                            title = "점심",
                            activities = listOf(
                                Activity(
                                    title = "식당명",
                                    category = "식당",
                                    type = ActivityType.COMPLEX,
                                    imgUrl = "https://gyeongju.go.kr/upload/content/thumb/20200529/4368708A9CC649CDB1EC5DD0C389804C.jpg",
                                )
                            )
                        ),
                        Schedule(
                            title = "이동",
                            activities = listOf(
                                Activity(
                                    title = "차로 10분",
                                    category = null,
                                    type = ActivityType.TEXT,
                                    imgUrl = null
                                )
                            )
                        ),
                        Schedule(
                            title = "카페",
                            activities = listOf(
                                Activity(
                                    title = "카페 1",
                                    category = "관광지",
                                    type = ActivityType.COMPLEX,
                                    imgUrl = "https://cdn.hankyung.com/photo/202209/01.31363897.1.jpg"
                                ),
                                Activity(
                                    title = "카페 2",
                                    category = "관광지",
                                    type = ActivityType.COMPLEX,
                                    imgUrl = "https://cdn.hankyung.com/photo/202209/01.31363897.1.jpg"
                                ),
                                Activity(
                                    title = "카페 3",
                                    category = "관광지",
                                    type = ActivityType.COMPLEX,
                                    imgUrl = "https://cdn.hankyung.com/photo/202209/01.31363897.1.jpg"
                                )
                            )
                        ),
                    )
                ),
            )
        )
    }
}
