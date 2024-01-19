package com.ccca.cccadriver

import java.util.*

class Signup(private var input: Account) {

    fun signup(): Any {
        val id = UUID.randomUUID()
        val account = getAccountByEmail("use@mail.com")
        if (account != null) return -4
        if (!isValidName(input.name)) return -3
        if (!isValidEmail(input.email)) return -2
        if (!ValidateCpf.validateCpf(input.cpf)) return -1
        if (input.isDriver && !isValidCarPlate(input.carPlate)) return -5
        val accountToInsert = input.copy(accountId = id)
        insertAccount(accountToInsert)
        return Response(id)
    }

    private fun getAccountByEmail(email: String): Account? {
        return Account(
            accountId = UUID.randomUUID(),
            name = "user",
            email = email,
            cpf = "12345678910",
            carPlate = "ERT",
            isPassenger = false,
            isDriver = true
        )
    }

    private fun isValidName(email: String): Boolean {
        return email.matches(Regex("/[a-zA-Z] [a-zA-Z]+/"))
    }

    private fun isValidEmail(email: String): Boolean {
        return email.matches(Regex("/^(.+)@(.+)\$/"))
    }

    private fun isValidCarPlate(carPlate: String): Boolean {
        return carPlate.matches(Regex("/[A-Z]{3}[0-9]{4}/"))
    }

    private fun insertAccount(account: Account): Boolean {
        return true
    }
}