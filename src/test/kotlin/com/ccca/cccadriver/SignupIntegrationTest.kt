package com.ccca.cccadriver

import com.ccca.cccadriver.exception.AccountAlreadyExistException
import com.ccca.cccadriver.exception.BadRequestException
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class SignupIntegrationTest {

    private lateinit var signup: Signup

    private lateinit var getAccount: GetAccount

    @Autowired
    private lateinit var accountRepository: AccountRepository

    @BeforeEach
    fun setup() {
        signup = Signup(accountRepository)
        getAccount = GetAccount(accountRepository)
    }

    @Test
    fun `Deve criar a conta de um passageiro`() {
        val input = AccountFixture.accountValid()
        val outputSignup = signup.execute(input)
        assertNotNull(outputSignup.accountId)
        val outputGetAccount = getAccount.execute(outputSignup.accountId!!)
        assertEquals(outputGetAccount.name, input.name)
        assertEquals(outputGetAccount.email, input.email)
        assertEquals(outputGetAccount.cpf, input.cpf)
        assertEquals(outputGetAccount.isPassenger, input.isPassenger)
    }

    @Test
    fun `Deve criar a conta de um motorista`() {
        val input = AccountFixture.accountValid().copy(isDriver = true)
        val outputSignup = signup.execute(input)
        assertNotNull(outputSignup.accountId)
        val outputGetAccount = getAccount.execute(outputSignup.accountId!!)
        assertEquals(outputGetAccount.name, input.name)
        assertEquals(outputGetAccount.email, input.email)
        assertEquals(outputGetAccount.cpf, input.cpf)
        assertEquals(outputGetAccount.isDriver, input.isDriver)
    }

    @Test
    fun `Não deve criar um passageiro se o cpf for inválido`() {
        val input = AccountFixture.accountValid().copy(cpf = "974563215")
        val result = assertThrows<BadRequestException> { signup.execute(input) }
        assertEquals("Invalid cpf", result.message)
    }

    @Test
    fun `Não deve criar um passageiro se a conta já existe`() {
        val input = AccountFixture.accountValid()
        signup.execute(input)
        val result = assertThrows<AccountAlreadyExistException> { signup.execute(input) }
        assertEquals("Account already exists", result.message)
    }

    @Test
    fun `Não deve criar a conta de um motorista se a placa for inválida`() {
        val input = AccountFixture.accountValid().copy(carPlate = "invalid", isDriver = true)
        val result = assertThrows<BadRequestException> { signup.execute(input) }
        assertEquals("Invalid car plate", result.message)
    }
}