package com.ccca.cccadriver

import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional
import java.util.UUID

interface AccountRepository : JpaRepository<Account, UUID> {
    fun findByEmail(email: String) : Optional<Account>
}