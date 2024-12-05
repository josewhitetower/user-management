package com.example.user_management

import org.springframework.stereotype.Repository

@Repository
class UserRepository {
    private val users = mutableMapOf<String, User>()

    fun save(user: User): User {
        users[user.id!!] = user
        return user
    }

    fun findById(id: String): User? {
        return users[id]
    }

    fun findAll(): List<User> {
        return users.values.toList()
    }

    fun deleteById(id: String) {
        users.remove(id)
    }
}