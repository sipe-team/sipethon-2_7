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
            title = "싱가폴 여행 계획을\n아래와 같이 짜보았어요",
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
                                    title = "가든스 바이 더 베이",
                                    category = "관광지",
                                    type = ActivityType.COMPLEX,
                                    imgUrl = "https://i2.wp.com/www.agoda.com/wp-content/uploads/2019/07/Gardens-by-the-Bay-Illuminated-Supertree-Grove.jpg",
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
                                    title = "동방미식",
                                    category = "식당",
                                    type = ActivityType.COMPLEX,
                                    imgUrl = "https://www.visitsingapore.com/ko_kr/editorials/where-to-eat-in-chinatown-singapore/jcr:content/par/mobile_21_content_sl/sliderccpar1/editorial_generic_co/content/item1.thumbnail.image-path1.560.315.jpg",
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
                                    title = "TWG",
                                    category = "관광지",
                                    type = ActivityType.COMPLEX,
                                    imgUrl = "https://media-cdn.tripadvisor.com/media/photo-s/1b/45/e2/00/caption.jpg"
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
                                    title = "센토사 섬",
                                    category = "관광지",
                                    type = ActivityType.COMPLEX,
                                    imgUrl = "https://tourimage.interpark.com//Spot/279/19691/201605/6359926833614836620.jpg",
                                ),
                                Activity(
                                    title = "유니버설 스튜디오 싱가포르",
                                    category = "관광지",
                                    type = ActivityType.COMPLEX,
                                    imgUrl = "https://blog.kakaocdn.net/dn/dJhkUl/btqxCN3enbX/KfCRQNycLnuMhCJlyO1MwK/img.jpg",
                                )
                            )
                        ),
                        Schedule(
                            title = "이동",
                            activities = listOf(
                                Activity(
                                    title = "도보로 5분",
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
                                    title = "트라피자",
                                    category = "식당",
                                    type = ActivityType.COMPLEX,
                                    imgUrl = "https://tourimage.interpark.com//Spot/279/19691/201008/6341850265369776473.jpg",
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
                            title = "오후",
                            activities = listOf(
                                Activity(
                                    title = "S.E.A. 아쿠아리움",
                                    category = "관광지",
                                    type = ActivityType.COMPLEX,
                                    imgUrl = "https://res.klook.com/images/fl_lossy.progressive,q_65/c_fill,w_1200,h_630/w_80,x_15,y_15,g_south_west,l_Klook_water_br_trans_yhcmh3/activities/dczglcfjh2ivknwtjtb8/%EC%8B%B1%EA%B0%80%ED%8F%AC%EB%A5%B4%20S.E.A.%20%EC%95%84%EC%BF%A0%EC%95%84%EB%A6%AC%EC%9B%80%E2%84%A2%201%EC%9D%BC%20%ED%8B%B0%EC%BC%93.jpg"
                                ),
                                Activity(
                                    title = "클락 키",
                                    category = "관광지",
                                    type = ActivityType.COMPLEX,
                                    imgUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSee06sZTAb3DPJzCIBF7k-cuo4O6PdZtu7DmfiQDwHUg&s"
                                )
                            )
                        ),
                    )
                ),
                PlanDetail(
                    day = 3,
                    schedule = listOf(
                        Schedule(
                            title = "오전",
                            activities = listOf(
                                Activity(
                                    title = "리틀 인디아",
                                    category = "관광지",
                                    type = ActivityType.COMPLEX,
                                    imgUrl = "https://www.visitsingapore.com/ko_kr/walking-tour/culture/in-the-neighbourhood-little-india/jcr:content/par-carousel/carousel_detailpage/carousel/item_2.thumbnail.carousel-img.600.338.jpg",
                                )
                            )
                        ),
                        Schedule(
                            title = "이동",
                            activities = listOf(
                                Activity(
                                    title = "도보로 5분",
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
                                    title = "부기스 스트리트",
                                    category = "식당",
                                    type = ActivityType.COMPLEX,
                                    imgUrl = "https://tourimage.interpark.com//Spot/279/19691/201506/6357068253205821910.jpg",
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
                            title = "오후",
                            activities = listOf(
                                Activity(
                                    title = "싱가포르 플라이어",
                                    category = "관광지",
                                    type = ActivityType.COMPLEX,
                                    imgUrl = "https://www.visitsingapore.com/content/dam/desktop/global/see-do-singapore/recreation-leisure/sgflyer-banner1-1670x940.jpg"
                                ),
                                Activity(
                                    title = "에스플러네이드",
                                    category = "관광지",
                                    type = ActivityType.COMPLEX,
                                    imgUrl = "https://mblogthumb-phinf.pstatic.net/MjAxOTAzMjZfMTM3/MDAxNTUzNTYzMzEzMTIx.w-rxWYeLksIACNM27CefJHNwFI4LsE7lWDf3vbHt-agg.tXNgTBesjZSF-k2OIzJytJJpwJewPinLQkm4N2fzoqcg.JPEG.designpress2016/11.jpg?type=w800"
                                )
                            )
                        ),
                        Schedule(
                            title = "저녁",
                            activities = listOf(
                                Activity(
                                    title = "리버 크루즈",
                                    category = "관광지",
                                    type = ActivityType.COMPLEX,
                                    imgUrl = "https://res.klook.com/images/fl_lossy.progressive,q_65/c_fill,w_1295,h_863/w_80,x_15,y_15,g_south_west,l_Klook_water_br_trans_yhcmh3/activities/vj3hgjkxyxcaegbbs86b/%EC%8B%B1%EA%B0%80%ED%8F%AC%EB%A5%B4%EB%A6%AC%EB%B2%84%ED%81%AC%EB%A3%A8%EC%A6%88byWaterB.jpg"
                                )
                            )
                        )
                    )
                ),
                PlanDetail(
                    day = 4,
                    schedule = listOf(
                        Schedule(
                            title = "이동",
                            activities = listOf(
                                Activity(
                                    title = "차로 1시간",
                                    category = null,
                                    type = ActivityType.TEXT,
                                    imgUrl = null
                                )
                            )
                        ),
                        Schedule(
                            title = "공항",
                            activities = listOf(
                                Activity(
                                    title = "창이 공항",
                                    category = "공항",
                                    type = ActivityType.COMPLEX,
                                    imgUrl = "https://www.kf.or.kr/old/achnewsletter/news/202009280300103461.png",
                                )
                            )
                        ),
                        Schedule(
                            title = "공항",
                            activities = listOf(
                                Activity(
                                    title = "인천 공항",
                                    category = "공항",
                                    type = ActivityType.COMPLEX,
                                    imgUrl = "https://www.spcmagazine.com/wp-content/uploads/2017/06/SPC_blog_1.jpg"
                                )
                            )
                        )
                    )
                ),
            )
        )
    }
}
