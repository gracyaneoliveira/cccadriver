package com.ccca.cccadriver

import java.util.*

object GetAccount {
    fun getAccount(accountId: UUID): Account {
        return Account(
            accountId = UUID.randomUUID(),
            name = "user",
            email = "user@mail.com",
            cpf = "12345678910",
            carPlate = "ERT",
            isPassenger = false,
            isDriver = true
        )
    }
}