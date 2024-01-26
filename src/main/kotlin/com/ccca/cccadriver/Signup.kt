package com.ccca.cccadriver

import com.ccca.cccadriver.exception.AccountAlreadyExistException
import com.ccca.cccadriver.exception.BadRequestException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class Signup(
    private val accountRepository: AccountRepository
) {

    fun execute(input: Account): Account {
        val existingAccount = getByEmail(input.email)
        if (existingAccount != null) throw AccountAlreadyExistException("Account already exists")
        if (!isValidName(input.name)) throw BadRequestException("Invalid name")
        if (!isValidEmail(input.email)) throw BadRequestException("Invalid email")
        if (!ValidateCpf.validateCpf(input.cpf)) throw BadRequestException("Invalid cpf")
        if (input.isDriver && !isValidCarPlate(input.carPlate)) throw BadRequestException("Invalid car plate")
        val id = UUID.randomUUID()
        val accountToInsert = input.copy(accountId = id)
        val accountSaved = insertAccount(accountToInsert)
        return accountSaved
    }

    private fun getByEmail(email: String): Account? {
        return accountRepository.findByEmail(email).orElse(null)
    }

    private fun isValidName(email: String): Boolean {
        return email.matches(Regex("[a-zA-Z]* [a-zA-Z]*"))
    }

    private fun isValidEmail(email: String): Boolean {
        return email.matches(Regex("^(.+)@(.+)\$"))
    }

    private fun isValidCarPlate(carPlate: String): Boolean {
        return carPlate.matches(Regex("[A-Z]{3}[0-9]{4}"))
    }

    @Transactional
    private fun insertAccount(account: Account): Account {
        return accountRepository.save(account)
    }
}
