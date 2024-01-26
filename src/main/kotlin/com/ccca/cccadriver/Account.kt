package com.ccca.cccadriver

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import java.util.UUID

@Entity(name = "account")
data class Account(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val accountId: UUID?,
    val name: String,
    val email: String,
    val cpf: String,
    val carPlate: String,
    val isPassenger: Boolean,
    val isDriver: Boolean
)
