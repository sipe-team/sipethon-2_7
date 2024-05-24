package me.sipethon.travel.application

import me.sipethon.travel.domain.User
import me.sipethon.travel.infrastructure.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository
) {
    fun findUserByEmail(email: String) = userRepository.findByEmail(email)

    fun create(email: String): User {
        val user = User(email = email)
        return userRepository.save(user)
    }
}
