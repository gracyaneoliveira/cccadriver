package com.ccca.cccadriver

import java.util.UUID

data class Account(
    val accountId: UUID,
    val name: String,
    val email: String,
    val cpf: String,
    val carPlate: String,
    val isPassenger: Boolean,
    val isDriver: Boolean
)
