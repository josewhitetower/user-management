package com.example.user_management

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.slf4j.LoggerFactory

@SpringBootTest
class UserManagementApplicationTests {

	@Autowired
	private lateinit var userService: UserService

	private val logger = LoggerFactory.getLogger(UserManagementApplicationTests::class.java)

	@Test
	fun testSaveUser() {
		val user = userService.save("TestUser", "testuser@example.com", listOf("user"), mapOf("key" to "value"), "tenant1")
		assertNotNull(user)
		assertEquals("TestUser", user.name)
		assertEquals("testuser@example.com", user.email)
	}

	@Test
	fun testFindAllUsers() {
		 userService.save("TestUser", "testuser@example.com", listOf("user"), mapOf("key" to "value"), "tenant1")

		val users = userService.findAll()
		assertNotNull(users)
		assertEquals(2, users.size)
	}

	@Test
	fun updateUser() {
			userService.save(
				"TestUser",
				"testuser@example.com",
				listOf("user"),
				mapOf("key" to "value"),
				"tenant1"
			).let { user ->
				logger.info("User saved: $user")
				assertNotNull(user)
				val updatedUser = userService.update(user.id!!, UserDto(name = "UpdatedUser", null, null, null))
				assertNotNull(updatedUser)
				assertEquals("UpdatedUser", updatedUser?.name)
			}


	}
}