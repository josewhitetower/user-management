package com.example.user_management

import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import java.util.*

@Configuration
@Profile("dev")
class UserSeeder {
    @Bean
    fun seedUsers(userService: UserService) = CommandLineRunner {
        val users = (1..10).map {
            User(
                id = UUID.randomUUID().toString(),
                name = "User$it",
                email = "user$it@example.com",
                roles = listOf(if (it == 1) "admin" else "user"),
                tenantId = "tenant$it",
                metadata = mapOf("key$it" to "value$it")
            )
        }

        users.forEach { userService.save(it.name, it.email, it.roles, it.metadata, it.tenantId) }
    }
}