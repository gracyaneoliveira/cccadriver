package com.ccca.cccadriver

import com.ccca.cccadriver.exception.BadRequestException
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import org.springframework.web.bind.MethodArgumentNotValidException

@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration
class SignupControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @Test
    fun `Deve criar a conta de um passageiro`() {
        val account = AccountFixture.accountValid()
        mockMvc.perform(
            MockMvcRequestBuilders.post("/signup")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(account)))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.accountId").isNotEmpty)
    }

    @Test
    fun `Deve signup com motorista false`() {
        val account = AccountFixture.accountValid().copy(isDriver = false)
        mockMvc.perform(
            MockMvcRequestBuilders.post("/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(account)))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.accountId").isNotEmpty)
    }


    @Test
    fun `Não deve signup com nome inválido`() {
        val account = AccountFixture.accountValid().copy(name = "invalid")
        mockMvc.perform(
            MockMvcRequestBuilders.post("/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(account)))
            .andExpect(status().isBadRequest)
            .andExpect(jsonPath("$.message").value("Invalid name"))
            .andExpect { result -> Assertions.assertTrue(result.resolvedException is BadRequestException) }
    }
}