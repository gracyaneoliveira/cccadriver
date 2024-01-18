package com.ccca.cccadriver

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class ValidateCpfTest {

    @ParameterizedTest
    @ValueSource(strings = ["97456321558", "71428793860", "87748248800"])
    fun `Deve testar se o cpf é válido`(cpf: String) {
        val isValid = ValidateCpf.validateCpf(cpf)
        assertEquals(true, isValid)
    }

    @Test
    fun `Deve testar se o cpf é inválido`() {
        val invalidCpfs = listOf("8774824880", "", "11111111111")
        for (cpf in invalidCpfs) {
            val isValid = ValidateCpf.validateCpf(cpf)
            assertEquals(false, isValid)
        }
    }
}