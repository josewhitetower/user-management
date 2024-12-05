package com.example.user_management

data class User(
    val id: String?,
    val name: String,
    val email: String,
    val roles: List<String> = emptyList(),
    val tenantId: String,
    val metadata: Map<String, String> = emptyMap()
)

data class UserDto(
    val name: String?,
    val email: String?,
    val roles: List<String>? = emptyList(),
    val metadata: Map<String, String>? = emptyMap()
)