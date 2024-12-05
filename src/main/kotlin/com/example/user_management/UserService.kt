package com.example.user_management

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class UserService(private val userRepository: UserRepository) {
    private val logger = LoggerFactory.getLogger(UserService::class.java)

    fun save(name: String, email: String, roles: List<String>, metadata: Map<String, String>, tenant: String = "no_tenant"): User {
        logger.info("Saving user with name: $name and email: $email")

        val user = User(
            id = UUID.randomUUID().toString(),
            name = name,
            email = email,
            roles = roles,
            tenantId = tenant,
            metadata = metadata
        )
        return userRepository.save(user)
    }

    fun findById(id: String): User? {
        return userRepository.findById(id)
    }

    fun findAll(): List<User> {
        return userRepository.findAll()
    }

    fun update(id: String, updates: UserDto): User? {
        logger.info("Updating user with ID: $id")

        val user = userRepository.findById(id) ?: return null
        val updatedUser = user.copy(
            name = updates.name ?: user.name,
            email = updates.email ?: user.email,
            roles = updates.roles ?: user.roles,
            metadata = updates.metadata ?: user.metadata
        )
        return userRepository.save(updatedUser)
    }

    fun deleteById(id: String): User? {
        val user = userRepository.findById(id) ?: return null
        userRepository.deleteById(id)
        return user
    }
}