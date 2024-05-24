package me.sipethon.travel.common.auth

import me.sipethon.travel.application.UserService
import me.sipethon.travel.domain.User
import org.springframework.core.MethodParameter
import org.springframework.stereotype.Component
import org.springframework.web.bind.support.WebDataBinderFactory
import org.springframework.web.context.request.NativeWebRequest
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.method.support.ModelAndViewContainer

@Component
class LoginUserArgumentResolver(
    private val userService: UserService
) : HandlerMethodArgumentResolver {
    override fun supportsParameter(parameter: MethodParameter): Boolean {
        return parameter.hasParameterAnnotation(LoginUser::class.java)
    }

    override fun resolveArgument(
        parameter: MethodParameter,
        mavContainer: ModelAndViewContainer?,
        webRequest: NativeWebRequest,
        binderFactory: WebDataBinderFactory?
    ): Long {
        val emailHeader = webRequest.getHeader("user-email")
        val user = emailHeader?.let {
            getOrCreateUser(it)
        }
        return user?.id ?: throw IllegalStateException("User not found")
    }

    private fun getOrCreateUser(email: String): User {
        return userService.findUserByEmail(email) ?: run {
            userService.create(email)
        }
    }
}
