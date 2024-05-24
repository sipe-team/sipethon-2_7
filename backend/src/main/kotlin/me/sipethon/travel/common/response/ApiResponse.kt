package me.sipethon.travel.common.response

data class ApiResponse<T> private constructor(
    val data: T?,
    val message: String?,
    val result: String,
) {
    companion object {
        fun <T> success(data: T): ApiResponse<T> {
            return ApiResponse(data, null, "success")
        }

        fun <T> fail(message: String? = null): ApiResponse<T> {
            return ApiResponse(null, message, "fail")
        }
    }
}
