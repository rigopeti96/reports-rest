package hu.aut.bme.report_rest_springdata

import hu.aut.bme.report_rest_springdata.repository.RoleRepository
import org.junit.Assert.*
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders


@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
class UserUnitTests {
    private val name = "Test User"
    private val username = "testUser"
    private val password = "testuser"
    private val email = "test.user@testuser.com"
    private var roles: Set<String> = setOf("USER")

    private val expectedSuccessfulMessage = "User registered successfully!"

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var roleRepository: RoleRepository

    /**
     * Test case: create a user
     * Expected result: method is successful
     */
    @Test
    fun createUserTest(){
        val requestBuilder = createTestUser()

        val result = mockMvc.perform(requestBuilder).andReturn()
        val response = result.response


        assertEquals(HttpStatus.OK.value(), response.status)
        assertTrue(response.errorMessage == null)
        assertTrue(response.contentAsString.contains(expectedSuccessfulMessage))
    }

    /**
     * Test case: create a user then create again with same username
     * Expected result: method returns with exception
     */
    @Test
    fun recreateUserTest(){
        val requestBuilder = createTestUser()

        val exception = assertThrows(Exception::class.java) {
            val result = mockMvc.perform(requestBuilder).andReturn()
        }

        val expectedMessage = "Error: Employeename is already taken!"
        val actualMessage = exception.message

        assertTrue(actualMessage!!.contains(expectedMessage))
    }

    /**
     * Test case: create a user then create again with same e-mail address
     * Expected result: method returns with exception
     */
    @Test
    fun recreateUserWithSameEmailTest(){
        val requestBuilder = createTestUser()

        val exception = assertThrows(Exception::class.java) {
            val result = mockMvc.perform(requestBuilder).andReturn()
        }

        val expectedMessage = "Error: Email is already in use!"
        val actualMessage = exception.message

        assertTrue(actualMessage!!.contains(expectedMessage))
    }

    /**
     * Create test user
     * @return result of mock mvc request builder
     */
    private fun createTestUser(): MockHttpServletRequestBuilder{
        val signUpRequestString = "{" +
                "\"name\" = $name," +
                "\"username\" = $username," +
                "\"password\" = $password," +
                "\"email\" = $email," +
                "\"roles\" = $roles," +
                "}"

        return MockMvcRequestBuilders
                .post("api/auth/signup")
                .accept(MediaType.APPLICATION_JSON).content(signUpRequestString)
                .contentType(MediaType.APPLICATION_JSON)
    }
}