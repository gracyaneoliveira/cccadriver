package com.ccca.cccadriver

import org.springframework.stereotype.Service
import java.util.UUID

@Service
class GetAccount(
    private val accountRepository: AccountRepository
) {

    fun execute(accountId: UUID) : Account {
        return accountRepository.findById(accountId).orElse(null)
    }
}