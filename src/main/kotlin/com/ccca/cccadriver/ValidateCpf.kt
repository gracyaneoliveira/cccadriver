package com.ccca.cccadriver

object ValidateCpf {

    private const val CPF_LENGTH = 11

    fun validateCpf (rawCpf: String): Boolean {
        if (rawCpf.isEmpty()) return false
        val cpf = removeNonDigits(rawCpf)
        if (isInvalidLength(cpf)) return false
        if (hasAllDigitsEqual(cpf)) return false
        val digit1 = calculateDigit(cpf, 10)
        val digit2 = calculateDigit(cpf, 11)
        return extractDigit(cpf) == "${digit1}${digit2}"
    }

    private fun removeNonDigits (cpf: String): String {
        return cpf.replace("\\D/g", "")
    }

    private fun isInvalidLength (cpf: String): Boolean{
        return cpf.length != CPF_LENGTH;
    }

    private fun hasAllDigitsEqual (cpf: String): Boolean {
        val firstCpfDigit = cpf.first()
        return cpf.all { digit -> digit == firstCpfDigit }
    }

    private fun calculateDigit (cpf: String, factor: Int): Int {
        var total = 0
        var currentFactor = factor
        for (digit in cpf) {
            if (currentFactor > 1) total += digit.toString().toInt() * currentFactor--
        }
        val rest = total%11
        return if (rest < 2) 0 else (11 - rest)
    }

    private fun extractDigit (cpf: String): String {
        return cpf.slice(9..10)
    }
}
