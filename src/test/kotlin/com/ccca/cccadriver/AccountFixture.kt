package com.ccca.cccadriver

object AccountFixture {

    fun accountValid() = account()

    private fun account(): Account {
        return Account(
            null,
            "Jonh Doe",
            "john.doe${Math.random()}@gmail.com",
            "97456321558",
            "ADR1234",
            isPassenger = true,
            isDriver = false
        )
    }
}