package com.example.user_management

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/users")
class UserController(private val userService: UserService) {
    @GetMapping
    fun getAllUsers(): ResponseEntity<List<User>> = ResponseEntity.ok(userService.findAll())

    @PostMapping()
    fun createUser(user: User): ResponseEntity<User> {
        println(user)
        userService.save(user.name, user.email, user.roles, user.metadata, user.tenantId)
        return ResponseEntity.ok(user)
    }
}

